package com.game.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.dto.TeamDto;
import com.game.dto.TeamFormDto;
import com.game.dto.TeamImageDto;
import com.game.entity.Team;
import com.game.entity.TeamImage;
import com.game.repository.TeamImageRepository;
import com.game.repository.TeamRepository;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
	private final TeamRepository teamRepository;
	private final TeamImageService teamImageService;
	private final TeamImageRepository teamImageRepository;
	
	
	//add team
	
	public Long SaveTeam(TeamFormDto teamFormDto, MultipartFile teamImageFile) throws Exception{
			Team team = teamFormDto.createTeam();
			teamRepository.save(team);
		
		//이미지 등록
//				for(int i=0; i<teamImageFileList.size(); i++) {
//					TeamImage teamImage = new TeamImage();
//					teamImage.setTeam(team);
//					
//					//첫번째 이미지 일때 대표 상품 이미지 여부 지정
//					if(i == 0) { 
//						teamImage.setTeamRepimgYn("Y");
//					} else {
//						teamImage.setTeamRepimgYn("N");
//					}
//					
//					teamImageService.saveTeamImage(teamImage, teamImageFileList.get(i));
//				}
			TeamImage teamImage = new TeamImage();
			teamImage.setTeam(team);
			teamImage.setTeamRepimgYn("Y");
			teamImageService.saveTeamImage(teamImage, teamImageFile);
			
			return team.getId();
		
	} 
	
	
	//team list
	@Transactional(readOnly = true)
	public Page<Team> getAdminTeamDtoPage(Pageable pageable){
		return teamRepository.getAdminTeamPage(pageable);	
	}
	
	
	@Transactional(readOnly = true)
	public Page<TeamDto> getTeamDtoPage(Pageable pageable){
		return teamRepository.getTeamPage(pageable);
	}

	// team detail
	@Transactional(readOnly = true)
	public TeamFormDto getTeamDetail(Long teamId) {
		//1. team_image테이블의 이미지를 가져온다
		List<TeamImage> teamImageList = teamImageRepository.findByTeamIdOrderByIdAsc(teamId);
		List<TeamImageDto> teamImageDtoList = new ArrayList<>();
		//엔티티 객체 -> dto객체로 변환
		for(TeamImage teamImage : teamImageList) {
			TeamImageDto teamImageDto = TeamImageDto.of(teamImage);
			teamImageDtoList.add(teamImageDto);
		}
		//2. team테이블에 있는 데이터를 가져온다.
		Team team = teamRepository.findById(teamId)
								  .orElseThrow(EntityNotFoundException::new);
		
		//엔티티 객체 -> dto객체로 변환
		TeamFormDto teamFormDto = TeamFormDto.of(team);
		
		//team의 이미지 정보를 넣어준다.
		teamFormDto.setTeamImageDtoList(teamImageDtoList);
		
		return teamFormDto;
	}


	
	//modify
	public Long updateTeam(TeamFormDto teamFormDto, List<MultipartFile> teamImgFileList) throws Exception {
		Team team = teamRepository.findById(teamFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		
		team.updateTeam(teamFormDto);
		
		List<Long> teamImageIds = teamFormDto.getTeamImageIds();

		System.out.println("updateTeam - teamImageIds :" + teamImageIds);
		
//		
		for(int i=0; i<teamImgFileList.size(); i++) {
			teamImageService.updateTeamImage(teamImageIds.get(i), teamImgFileList.get(i));
		}
		
		return team.getId();
		
		
	}


	
}

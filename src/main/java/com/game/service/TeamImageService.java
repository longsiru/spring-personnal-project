package com.game.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


import com.game.entity.TeamImage;
import com.game.repository.TeamImageRepository;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class TeamImageService {
	@Value("${teamImageLocation}")
	private String teamImageLocation; // C:/game/team
	
	private final TeamImageRepository teamImageRepository;
	
	private final FileService fileService;
	
	//team 이미지 저장 메소드
		public void saveTeamImage(TeamImage teamImage, MultipartFile teamImageFile) throws Exception {
			String teamOriImgName = teamImageFile.getOriginalFilename(); //파일 이름
			String teamImageName = "";
			String teamImageUrl = "";
			
			//파일 업로드
			if(!StringUtils.isEmpty(teamOriImgName)) {
				teamImageName = fileService.uploadFile(teamImageLocation, teamOriImgName, teamImageFile.getBytes());
				teamImageUrl = "/game/team/" + teamImageName;
			}
			
			//상품 이미지 정보 저장
			teamImage.updateTeamImage(teamOriImgName, teamImageName, teamImageUrl);
			teamImageRepository.save(teamImage);
			
		}

		public void updateTeamImage(Long teamImageId, MultipartFile teamImageFile) throws Exception {
			if(!teamImageFile.isEmpty()) {
				TeamImage savedTeamImage = teamImageRepository.findById(teamImageId)
					.orElseThrow(EntityNotFoundException::new);
				
				if(!StringUtils.isEmpty(savedTeamImage.getTeamImageName())) {
					fileService.deleteFile(teamImageLocation + "/" + savedTeamImage.getTeamImageName()); 
				}
				
				//수정된 이미지 파일 업로드
				String teamOriImgName = teamImageFile.getOriginalFilename();
				String teamImageName = fileService.uploadFile(teamImageLocation, teamOriImgName, teamImageFile.getBytes());
				String teamImgUrl = "/game/team/" + teamImageName;
				
				savedTeamImage.updateTeamImage(teamOriImgName, teamImageName, teamImgUrl);
			}
			
		}
		
}



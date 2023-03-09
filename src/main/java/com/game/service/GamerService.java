package com.game.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.dto.GamerDto;
import com.game.dto.GamerFormDto;
import com.game.dto.GamerImageDto;
import com.game.entity.Gamer;
import com.game.entity.GamerImage;

//import com.game.entity.TeamImage;
import com.game.repository.GamerImageRepository;
import com.game.repository.GamerRepository;
//import com.game.repository.TeamImageRepository;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class GamerService {
	private final GamerRepository gamerRepository;
	private final GamerImageService gamerImageService;
	private final GamerImageRepository gamerImageRepository;
	
	
	//add team
	
	public Long SaveGamer(GamerFormDto gamerFormDto, MultipartFile gamerImageFile) throws Exception{
		Gamer gamer = gamerFormDto.createGamer();
		gamerRepository.save(gamer);
		//이미지 등록
//				for(int i=0; i<gamerImageFileList.size(); i++) {
//					GamerImage gamerImage = new GamerImage();
//					gamerImage.setGamer(gamer);
//					
//					//첫번째 이미지 일때 대표 상품 이미지 여부 지정
//					if(i == 0) { 
//						gamerImage.setGamerRepimgYn("Y");
//					} else {
//						gamerImage.setGamerRepimgYn("N");
//					}
//					
//					gamerImageService.saveGamerImage(gamerImage, gamerImageFileList.get(i));
//				}
				
			GamerImage gamerImage = new GamerImage();
			gamerImage.setGamer(gamer);
			gamerImage.setGamerRepimgYn("Y");
			gamerImageService.saveGamerImage(gamerImage, gamerImageFile);
			return gamer.getId();
		
	} 
	
	
	//gamer list
	@Transactional(readOnly = true)
	public Page<Gamer> getAdminGamerDtoPage(Pageable pageable){
		return gamerRepository.getAdminGamerPage(pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<GamerDto> getGamerDtoPage(Pageable pageable){
		return gamerRepository.getGamerPage(pageable);
	}
	
	
	//gamer detail
	@Transactional(readOnly = true)
	public GamerFormDto getGamerDetail(Long gamerId) {
		List<GamerImage> gamerImageList = gamerImageRepository.findByGamerIdOrderByIdAsc(gamerId);
		List<GamerImageDto> gamerImageDtoList = new ArrayList<>();
		
		for(GamerImage gamerImage : gamerImageList) {
			GamerImageDto gamerImageDto = GamerImageDto.of(gamerImage);
			gamerImageDtoList.add(gamerImageDto);
		}
		
		//2. gamer테이블에 있는 데이터를 가져온다.
		Gamer gamer = gamerRepository.findById(gamerId)
									 .orElseThrow(EntityNotFoundException::new);
		
		//엔티티 객체 -> dto객체로 변환
		GamerFormDto gamerFormDto = GamerFormDto.of(gamer);
		
		//gamer의 이미지 정보를 넣어준다.
		gamerFormDto.setGamerImageDtoList(gamerImageDtoList);
		
		return gamerFormDto;
		
	}
	
	//gamer modify
	public Long updateGamer(GamerFormDto gamerFormDto, List<MultipartFile> gamerImageFileList) throws Exception {
		Gamer gamer = gamerRepository.findById(gamerFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		gamer.updateGamer(gamerFormDto);
		
		List<Long> gamerImageIds = gamerFormDto.getGamerImageIds();
		
		for(int i=0; i<gamerImageFileList.size(); i++) {
			gamerImageService.updeteGamerImage(gamerImageIds.get(i), gamerImageFileList.get(i));
		}
		
		return gamer.getId();
	}
	
	//gamer delete
	public void deleteGamer(Long gamerId) {
		Gamer gamer = gamerRepository.findById(gamerId)
									 .orElseThrow(EntityNotFoundException::new);
		gamerRepository.delete(gamer);
	}
}

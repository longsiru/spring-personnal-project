package com.game.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.game.entity.GamerImage;
import com.game.repository.GamerImageRepository;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class GamerImageService {
	@Value("${gamerImageLocation}")
	private String gamerImageLocation; // C:/game/gamer
	
	private final GamerImageRepository gamerImageRepository;
	
	private final FileService fileService;
	
	//gamer 이미지 저장 메소드
		public void saveGamerImage(GamerImage gamerImage, MultipartFile gamerImageFile) throws Exception {
			String gamerOriImgName = gamerImageFile.getOriginalFilename(); //파일 이름
			String gamerImageName = "";
			String gamerImageUrl = "";
			
			//파일 업로드
			if(!StringUtils.isEmpty(gamerOriImgName)) {
				gamerImageName = fileService.uploadFile(gamerImageLocation, gamerOriImgName, gamerImageFile.getBytes());
				gamerImageUrl = "/game/gamer/" + gamerImageName;
			}
			
			//상품 이미지 정보 저장
			gamerImage.updateGamerImage(gamerOriImgName, gamerImageName, gamerImageUrl);
			gamerImageRepository.save(gamerImage);
			
			
		}

		public void updeteGamerImage(Long gamerImageId, MultipartFile gamerImageFile) throws Exception{
			if(!gamerImageFile.isEmpty()) {
				GamerImage savedGamerImage = gamerImageRepository.findById(gamerImageId)
						.orElseThrow(EntityNotFoundException::new);
				
				//기존 이미지 파일 삭제
				if(!StringUtils.isEmpty(savedGamerImage.getGamerImageName())) {
					fileService.deleteFile(gamerImageLocation + "/" + savedGamerImage.getGamerImageName()); 
				}
				
				String gamerOriImgName = gamerImageFile.getOriginalFilename();
				String gamerImageName = fileService.uploadFile(gamerImageLocation, gamerOriImgName, gamerImageFile.getBytes());
				String gamerImageUrl = "/game/gamer/" + gamerImageName;
				
				savedGamerImage.updateGamerImage(gamerOriImgName, gamerImageName, gamerImageUrl);
			}
			
		}
		
		public void deleteGamerImage(Long gamerId) throws Exception{
//			if(!gamerImageFile.isEmpty()) {
				GamerImage deleteGamerImage = gamerImageRepository.findByGamerId(gamerId);
//						.orElseThrow(EntityNotFoundException::new);
				
				//기존 이미지 파일 삭제
				if(!StringUtils.isEmpty(deleteGamerImage.getGamerImageName())) {
					fileService.deleteFile(gamerImageLocation + "/" + deleteGamerImage.getGamerImageName()); 
				}
				
//				String gamerOriImgName = gamerImageFile.getOriginalFilename();
//				String gamerImageName = fileService.uploadFile(gamerImageLocation, gamerOriImgName, gamerImageFile.getBytes());
//				String gamerImageUrl = "/game/gamer/" + gamerImageName;
//				
				gamerImageRepository.delete(deleteGamerImage);
			}
//		}
}



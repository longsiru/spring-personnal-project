package com.game.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.game.entity.MatchImage;
import com.game.entity.PostImage;
import com.game.repository.MatchImageRepository;
import com.game.repository.PostImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchImgService {

	@Value("${matchImageLocation}")
	private String matchImageLocation; // C:/game/post
	
	private final MatchImageRepository matchImageRepository;
	
	private final FileService fileService;
	
	
	//post 이미지 저장 메소드
			public void saveMatchImage(MatchImage matchImage, MultipartFile matchImageFile) throws Exception {
				String matchOriImgName = matchImageFile.getOriginalFilename(); //파일 이름
				String matchImageName = "";
				String matchImageUrl = "";
				
				//파일 업로드
				if(!StringUtils.isEmpty(matchOriImgName)) {
					matchImageName = fileService.uploadFile(matchImageLocation, matchOriImgName, matchImageFile.getBytes());
					matchImageUrl = "/game/match/" + matchImageName;
				}
				
				//상품 이미지 정보 저장
				matchImage.updateMatchImage(matchOriImgName, matchImageName, matchImageUrl);
				matchImageRepository.save(matchImage);
				
			}
}

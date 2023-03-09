package com.game.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.game.entity.PostImage;
import com.game.repository.PostImageRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class PostImageService {
	@Value("${postImageLocation}")
	private String postImageLocation; // C:/game/post
	
	private final PostImageRepository postImageRepository;
	
	private final FileService fileService;
	
	//post 이미지 저장 메소드
		public void savePostImage(PostImage postImage, MultipartFile postImageFile) throws Exception {
			String postOriImgName = postImageFile.getOriginalFilename(); //파일 이름
			String postImageName = "";
			String postImageUrl = "";
			
			//파일 업로드
			if(!StringUtils.isEmpty(postOriImgName)) {
				postImageName = fileService.uploadFile(postImageLocation, postOriImgName, postImageFile.getBytes());
				postImageUrl = "/game/post/" + postImageName;
			}
			
			//상품 이미지 정보 저장
			postImage.updatePostImage(postOriImgName, postImageName, postImageUrl);
			postImageRepository.save(postImage);
			
		}
		
}



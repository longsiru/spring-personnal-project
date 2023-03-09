package com.game.dto;



import org.modelmapper.ModelMapper;

import com.game.entity.PostImage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostImageDto {
	private Long id;
	 
	 private String postImageName;
	 
	 private String postOriImgName;
	 
	 private String postImageUrl;
	 
	 private static ModelMapper modelMapper = new ModelMapper();
	 
	 public static PostImageDto of(PostImage postImage) {
		 return modelMapper.map(postImage, PostImageDto.class);
	 }
}
package com.game.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;


import com.game.entity.Post;

import lombok.*;

@Getter
@Setter
public class PostFormDto {
	 private Long id;
	 
	 @NotBlank(message = "Must enter the post title.")
	 private String postTitle;
	 
	 @NotBlank(message = "Must enter the post text.")
	 private String postText;
	 
	 private String createdBy;
		
	 private LocalDateTime regTime;
	 
	 private List<PostImageDto> postImageDtoList = new ArrayList<>(); //상품이미지 정보를 저장하는 리스트
	 private List<Long> postImageIds = new ArrayList<>(); //상품의 이미지 아이디를 저장 -> 수정시에 이미지를 담아 둘 용도.
	 
	 
	 private static ModelMapper modelMapper = new ModelMapper();
	 
	 public Post createPost() {
		return modelMapper.map(this, Post.class);
		 
	 }
	 
	 public static PostFormDto of(Post post) {
		return modelMapper.map(post, PostFormDto.class);
		 
	 }
	 
}

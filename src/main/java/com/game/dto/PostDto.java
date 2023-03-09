package com.game.dto;


import java.time.LocalDateTime;
import java.util.Date;

import com.game.entity.Users;
import com.querydsl.core.annotations.QueryProjection;

import lombok.*;

@Getter
@Setter
public class PostDto{
	private Long id;

	private String postTitle;

	private String postText;
	
	private String postImageUrl;

	private String createdBy;
	
	private LocalDateTime regTime;
	

	@QueryProjection 
	public PostDto(Long id, String postTitle, String postText, String postImageUrl, String createdBy, LocalDateTime regTime) {
		this.id = id;
		this.postTitle = postTitle;
		this.postText = postText;
		this.postImageUrl = postImageUrl;
		this.createdBy = createdBy;
		this.regTime = regTime;
		
	}
	 

}

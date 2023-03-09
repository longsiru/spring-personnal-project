package com.game.dto;


import java.time.format.DateTimeFormatter;


import org.modelmapper.ModelMapper;

import com.game.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private Long id;
	
	private String content;
	
	private String createdBy;
	
	private String commentEmail;
	
	private String commentUsers;
	
	private String regTime;
	
	private Long postId;
	
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	
	public Comment writeComment() {
		return modelMapper.map(this, Comment.class);
	}
	
	
	public static CommentDto of(Comment comment) {
		CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
		commentDto.setRegTime(comment.getRegTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a")));
		return commentDto;
	}

	
	
}

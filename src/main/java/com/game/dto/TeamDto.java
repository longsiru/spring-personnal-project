package com.game.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.*;

@Getter
@Setter
public class TeamDto {
	 private Long id;
	 
	 private String teamName;
	 
	 private String teamEmail;
	 
	 private String teamRank;
	 
	 private String teamWin;
	 
	 private String teamLose;
	 
	 private String teamIntro;
	 
	 private String teamImageUrl;
	 
	 private LocalDateTime regTime;//등록시간
		
	 private LocalDateTime updateTime;//수정시간
	 
	 @QueryProjection
	 public TeamDto(Long id, String teamName, String teamImageUrl) {
		 this.id = id;
		 this.teamName = teamName;
		 this.teamImageUrl = teamImageUrl;
	 }
	 
	 
}

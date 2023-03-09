package com.game.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.*;

@Getter
@Setter
public class GamerDto {
	 private Long id;
	 
	 private String gamerName;
	 
	 private String gamerEmail;
	 
	 private String gamerAge;
	 
	 private String gamerGender;
	 
	 private String gamerRoute;
	 
	 private String teamIntro;
	 
	 private String gamerImageUrl;
	 
	 private LocalDateTime regTime;//등록시간
		
	 private LocalDateTime updateTime;//수정시간
	 
	 
	 
		/*
		 * private TeamDto teamDto;
		 * 
		 * private String teamName;
		 */ 
	 
	 
	 @QueryProjection
	 public GamerDto(Long id, String gamerName, String gamerRoute,String gamerImageUrl) {
		 this.id = id;
		 this.gamerName = gamerName;
		 this.gamerRoute = gamerRoute;
		 this.gamerImageUrl = gamerImageUrl;
	 }
	 
	 
	 
}

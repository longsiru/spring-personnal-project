package com.game.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.*;

@Getter
@Setter
public class GamerDetailDto {
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
	 public GamerDetailDto(Long id, String gamerName, String gamerEmail, String gamerAge, String gamerGender, String teamIntro, String gamerRoute,String gamerImageUrl) {
		 this.id = id;
		 this.gamerName = gamerName;
		 this.gamerEmail = gamerEmail;
		 this.gamerAge = gamerAge;
		 this.gamerGender = gamerGender;
		 this.teamIntro = teamIntro;
		 this.gamerRoute = gamerRoute;
		 this.gamerImageUrl = gamerImageUrl;
	 }
	 
}

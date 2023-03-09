package com.game.dto;



import org.modelmapper.ModelMapper;

import com.game.entity.TeamImage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamImageDto {
	private Long id;
	 
	 private String teamImageName;
	 
	 private String teamOriImgName;
	 
	 private String teamImageUrl;
	 
	 private static ModelMapper modelMapper = new ModelMapper();
	 
	 public static TeamImageDto of(TeamImage teamImage) {
		 return modelMapper.map(teamImage, TeamImageDto.class);
	 }
}
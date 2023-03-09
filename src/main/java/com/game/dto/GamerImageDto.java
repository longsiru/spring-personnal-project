package com.game.dto;



import org.modelmapper.ModelMapper;

import com.game.entity.GamerImage;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamerImageDto {
	private Long id;
	 
	 private String gamerImageName;
	 
	 private String gamerOriImgName;
	 
	 private String gamerImageUrl;
	 
	 private static ModelMapper modelMapper = new ModelMapper();
	 
	 public static GamerImageDto of(GamerImage gamerImage) {
		 return modelMapper.map(gamerImage, GamerImageDto.class);
	 }
}
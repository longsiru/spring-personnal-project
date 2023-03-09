package com.game.dto;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.game.entity.Gamer;
import com.game.entity.Team;

import lombok.*;

@Getter
@Setter
public class GamerFormDto {
	 private Long id;
	 
	 @NotBlank(message = "Must enter the gamer name.")
	 private String gamerName;
	 
	 @NotBlank(message = "Must enter the gamer email.")
	 private String gamerEmail;
	 
	 @NotBlank(message = "Must enter the gamer age.")
	 private String gamerAge;
	 
	 @NotBlank(message = "Must enter the number of gamer gender.")
	 private String gamerGender;
	 
	 @NotBlank(message = "Must enter the number of gamer route.")
	 private String gamerRoute;
	 
	 @NotBlank(message = "Must enter the gamer introduce.")
	 private String gamerIntro;
	 
	 private List<GamerImageDto> gamerImageDtoList = new ArrayList<>(); //상품이미지 정보를 저장하는 리스트
	 private List<Long> gamerImageIds = new ArrayList<>(); //상품의 이미지 아이디를 저장 -> 수정시에 이미지를 담아 둘 용도.
	 
	 
	 private static ModelMapper modelMapper = new ModelMapper();
	 
	 public Gamer createGamer() {
		return modelMapper.map(this, Gamer.class);
		 
	 }
	 
	 public static GamerFormDto of(Gamer gamer) {
		return modelMapper.map(gamer, GamerFormDto.class);
		 
	 }
	 
}

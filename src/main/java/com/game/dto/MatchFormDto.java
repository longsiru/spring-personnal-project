package com.game.dto;

import org.modelmapper.ModelMapper;

import com.game.entity.Contest;
import com.game.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchFormDto {
	private Long id;
	
	private String contestName;
	
	private String contestDate;
	
	private String contestIntro;
	
	private MatchSelectDto matchSelectDto;
	
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Contest createMatch() {
		return modelMapper.map(this, Contest.class);
		 
	 }
	
	 public static MatchFormDto of(Contest contest) {
			return modelMapper.map(contest, MatchFormDto.class);
			 
		 }
}

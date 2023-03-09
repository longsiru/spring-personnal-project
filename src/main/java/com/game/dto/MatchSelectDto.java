package com.game.dto;

import org.modelmapper.ModelMapper;

import com.game.entity.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchSelectDto {

	private Long id;
	
	private String teamName;
	
	private String teamEmail;
	
	private String teamWin;
	
	private String teamRank;
	
	private String teamImageUrl;
	
	private String matchGamer;
	
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MatchSelectDto of(Team team) {
		MatchSelectDto matchSelectDto = modelMapper.map(team, MatchSelectDto.class);
		return matchSelectDto;
	}
}

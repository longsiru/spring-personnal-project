package com.game.dto;

import java.util.ArrayList;
import java.util.List;

//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.game.entity.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamFormDto {
	 private Long id;
	 
	 @NotBlank(message = "Must enter the team name.")
	 private String teamName;
	 
	 @NotBlank(message = "Must enter the team email.")
	 private String teamEmail;
	 
	 @NotBlank(message = "Must enter the team rank.")
	 private String teamRank;
	 
	 @NotBlank(message = "Must enter the number of team win.")
	 private String teamWin;
	 
	 @NotBlank(message = "Must enter the number of team lose.")
	 private String teamLose;
	 
	 @NotBlank(message = "Must enter the team introduce.")
	 private String teamIntro;
	 
	 
	 
//	 private TeamImageDto teamImageDto; //상품이미지 정보를 저장하는 리스트
	 
	 private List<TeamImageDto> teamImageDtoList = new ArrayList<>();
	 private List<Long> teamImageIds = new ArrayList<>(); //상품의 이미지 아이디를 저장 -> 수정시에 이미지를 담아 둘 용도.
	 
	 
	 private static ModelMapper modelMapper = new ModelMapper();
	 
	 public Team createTeam() {
		return modelMapper.map(this, Team.class);
		 
	 }
	 
	 public static TeamFormDto of(Team team) {
		return modelMapper.map(team, TeamFormDto.class);
		 
	 }
	 
}

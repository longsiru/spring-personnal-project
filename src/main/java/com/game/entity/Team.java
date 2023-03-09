package com.game.entity;

import javax.persistence.*;

import com.game.dto.TeamFormDto;

import lombok.*;

@Entity
@Table(name="team")
@Getter
@Setter
@ToString
public class Team {
	@Id
	@Column(name="team_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable = false, length = 50)
	private String teamName;
	
	
	@Column(nullable = false, length = 50)
	private String teamEmail;
	
	@Column(nullable = false, length = 20)
	private String teamRank;
	
	
	@Column(nullable = false, length = 20)
	private String teamWin;
	
	
	@Column(nullable = false, length = 20)
	private String teamLose;
	
	
	@Lob
	@Column(nullable = false)
	private String teamIntro;
	
	public void updateTeam(TeamFormDto teamFormDto) {
		this.teamName = teamFormDto.getTeamName();
		this.teamEmail = teamFormDto.getTeamEmail();
		this.teamRank = teamFormDto.getTeamRank();
		this.teamWin = teamFormDto.getTeamWin();
		this.teamLose = teamFormDto.getTeamLose();
		this.teamIntro = teamFormDto.getTeamIntro();
	}
}

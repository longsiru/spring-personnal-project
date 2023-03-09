package com.game.entity;

import javax.persistence.*;

import com.game.dto.GamerFormDto;

import lombok.*;

@Entity
@Table(name="gamer")
@Getter
@Setter
@ToString
public class Gamer {
	@Id
	@Column(name="gamer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable = false, length = 50)
	private String gamerName;
	
	
	@Column(nullable = false, length = 50)
	private String gamerEmail;
	
	@Column(nullable = false, length = 20)
	private String gamerAge;
	
	
	@Column(nullable = false, length = 20)
	private String gamerGender;
	
	
	@Column(nullable = false, length = 20)
	private String gamerRoute;
	
	
	@Lob
	@Column(nullable = false)
	private String gamerIntro;
	
	
	public void updateGamer(GamerFormDto gamerFormDto) {
		this.gamerName = gamerFormDto.getGamerName();
		this.gamerEmail = gamerFormDto.getGamerEmail();
		this.gamerAge = gamerFormDto.getGamerAge();
		this.gamerGender = gamerFormDto.getGamerGender();
		this.gamerRoute = gamerFormDto.getGamerRoute();
		this.gamerIntro = gamerFormDto.getGamerIntro();
	}
	
	
	
}

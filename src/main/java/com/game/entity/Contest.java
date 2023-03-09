package com.game.entity;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="contest")
@Getter
@Setter
@ToString
public class Contest extends BaseEntity{
	@Id
	@Column(name="contest_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String contestName;
	
	
	private LocalDate contestDate;
	
	@Lob
	@Column(nullable = false)
	private String contestIntro;
	
}

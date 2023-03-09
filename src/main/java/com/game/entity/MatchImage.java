package com.game.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "match_images")
@Getter
@Setter
@ToString
public class MatchImage {
	@Id
	@Column(name = "match_image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String matchImageName;

	private String matchOriImgName;// 원본 이미지 파일명

	private String matchImageUrl;// 이미지 저회 경로

	private String matchRepimgYn;// 대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contest_id")
	private Contest contest;

	public void updateMatchImage(String matchOriImgName, String matchImageName, String matchImageUrl) {
		this.matchOriImgName = matchOriImgName;
		this.matchImageName = matchImageName;
		this.matchImageUrl = matchImageUrl;
		
	}
}

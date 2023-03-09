package com.game.entity;

import javax.persistence.*;



import lombok.*;

@Entity
@Table(name="team_images")
@Getter
@Setter
@ToString
public class TeamImage extends BaseEntity{
	@Id
	@Column(name = "team_image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String teamImageName; //이미지 파일명
	
	private String teamOriImgName;//원본 이미지 파일명
	
	private String teamImageUrl;//이미지 저회 경로
	
	private String teamRepimgYn;//대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	
	//원본이미지 파일명, 업데으트할 이미지, 이미지 경로를 파라메터로 받아서 이미지  정보를 업데이트 하는 메소드
		public void updateTeamImage(String teamOriImgName, String teamImageName, String teamImageUrl) {
			this.teamOriImgName = teamOriImgName;
			this.teamImageName = teamImageName;
			this.teamImageUrl = teamImageUrl;
		}
	
}

package com.game.entity;

import javax.persistence.*;



import lombok.*;

@Entity
@Table(name="gamer_images")
@Getter
@Setter
@ToString
public class GamerImage extends BaseEntity{
	@Id
	@Column(name = "gamer_image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String gamerImageName; //이미지 파일명
	
	private String gamerOriImgName;//원본 이미지 파일명
	
	private String gamerImageUrl;//이미지 저회 경로
	
	private String gamerRepimgYn;//대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gamer_id")
	private Gamer gamer;
	
	//원본이미지 파일명, 업데으트할 이미지, 이미지 경로를 파라메터로 받아서 이미지  정보를 업데이트 하는 메소드
		public void updateGamerImage(String gamerOriImgName, String gamerImageName, String gamerImageUrl) {
			this.gamerOriImgName = gamerOriImgName;
			this.gamerImageName = gamerImageName;
			this.gamerImageUrl = gamerImageUrl;
		}
	
}

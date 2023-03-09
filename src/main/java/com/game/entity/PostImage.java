package com.game.entity;

import javax.persistence.*;



import lombok.*;

@Entity
@Table(name="post_images")
@Getter
@Setter
@ToString
public class PostImage extends BaseEntity{
	@Id
	@Column(name = "post_image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String postImageName; //이미지 파일명
	
	private String postOriImgName;//원본 이미지 파일명
	
	private String postImageUrl;//이미지 저회 경로
	
	private String postRepimgYn;//대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	//원본이미지 파일명, 업데으트할 이미지, 이미지 경로를 파라메터로 받아서 이미지  정보를 업데이트 하는 메소드
		public void updatePostImage(String postOriImgName, String postImageName, String postImageUrl) {
			this.postOriImgName = postOriImgName;
			this.postImageName = postImageName;
			this.postImageUrl = postImageUrl;
		}
	
}

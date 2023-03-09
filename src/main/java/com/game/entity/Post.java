package com.game.entity;




import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="post")
@Getter
@Setter
@ToString
public class Post extends BaseEntity{
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable = false, length = 100)
	private String postTitle;
	
	
	@Lob
	@Column(nullable = false)
	private String postText;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private Users users;
}

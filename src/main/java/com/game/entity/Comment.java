package com.game.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="comment")
@Getter
@Setter
@ToString
public class Comment extends BaseEntity{
	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "users_id")
	private Users users;
}

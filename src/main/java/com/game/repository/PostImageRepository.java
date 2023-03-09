package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.game.entity.PostImage;
public interface PostImageRepository extends JpaRepository<PostImage, Long> {
	
	List<PostImage> findByPostIdOrderByIdAsc(Long postId);
	
	//PostImage findByPostIdAndPostRepimgYn(Long postId, String postRepimgYn);
	
	
	//PostImage findByPostId(Long PostId);
}

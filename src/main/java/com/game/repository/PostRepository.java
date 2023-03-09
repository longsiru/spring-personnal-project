package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.game.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post>, PostRepositoryCustom{
	
	List<Post> findByPostTitle(String PostTitle);

}

package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.game.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByPostIdOrderByIdAsc(Long postId);
}

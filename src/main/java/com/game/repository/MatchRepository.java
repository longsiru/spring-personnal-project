package com.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.game.entity.Contest;



public interface MatchRepository extends JpaRepository<Contest, Long>, QuerydslPredicateExecutor<Contest>{

}

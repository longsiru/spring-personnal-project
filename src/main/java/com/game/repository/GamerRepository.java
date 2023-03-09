package com.game.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.game.entity.Gamer;

public interface GamerRepository extends JpaRepository<Gamer, Long>, QuerydslPredicateExecutor<Gamer>, GamerRepositoryCustom{
	List<Gamer> findByGamerName(String GamerName);

	List<Gamer> findByGamerNameOrGamerIntro(String GamerName, String GamerIntro);

	

	
	


}

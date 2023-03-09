package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.game.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>, QuerydslPredicateExecutor<Team>, TeamRepositoryCustom{
	List<Team> findByTeamName(String teamName);

	List<Team> findByTeamNameOrTeamIntro(String teamName, String teamIntro);

}

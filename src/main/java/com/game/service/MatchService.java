package com.game.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.dto.MatchSelectDto;
import com.game.entity.Team;
import com.game.repository.TeamRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {
	private final TeamRepository teamRepository;

	public List<MatchSelectDto> getMatchSelect() {
		List<Team> teams = teamRepository.findAll();
		List<MatchSelectDto> matchSelectDtos = new ArrayList<>();
		for(Team team : teams) {
			MatchSelectDto matchSelectDto = MatchSelectDto.of(team);
			matchSelectDtos.add(matchSelectDto);
		}
		return matchSelectDtos;
	}

}

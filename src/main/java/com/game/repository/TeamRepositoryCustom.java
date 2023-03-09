package com.game.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.game.dto.TeamDto;
import com.game.entity.Team;

public interface TeamRepositoryCustom {
	Page<Team> getAdminTeamPage(Pageable pageable);
	Page<TeamDto> getTeamPage(Pageable pageable);
}

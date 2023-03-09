package com.game.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.game.dto.GamerDto;
import com.game.dto.TeamDto;
import com.game.entity.Gamer;
import com.game.entity.Team;

public interface GamerRepositoryCustom {
	Page<Gamer> getAdminGamerPage(Pageable pageable);
	Page<GamerDto> getGamerPage(Pageable pageable);
}

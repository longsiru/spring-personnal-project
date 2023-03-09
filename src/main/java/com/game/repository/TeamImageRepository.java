package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.TeamImage;
public interface TeamImageRepository extends JpaRepository<TeamImage, Long> {
	List<TeamImage> findByTeamIdOrderByIdAsc(Long teamId);
	
	//상품의 대표 이미지를 찾음
	TeamImage findByTeamIdAndTeamRepimgYn(Long teamId, String teamRepimgYn);
	
}

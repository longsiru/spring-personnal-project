package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.GamerImage;
import com.game.entity.TeamImage;


public interface GamerImageRepository extends JpaRepository<GamerImage, Long> {
	List<GamerImage> findByGamerIdOrderByIdAsc(Long gamerId);
	
	//상품의 대표 이미지를 찾음
	GamerImage findByGamerIdAndGamerRepimgYn(Long gamerId, String gamerRepimgYn);
	
	GamerImage findByGamerId(Long gamerId);
	
}

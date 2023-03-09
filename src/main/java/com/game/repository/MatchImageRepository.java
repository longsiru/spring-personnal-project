package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.MatchImage;
public interface MatchImageRepository extends JpaRepository<MatchImage, Long>{

List<MatchImage> findByContestIdOrderByIdAsc(Long contestId);
	
	//상품의 대표 이미지를 찾음
MatchImage findByContestIdAndMatchRepimgYn(Long contestId, String matchRepimgYn);
}

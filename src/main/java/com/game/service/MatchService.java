package com.game.service;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.game.dto.MatchFormDto;
import com.game.dto.MatchSelectDto;
import com.game.entity.Contest;
import com.game.entity.MatchImage;
import com.game.entity.Team;
import com.game.repository.MatchRepository;
import com.game.repository.TeamRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {
	private final TeamRepository teamRepository;
	private final MatchRepository matchRepository;
	private final MatchService matchService;
	private final MatchImgService matchImgService;
	
	public List<MatchSelectDto> getMatchSelect() {
		List<Team> teams = teamRepository.findAll();
		List<MatchSelectDto> matchSelectDtos = new ArrayList<>();
		for(Team team : teams) {
			MatchSelectDto matchSelectDto = MatchSelectDto.of(team);
			matchSelectDtos.add(matchSelectDto);
		}
		return matchSelectDtos;
	}

	public Long SaveMatch(@Valid MatchFormDto matchFormDto, MultipartFile matchImgFile) throws Exception {
		Contest contest = matchFormDto.createMatch();
		
		matchRepository.save(contest);
		
		MatchImage matchImage = new MatchImage();
		matchImage.setContest(contest);
		matchImage.setMatchRepimgYn("Y");
		matchImgService.saveMatchImage(matchImage, matchImgFile);
		
		return contest.getId();		
	}
	
	

}

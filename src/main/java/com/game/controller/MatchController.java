package com.game.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.game.dto.MatchFormDto;
import com.game.service.MatchService;
import com.game.service.TeamService;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchController {
	private final MatchService matchService;
	
	@GetMapping(value= "/admin/match/new")
	public String matchForm(Model model) {
		model.addAttribute("teams", matchService.getMatchSelect());
		model.addAttribute("matchFormDto", new MatchFormDto());
		return "match/matchForm";
		
	}
	
	
	@GetMapping(value= "/match")
	public String match() {
		
		return "match/matchPage";
		
	}
	
	@GetMapping(value= "/match/detail")
	public String matchDetail() {
		
		return "match/match-detail";
		
	}
}

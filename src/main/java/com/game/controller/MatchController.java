package com.game.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.game.dto.MatchFormDto;
import com.game.service.MatchService;
import com.game.service.TeamService;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchController {
	private final MatchService matchService;
	
	//add page
	@GetMapping(value= "/admin/match/new")
	public String matchForm(Model model) {
		model.addAttribute("teams", matchService.getMatchSelect());
		model.addAttribute("matchFormDto", new MatchFormDto());
		return "match/matchForm";
		
	}
	
	
	//add match
	@GetMapping(value= "/match")
	public String matchView(@Valid MatchFormDto matchFormDto, BindingResult bindingResult, 
			Model model, @RequestParam("matchImgFile") MultipartFile matchImgFile) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(e -> {
				System.out.println(e);
			});
			return "match/matchForm";
		}
		
		if(matchImgFile.isEmpty() && matchFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값 입니다.");
			return "match/matchForm";
		}
		
		try {
			matchService.SaveMatch(matchFormDto, matchImgFile);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "An error occurred while adding a gamer.");
			return "match/matchForm";
		}
		
		return "redirect:/match";
		
	}
	
	@GetMapping(value= "/match/detail")
	public String matchDetail() {
		
		return "match/match-detail";
		
	}
}

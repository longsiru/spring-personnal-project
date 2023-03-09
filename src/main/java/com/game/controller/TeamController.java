package com.game.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.game.dto.TeamDto;
import com.game.dto.TeamFormDto;
import com.game.dto.TeamImageDto;
import com.game.service.TeamService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TeamController {
	private final TeamService teamService;
	
	//show the team add page
	@GetMapping(value = "/admin/team/new")
	public String teamForm(Model model) {
		model.addAttribute("teamFormDto", new TeamFormDto());
//		TeamFormDto tfd = new TeamFormDto();
//		if(tfd.getTeamImageDto() != null ) {
//			System.out.println(tfd.getTeamImageDto().getTeamOriImgName());
//		} 
//		System.out.println("1111" + tfd.toString());
		return "team/teamForm";
		
	}
	
	//add team
	@PostMapping(value = "/admin/team/new")
	public String teamNew(@Valid TeamFormDto teamFormDto, BindingResult bindingResult, 
			Model model, @RequestParam("teamImageFile") MultipartFile teamImageFile) {

		System.out.println("teamNew");
		if(bindingResult.hasErrors()) {
			return "team/teamForm";
		}
		System.out.println("teamNew2");
		if(teamImageFile.isEmpty() && teamFormDto.getId() == null) {
			model.addAttribute("errorMessage", "이미지는 필수 입력 값 입니다.");
			return "team/teamForm";
		}
		System.out.println("teamNew3");
		try {
			teamService.SaveTeam(teamFormDto, teamImageFile);
			System.out.println("teamNew4");
		} catch (Exception e) {
			System.out.println("teamNew5");
			model.addAttribute("errorMessage", "An error occurred while adding a team.");
			return "team/teamForm";
			
		}
		System.out.println("teamNew6");
		return "redirect:/";
		
	}
	
	// modify page view
	@GetMapping(value = "/admin/team/{teamId}")
	public String teamDetail(@PathVariable("teamId") Long teamId, Model model) {
		try {
			TeamFormDto teamFormDto = teamService.getTeamDetail(teamId);
			model.addAttribute(teamFormDto);
		} catch (EntityNotFoundException e) {
			model.addAttribute("errorMessage","This team that dose not exist." );
			model.addAttribute("itemFormDto", new TeamFormDto());
			return "team/teamForm";
		}
		return "team/teamForm";
	}
	
	//수정
	@PostMapping(value = "/admin/team/{teamId}")
	public String teamUpdate(@Valid TeamFormDto teamFormDto,  BindingResult bindingResult, 
			Model model, @RequestParam("teamImageFile") List<MultipartFile> teamImgFileList) {
		if(bindingResult.hasErrors()) {
			System.out.println("teamUpdate - bindingResult.hasErrors()");
			return "team/teamForm";
		}		
		
		if(teamImgFileList.get(0).isEmpty() && teamFormDto.getId() == null) {
			System.out.println("teamUpdate - teamImgFileList.get(0).isEmpty()");
			model.addAttribute("errorMessage", "Must enter team image.");
			return "team/teamForm";
		}
		
		try {
			teamService.updateTeam(teamFormDto, teamImgFileList);
		} catch (Exception e) {
			System.out.println("teamUpdate - Exception e");
			e.printStackTrace();
			model.addAttribute("errorMessage", "An error occurred while modifying.");
			return "team/teamForm";
		}
		return "redirect:/";
		
	}
	
	
	
	@GetMapping(value= "/team")
	public String team(Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<TeamDto> teams = teamService.getTeamDtoPage(pageable);
		
		model.addAttribute("teams",teams);
		model.addAttribute("maxPage", 5);
		
		return "team/team";
		
	}
	
	
	//team detail
	@GetMapping(value= "/team/{teamId}")
	public String teamDetail(Model model, @PathVariable("teamId") Long teamId) {
		TeamFormDto teamFormDto = teamService.getTeamDetail(teamId);
		
		List<TeamImageDto> c  = teamFormDto.getTeamImageDtoList();
		for(TeamImageDto r : c) {
			System.out.println("ccc:" + r.getTeamImageUrl());
		}
		model.addAttribute("team", teamFormDto);
		return "team/team-detail";
		
	}
	
	
	
	
	
}

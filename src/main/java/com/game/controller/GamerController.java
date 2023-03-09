package com.game.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.game.dto.GamerDto;
import com.game.dto.GamerFormDto;
import com.game.dto.GamerImageDto;
import com.game.service.GamerImageService;
import com.game.service.GamerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GamerController {
	private final GamerService gamerService;
	private final GamerImageService gamerImageService;
	
	//show the gamer add page
	@GetMapping(value = "/admin/gamer/new")
	public String gamerForm(Model model) {
		model.addAttribute("gamerFormDto", new GamerFormDto());
		return "gamer/gamerForm";
		
	}
	
	//add gamer
	@PostMapping(value = "/admin/gamer/new")
	public String gamerNew(@Valid GamerFormDto gamerFormDto, BindingResult bindingResult, 
			Model model, @RequestParam("gamerImageFile") MultipartFile gamerImageFile) {
		System.out.println("gamerNew");
		if(bindingResult.hasErrors()) {
			return "gamer/gamerForm";
		}
		System.out.println("gamerNew2");
		if(gamerImageFile.isEmpty() && gamerFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값 입니다.");
			return "gamer/gamerForm";
		}
		System.out.println("gamerNew3");
		try {
			gamerService.SaveGamer(gamerFormDto, gamerImageFile);
			System.out.println("gamerNew4");
		} catch (Exception e) {
			System.out.println("gamerNew5");
			model.addAttribute("errorMessage", "An error occurred while adding a gamer.");
			return "gamer/gamerForm";
		}
		return "redirect:/";
		
	}
	
	
	@GetMapping(value= "/gamer")
	public String gamer(Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<GamerDto> gamers = gamerService.getGamerDtoPage(pageable);
		
		model.addAttribute("gamers",gamers);
		model.addAttribute("maxPage", 5);
		return "gamer/gamer";
	}
	
	
	//gamer detail
	@GetMapping(value= "/gamer/{gamerId}")
	public String gamerDetail(Model model, @PathVariable("gamerId") Long gamerId) {
		GamerFormDto gamerFormDto = gamerService.getGamerDetail(gamerId);
		List<GamerImageDto> gamerImageDtos = gamerFormDto.getGamerImageDtoList();
		for(GamerImageDto g : gamerImageDtos) {
			System.out.println("ccc:" + g.getGamerImageUrl());
		}
		model.addAttribute("gamer", gamerFormDto);
		return "gamer/gamer-detail";
		
	}
	
	//gamer modify view
	
	@GetMapping(value = "/admin/gamer/{gamerId}")
	public String gamerDetail(@PathVariable("gamerId") Long gamerId, Model model) {
		try {
			GamerFormDto gamerFormDto = gamerService.getGamerDetail(gamerId);
			model.addAttribute(gamerFormDto);
		} catch (EntityNotFoundException e) {
			model.addAttribute("errorMessage","This gamer that dose not exist." );
			model.addAttribute("gamerFormDto", new GamerFormDto());
			return "gamer/gamerForm";
		}
		
		return "gamer/gamerForm";
	}
	
	//gamer modify
	@PostMapping(value = "/admin/gamer/{gamerId}")
	public String gamerUpdate(@Valid GamerFormDto gamerFormDto, BindingResult bindingResult, 
			Model model, @RequestParam("gamerImageFile") List<MultipartFile> gamerImageFileList) {
		
		if(bindingResult.hasErrors()) {
			return "gamer/gamerForm";
		}
		
		if(gamerImageFileList.get(0).isEmpty() && gamerFormDto.getId() == null) {
			model.addAttribute("errorMessage", "Must enter gamer image.");
			return "gamer/gamerForm";
		}
		
		try {
			gamerService.updateGamer(gamerFormDto, gamerImageFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "An error occurred while modifying.");
			return "gamer/gamerForm";
		}
				return "redirect:/";
		}
	
	
	//delete
	@DeleteMapping("/admin/gamer/{gamerId}/delete")
	public @ResponseBody ResponseEntity deleteGamer(@PathVariable("gamerId") Long gamerId, Model model) {
		try {
//			GamerFormDto gamerFormDto = gamerService.getGamerDetail(gamerId);
			gamerImageService.deleteGamerImage(gamerId);
			gamerService.deleteGamer(gamerId);
			
//			model.addAttribute(gamerFormDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Long>(gamerId, HttpStatus.OK);
	}
}

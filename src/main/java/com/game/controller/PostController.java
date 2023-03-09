package com.game.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.game.dto.CommentDto;
import com.game.dto.GamerDto;
import com.game.dto.PostDto;
import com.game.dto.PostFormDto;
import com.game.dto.PostImageDto;
import com.game.entity.Users;
import com.game.repository.UsersRepository;
import com.game.service.PostImageService;
import com.game.service.PostService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PostController {
	private final PostImageService postImageService;
	private final PostService postService;
	private final UsersRepository usersRepository;
	
	
	//show the post add page
	@GetMapping(value= "/post/createpost")
	public String postForm(Model model) {
		model.addAttribute("postFormDto", new PostFormDto());
		return "post/create-post";
		
	}
	
	//add post
	@PostMapping(value = "/post/createpost")
	public String postCreate(@Valid PostFormDto postFormDto, BindingResult bindingResult, 
			Model model, @RequestParam("postImageFile") MultipartFile postImageFile) {
		System.out.println("postNew");
		if(bindingResult.hasErrors()) {
			return "post/create-post";
		}
		System.out.println("postNew2");
		if(postImageFile.isEmpty() && postFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값 입니다.");
			return "post/create-post";
		}
		System.out.println("postNew3");
		try {
			postService.SavePost(postFormDto, postImageFile);
			System.out.println("postNew4");
		} catch (Exception e) {
			System.out.println("postNew5");
			model.addAttribute("errorMessage", "An error occurred while adding a gamer.");
			return "post/create-post";
		}
		return "redirect:/post";
		
	}
	
	
	@GetMapping(value= "/post")
	public String post(Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<PostDto> posts = postService.getPostDtoPage(pageable);
		
		model.addAttribute("posts",posts);
		model.addAttribute("maxPage", 5);
		return "post/post";
		
	}
	
	
	//post detail
	@GetMapping(value= "/post/{postId}")
	public String postDetail(Model model, @PathVariable("postId") Long postId) {
		PostFormDto postFormDto = postService.getPostDetail(postId);
		List<PostImageDto> postImageDtos = postFormDto.getPostImageDtoList();
		List<CommentDto> commentDtoList = postService.getCommentList(postId);
		
		for(PostImageDto p : postImageDtos) {
			System.out.println("ccc:" + p.getPostImageUrl());
		}
		model.addAttribute("post",postFormDto);
		model.addAttribute("commentDtoList", commentDtoList);
		return "post/post-detail";
		
	}
	
//	private final CommentService commentService;
//	
//	@PostMapping(value = "/post/{postId}/comment")
//	public @ResponseBody ResponseEntity commentCreate(@PathVariable("postId") CommentFormDto postId ,
//			Model model){
//		try {
//			commentService.SaveComment(postId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<CommentFormDto>(postId, HttpStatus.OK);
//		
//	}
	
	
	//comment
	@PostMapping(value= "/post/{postId}/comment")
	public String insertComment(@PathVariable("postId") Long id, @RequestParam String content,Principal principal, Model model) {  //Principal principal,
		String email = principal.getName();
		Users users = usersRepository.findByEmail(email);
		Long cusers = users.getId();
	
		postService.insertComment(cusers, id, content);
		return "redirect:/post/" + id;
		
	}
	
	//comment delete
	@DeleteMapping("/post/{id}/delete")
	public @ResponseBody ResponseEntity<?> deleteComment(@PathVariable ("id") Long id,  Model model){
		
		postService.deleteComment(id);
		return new ResponseEntity<Long> (id, HttpStatus.OK);
		
	}
	
	
}

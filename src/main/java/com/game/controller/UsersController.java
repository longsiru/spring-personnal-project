package com.game.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.dto.UsersLoginFormDto;
import com.game.entity.Users;
import com.game.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
public class UsersController {
	private final UsersService usersService;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 화면 
	@GetMapping(value= "/signup")
	public String usersSignUpForm(Model model) {
		model.addAttribute("usersLoginFormDto", new UsersLoginFormDto());
		return "user/userSignUpForm";
		
	}
	
	//회원가입 버튼을 눌렀을때 실행되는 메소드
	@PostMapping(value = "/signup")
	public String usersSignUpForm(@Valid UsersLoginFormDto usersLoginFormDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "user/userSignUpForm";
		}
		
		try {
			Users users = Users.createUser(usersLoginFormDto, passwordEncoder);
			usersService.saveUsers(users);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/userSignUpForm";
		}
		
		
		return "redirect:/";
		
	}
	
	//로그인 화면
	@GetMapping(value= "/login")
	public String usersLoginForm() {	
		return "user/userLoginForm";	
	}
	
	private final SessionManager sessionManager;
	
	//쿠키, 세션 테스트
	@PostMapping(value = "/login2")
	public String loginMember2(HttpServletResponse response, HttpSession session, @RequestParam String email) {
		System.out.println("email: " + email);
		Cookie idCookie = new Cookie("userCookieId2", email);
		response.addCookie(idCookie);
		
		session.setAttribute("useSessionId2", email);
		
		sessionManager.createSession(email, response);
		
		return "member/memberLoginForm";
	}
	
	
	//로그인을 실패했을때
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "Please Check Your Password!");
		return "user/userLoginForm";
		
	}
}

package com.game.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.*;

@Getter
@Setter
public class UsersLoginFormDto {
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요")
	private String email;
	
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16, message= "비밀번호는 8자이상, 16자 이하으로 입력하세요")
	private String password;
}
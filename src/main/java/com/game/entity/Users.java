package com.game.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.game.contant.Role;
import com.game.dto.UsersLoginFormDto;

import lombok.*;

@Entity
@Table(name="users")  //table명
@Getter
@Setter
@ToString
public class Users {
	@Id
	@Column(name="users_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String Password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Users createUser(UsersLoginFormDto usersLoginFormDto, PasswordEncoder passwordEncoder) {
		Users users = new Users();
		users.setName(usersLoginFormDto.getName());
		users.setEmail(usersLoginFormDto.getEmail());
		users.setPassword(usersLoginFormDto.getPassword());
		
		
		String password = passwordEncoder.encode(usersLoginFormDto.getPassword());
		users.setPassword(password);
		users.setRole(Role.USER);
		
		return users;
		
	}

}

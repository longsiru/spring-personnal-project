package com.game.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.entity.Users;
import com.game.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service  
@Transactional
@RequiredArgsConstructor
public class UsersService implements UserDetailsService{
	private final UsersRepository usersRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users users = usersRepository.findByEmail(email);
		
		if(users == null) {
			throw new UsernameNotFoundException(email);
		}
		return User.builder()
				.username(users.getEmail())
				.password(users.getPassword())
				.roles(users.getRole().toString())
				.build();
	}
	
	public Users saveUsers(Users users) {
		validateDuplicateUsers(users);
		return usersRepository.save(users);
	}

	
	//email 중복체크 메소드
	private void validateDuplicateUsers(Users users) {
		Users findUser = usersRepository.findByEmail(users.getEmail());
		
		if(findUser != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

}

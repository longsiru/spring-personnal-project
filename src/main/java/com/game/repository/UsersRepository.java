package com.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.entity.Users;




public interface UsersRepository extends JpaRepository<Users, Long> {
	 Users findByEmail(String email);//회원가입시 중복 회원 검사하기 위해
}

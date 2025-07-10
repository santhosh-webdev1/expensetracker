package com.example.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String username);

}

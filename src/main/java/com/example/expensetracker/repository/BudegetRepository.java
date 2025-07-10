package com.example.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.Entity.Budget;

public interface BudegetRepository extends JpaRepository<Budget, Integer>{
	
	

}

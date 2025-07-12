package com.example.expensetracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.Entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	
	List<Expense> findByUserIdAndCategory(int UserId, String category);
	
	List<Expense> findByUserId(int userId);
	
	List<Expense> findByUserIdAndDateBetween(int userId, LocalDate start, LocalDate end);

	
}

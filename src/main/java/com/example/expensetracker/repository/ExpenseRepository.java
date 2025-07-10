package com.example.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.Entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	
	List<Expense> findByCategory(String category);
	
	
}

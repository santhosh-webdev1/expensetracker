package com.example.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.Entity.Budget;
import com.example.expensetracker.Enum.MonthEnum;

public interface BudegetRepository extends JpaRepository<Budget, Integer>{
	
	Optional<Budget> findByUserIdAndMonthAndYear(int userId, MonthEnum month, int year);

	Optional<Budget> findById(int BudgetId);
	
	
	
}

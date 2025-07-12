package com.example.expensetracker.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.expensetracker.DTO.BudgetDto;
import com.example.expensetracker.DTO.CurrentMonthSummaryDto;
import com.example.expensetracker.Entity.Budget;
import com.example.expensetracker.Entity.Expense;
import com.example.expensetracker.Entity.ResponseStructure;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.MonthEnum;
import com.example.expensetracker.repository.BudegetRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;

@Service
public class BudgetServices {
	
	@Autowired
	BudegetRepository budgetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	public ResponseStructure<Budget> addbudget(BudgetDto budgetDto){
		
		Optional<User> optionalUser = userRepository.findById(budgetDto.getUserId());
		
		ResponseStructure<Budget> response = new ResponseStructure<>();
		
		if (optionalUser.isEmpty()) {
	        response.setData(null);
	        response.setMessage("User not found");
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
		    return response;
		}
		
		Budget budget = new Budget();
		budget.setAmount(budgetDto.getAmount());
		budget.setMonth(budgetDto.getMonth());
		budget.setYear(budgetDto.getYear());
		budget.setTotalSpent(0.0);
		// saves on user's id
		budget.setUser(optionalUser.get());
		
		budgetRepository.save(budget);
		
		
		
		response.setData(budget);
		response.setMessage("Budget created successfully");
		response.setStatusCode(HttpStatus.CREATED.value());
		
		return response;
	}
	
	
	public ResponseStructure<Budget> UpdateBudget(BudgetDto budgetDto){
		
		Optional<Budget> optionalBudget = budgetRepository.findById(budgetDto.getBudgetId());
		
		ResponseStructure<Budget> response = new ResponseStructure<>();
		
		if (optionalBudget.isEmpty()) {
	        response.setData(null);
	        response.setMessage("Budget not found");
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        return response;
	    }
		
		Budget budget = optionalBudget.get();
		
		budget.setAmount(budgetDto.getAmount());
		budget.setMonth(budgetDto.getMonth());
		budget.setYear(budgetDto.getYear());
		
	    Budget updatedBudget = budgetRepository.save(budget);

	    response.setData(updatedBudget);
	    response.setMessage("Budget updated successfully");
	    response.setStatusCode(HttpStatus.OK.value());

	    return response;
	}
	
	
	public CurrentMonthSummaryDto getCurrentMonthSummary(int userId){
		
		MonthEnum currentMonth = MonthEnum.fromNumber(LocalDate.now().getMonthValue());
		int currentYear = LocalDate.now().getYear();
		
		Optional<Budget> optionalBudget = budgetRepository.findByUserIdAndMonthAndYear(userId, currentMonth, currentYear);
		
		LocalDate start = LocalDate.now().withDayOfMonth(1);
		LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
		
		List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(userId, start, end);
		
		CurrentMonthSummaryDto summary = new CurrentMonthSummaryDto();
		summary.setBudget(optionalBudget.get());
		summary.setExpense(expenses);
		
		return summary;
		
	}
	
	public Double getRemainingAmount(int userId) {
		
		MonthEnum currentMonth = MonthEnum.fromNumber(LocalDate.now().getMonthValue());
		int currentYear = LocalDate.now().getYear();
		
		Optional<Budget> optionalBudget = budgetRepository.findByUserIdAndMonthAndYear(userId, currentMonth, currentYear);
		
		LocalDate start = LocalDate.now().withDayOfMonth(1);
		LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
		
		List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(userId, start, end);
		
		Budget budget = optionalBudget.get();
		
		Double totalSpent = expenses.stream()
				.mapToDouble(Expense::getAmount)
				.sum();	
		
		Double remainingAmount = budget.getAmount() - totalSpent;
		
		return remainingAmount;
	}
	
	
	
}

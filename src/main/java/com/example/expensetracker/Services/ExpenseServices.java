package com.example.expensetracker.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.expensetracker.DTO.ExpenseDto;
import com.example.expensetracker.Entity.Expense;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;

public class ExpenseServices {

	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public String addExpense(int userid, ExpenseDto expenseDto) {
		
		Optional<User> user = userRepository.findById(userid);
		
		if(user.isEmpty()) {
			return "user is not found";
		}
		
		Expense expense = new Expense();
		
		expense.setAmount(expenseDto.getAmount());
		expense.setCategory(expenseDto.getCategory());
		expense.setDate(expenseDto.getDate());
		expense.setDescription(expenseDto.getDescription());
		
		expense.setUser(user.get());
		
		expenseRepository.save(expense);
		
		return "Expense Saved";
	}
}

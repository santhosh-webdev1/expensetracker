package com.example.expensetracker.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.expensetracker.DTO.ExpenseDto;
import com.example.expensetracker.Entity.Expense;
import com.example.expensetracker.Entity.ResponseStructure;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;

@Service
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
		
		// setting the foreign key 
		expense.setUser(user.get());
		
		expenseRepository.save(expense);
		
		return "Expense Saved";
	}
	
	public ResponseStructure<Expense> editExpense(int id, ExpenseDto updatedExpenseDto){
		
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		
		if(optionalExpense.isEmpty()) {
			
			ResponseStructure<Expense> response = new ResponseStructure<>();
			response.setMessage("Expense not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(null);
			return response;
		}
		
		// created reference for and existing expense object to override with a new one
		Expense expense = optionalExpense.get();
		
		expense.setAmount(updatedExpenseDto.getAmount());
		expense.setCategory(updatedExpenseDto.getCategory());
		expense.setDate(updatedExpenseDto.getDate());
		expense.setDescription(updatedExpenseDto.getDescription());
		
		//save the updated expense
		Expense updatedExpense = expenseRepository.save(expense);
		
		//prepare response
		ResponseStructure<Expense> response = new ResponseStructure<>();
		response.setData(updatedExpense);
		response.setMessage("Expense Updated Successfully");
		response.setStatusCode(HttpStatus.OK.value());
		
		return response;
		
	}
	
	
	public ResponseStructure<String> deleteExpense(int id){
		
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		
		if(optionalExpense.isEmpty()) {
			ResponseStructure<String> response = new ResponseStructure<>();
			response.setMessage("Expense not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(null);
			return response;
		}
		
		Expense expense = optionalExpense.get();
		
		expenseRepository.delete(expense);
		
		ResponseStructure<String> response = new ResponseStructure<>();
		
		response.setData("Deleted Id :" + id);
		response.setMessage("Expense deleted");
		response.setStatusCode(HttpStatus.OK.value());
		
		return response;
		
	}
	
	public ResponseStructure<Expense> getExpenseById(int id){
		
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		
		if(optionalExpense.isEmpty()) {
			ResponseStructure<Expense> response = new ResponseStructure<>();
			
			response.setData(null);
			response.setMessage("Expense not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			
			return response;
		}
		
		Expense expense = optionalExpense.get();
		
		ResponseStructure<Expense> response = new ResponseStructure<>();
		response.setData(expense);
		response.setMessage("Expense found");
		response.setStatusCode(HttpStatus.OK.value());;
		
		return response;
		
	}
	
	public ResponseStructure<List<Expense>> getAllExpenseByUser(int userId){
		Optional<User> optionalUser = userRepository.findById(userId);
		
		ResponseStructure<List<Expense>> response = new ResponseStructure<>();
		
		if(optionalUser.isEmpty()) {
			response.setData(null);
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("user not found");
			
			return response;
		}
		
		
		List<Expense> expenses = expenseRepository.findByUserId(userId);
		
		response.setData(expenses);
		response.setMessage("here's the all expenses from user");
		response.setStatusCode(HttpStatus.OK.value());
		
		return response;
	}
	
	
	public ResponseStructure<List<Expense>> getExpenseByCategory(int userId, String category){
		
		Optional<User> optionalUser = userRepository.findById(userId);
		
		ResponseStructure<List<Expense>> response = new ResponseStructure<>();
		
		if(optionalUser.isEmpty()) {
			response.setData(null);
			response.setMessage("User not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			
			return response;
		}
		
		List<Expense> expenses = expenseRepository.findByUserIdAndCategory(userId, category);
		
		response.setData(expenses);
		response.setMessage("Here's expenses from the category : "+ category);
		response.setStatusCode(HttpStatus.OK.value());
		
		return response;
		
	}
	
	
}














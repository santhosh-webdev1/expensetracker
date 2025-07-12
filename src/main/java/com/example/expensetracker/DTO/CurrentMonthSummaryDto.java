package com.example.expensetracker.DTO;

import java.util.List;

import com.example.expensetracker.Entity.Budget;
import com.example.expensetracker.Entity.Expense;

public class CurrentMonthSummaryDto {

	private Budget budget;
	
	private List<Expense> expense;
	
	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {	
		this.budget = budget;
	}

	public List<Expense> getExpense() {
		return expense;
	}

	public void setExpense(List<Expense> expense) {
		this.expense = expense;
	}


}

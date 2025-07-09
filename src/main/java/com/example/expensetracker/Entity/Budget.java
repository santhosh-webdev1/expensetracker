package com.example.expensetracker.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Budget")
public class Budget {
	
	@Id
	@Column(name="BudgetId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer BudgetId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private String month;
	
	
	
	
	public Integer getBudgetId() {
		return BudgetId;
	}

	public void setBudgetId(Integer budgetId) {
		BudgetId = budgetId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	
}

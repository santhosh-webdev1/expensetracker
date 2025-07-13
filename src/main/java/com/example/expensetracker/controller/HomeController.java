package com.example.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.expensetracker.DTO.CurrentMonthSummaryDto;
import com.example.expensetracker.Services.BudgetServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @Autowired
    private BudgetServices budgetServices;

    @GetMapping("/home")
    public String loadHomePage(HttpSession session, Model model){
        
        Integer userId = (Integer)session.getAttribute("userId");

        if(userId == null){
            return "redirect:/login";
        }

        try {
            CurrentMonthSummaryDto summary = budgetServices.getCurrentMonthSummary(userId);

            model.addAttribute("budget", summary.getBudget());
            model.addAttribute("expenses", summary.getExpense());
            Double remainingAmount = budgetServices.getRemainingAmount(userId);
            model.addAttribute("remainingAmount", remainingAmount);
        } catch (Exception e) {
            model.addAttribute("budget", null);
            model.addAttribute("expenses", null);
            model.addAttribute("remainingAmount", 0); 
        }

        model.addAttribute("userId", userId);

        return "home";
    }





}

package com.example.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.expensetracker.DTO.BudgetDto;
import com.example.expensetracker.Services.BudgetServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class BudgetController {

    @Autowired
    BudgetServices budgetServices;

    @PostMapping("/createBudget")
    public String createBudget(@ModelAttribute BudgetDto budgetDto, HttpSession session){
        
        Integer userId = (Integer)session.getAttribute("userId");

        if(userId == null){
            return "redirect:/login";
        }

        budgetDto.setUserId(userId);
        budgetServices.addbudget(budgetDto);

        return "redirect:/home";
    }

    @PostMapping("/deleteBudget")
    public String deleteBudget(HttpSession session, BudgetDto budgetDto, Model model) {
    Integer userId = (Integer) session.getAttribute("userId");

    boolean isDeleted = budgetServices.deleteBudget(userId, budgetDto);

    if (isDeleted) {
        model.addAttribute("message", "Budget deleted successfully!");
    }
    else {
        model.addAttribute("message", "Failed to delete budget.");
    }

    return "redirect:/home"; // or loadHomePage again if needed
    }

}

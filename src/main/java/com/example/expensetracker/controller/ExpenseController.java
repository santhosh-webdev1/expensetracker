package com.example.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.expensetracker.DTO.ExpenseDto;
import com.example.expensetracker.Services.ExpenseServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseController {

    @Autowired
    ExpenseServices expenseServices;

    // ADD THE EXPENSE TO THE USER
    @PostMapping("/addExpense")
    public String addExpense(@ModelAttribute ExpenseDto expenseDto, HttpSession session){
        
        Integer userId = (Integer)session.getAttribute("userId");
        
        if(userId == null){
            return "redirect:/login";
        }

        expenseDto.setUserId(userId);
        expenseServices.addExpense(userId, expenseDto);
        return "redirect:/home";
    }
}

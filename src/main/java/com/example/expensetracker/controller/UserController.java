package com.example.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.expensetracker.DTO.UserDto;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserServices userServices;

    // first page 
    @GetMapping("/")
    public String redirectToLogin(){
        return "loginpage";
    }

    //get request to open loginpage
    @GetMapping("/login")
    public String loginPage(){
        return "loginpage";
    }


    // method to check the user registered or not!
    @PostMapping("/loginUser")
    public String loginUser(@RequestParam String userName, @RequestParam String password, HttpSession session, Model model ){

        boolean isLoginValid = userServices.login(userName, password);

        if(isLoginValid){

            User user = userServices.getUserByUserName(userName);

            if(user != null){
                session.setAttribute("userId", user.getId());
                return "redirect:/home";
            }
            else{
                model.addAttribute("message", "user not found after login.");
                return "loginpage";
            }
        }
        else{
            model.addAttribute("message", "Invalid username or password");
            return "loginpage";
        }
    }



    
    //redirects to user
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    // add a new user 
    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto,HttpSession session, Model model){

        //password does not match
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            model.addAttribute("error", "password does not match");
            return "register";
        }


        //Registered or not
        User RegisteredUser = userServices.userRegistration(userDto);

        if(RegisteredUser != null){
            session.setAttribute("userId", RegisteredUser.getId());
            return "redirect:/home";
        }
        else{
            model.addAttribute("error", "userName already exist");
            return "register";
        }
    }

}

package com.example.expensetracker.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.DTO.UserDto;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepository;
	
	public String userRegistration(UserDto userDto) {
		
		if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
			return "Password and ConfirmPassword does not Match";
		}
		
			User user = new User();
			
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			
			user.setUserName(userDto.getUserName());
			user.setPassword(userDto.getPassword());
			
			userRepository.save(user);
			
			return "User Registered Successfully";
	}
	
	public String login(String username, String password) {
		Optional<User> optionalUser = userRepository.findByUserName(username);
		
		if(optionalUser.isEmpty()) {
			return "username not found!, please register";
		}
		
		User user = optionalUser.get();
		
		if(!user.getPassword().equals(password)) {
			return "Password incorrect! try again";
		}
		
		return "Login succesfull";
	}
	
}

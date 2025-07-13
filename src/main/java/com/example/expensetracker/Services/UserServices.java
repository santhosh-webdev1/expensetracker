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
	
	public User userRegistration(UserDto userDto) {

		if(userRepository.existsByUserName(userDto.getUserName())){
			return null;
		}
		
		if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
			return null;
		}
		
			User user = new User();
			
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			
			user.setUserName(userDto.getUserName());
			user.setPassword(userDto.getPassword());
			
			return userRepository.save(user);
	}
	
	public Boolean login(String userName, String password) {
		Optional<User> optionalUser = userRepository.findByUserName(userName);
		
		if(optionalUser.isEmpty()) {
			return false;
		}
		
		User user = optionalUser.get();
		
		
		return user.getPassword().equals(password);
		
	}

	public User getUserByUserName(String userName){
		return userRepository.findByUserName(userName).orElse(null);
	}
	
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("users")
	public List<User> getUsers(){
		return this.userRepo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public void createUsers(@RequestBody String[] userArr, String[] arr2) {
		if(userArr.length > 3) {
			User newUser = new User(userArr[0], userArr[1], userArr[2], userArr[3], userArr[4], userArr[5]);
			this.userRepo.save(newUser);
			//Save to DB
		}
		else {
			String username = userArr[0];
			String password = userArr[1];
			//Find in DB	
		}
		
		
	}
}

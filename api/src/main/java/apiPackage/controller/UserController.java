/*
 * Controller class for sending/receiving user data
 */

package apiPackage.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiPackage.model.User;
import apiPackage.repo.UserRepo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserController {
	//Can view 1 logged in user at a time, work-around for keeping logic in the backend
	private User loggedInUser = null; 
	
	
	@Autowired
	private UserRepo userRepo; //"Database" to refer to: Is just an arraylist here
	
	
	
	@GetMapping(value="users")
	public User getUser() { //Sends loggedInUser to front end, will be null until user attempts to login
		return loggedInUser;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/users/remove")
	public void removeUser(@RequestBody User newUser) {
		System.out.println("Removing user");
		loggedInUser = null;
		this.userRepo.delete(newUser); //DELETE from DB
		getUser();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/login")
	public void loginUsers(@RequestBody User newUser) {
		User foundUser = this.userRepo.find(newUser.getEmail(), newUser.getPassword());
	}
	
	
		
		
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public void createUsers(@RequestBody User newUser) {
		/*
		 *  Handles both Register and Login logic
		 *  newUser will either only be an email and password (login) with all other fields null
		 *  or newUser will be an entire user
		 */
		

		if(newUser.getCreditCardNum() == null) {
			//Login Process
			User foundUser = this.userRepo.find(newUser.getEmail(), newUser.getPassword()); //Queries "database" for a user that matches the email and password
			// ^^^ Returns null if no match
			
			if(foundUser.getCreditCardNum() != null) {
				//If we find a matching user: 
				loggedInUser = foundUser; //Update loggedInUser member to interact with frontend
				getUser(); //Updated API so frontend can see
			}
			//No action taken if user does not exist in DB
		}
		
		else {
			//Register Process
			
			//Sets Registered/Renewal Dates 
			newUser.setregisteredDate(LocalDate.now());
			newUser.setrenewalDate(LocalDate.now().plusYears(1));
			
			this.userRepo.save(newUser); //INSERT in to "Database"
			loggedInUser = newUser; //Set loggedInUser member to interact with frontend
			
			getUser(); //Update API so frontend can see
			
		}
	}
}

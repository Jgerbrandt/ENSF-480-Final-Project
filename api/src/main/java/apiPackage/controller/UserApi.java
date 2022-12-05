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

import apiPackage.model.Theatre;
import apiPackage.model.User;
import apiPackage.model.UserController;
import apiPackage.repo.UserRepo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")

public class UserApi {
	private UserController controller = UserController.getLoginInstance();
	
	//Can view 1 logged in user at a time, work-around for keeping logic in the backend
	private User loggedInUser = null; 
	
	
//	@Autowired
//	private UserRepo userRepo; //"Database" to refer to: Is just an arraylist here
//	
	
	
	@GetMapping(value="users")
	public User getUser() { //Sends loggedInUser to front end, will be null until user attempts to login
		return loggedInUser;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/users/remove")
	public void removeUser(@RequestBody User newUser) {
		System.out.println("Removing user");
		loggedInUser = null;
		controller.delete(newUser); //DELETE from DB
		getUser();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/pay")
	public void receivePayment(@RequestBody User targetUser) {
		System.out.println("Payment Received");
		targetUser.payAnnualFee();
		
		controller.updateUser(targetUser); //Update DB with new info
		//IMPLEMENT (?): Generate a receipt for payment
		getUser();
	}
	
	
	
	
	
		
		
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public void createUsers(@RequestBody User newUser) {
		System.out.println("In create user");

		/*
		 *  Handles both Register and Login logic
		 *  newUser will either only be an email and password (login) with all other fields null
		 *  or newUser will be an entire user
		 */
		

		if(newUser.getCreditCardNum().isBlank()) {
			//Login Process
			User foundUser = controller.verifyUser(newUser); //Queries "database" for a user that matches the email and password
			// ^^^ Returns null if no match
			
			if(foundUser != null) {
				System.out.println("Found them!");
				//If we find a matching user: 
				loggedInUser = foundUser; //Update loggedInUser member to interact with frontend
				getUser(); //Updated API so frontend can see
			}
			//No action taken if user does not exist in DB
		}
		
		else {
			System.out.println("In register else");
			//Register Process
			//Sets Registered/Renewal Dates 
			
			User correct = controller.parseInput(newUser);
			//User correct = newUser;
			System.out.println(correct.getEmail());
			if(correct.getEmail() != null) {
				controller.signUp(newUser);
			}
			loggedInUser = correct;
			

			
			
//			newUser.setregisteredDate(LocalDate.now());
//			newUser.setrenewalDate(LocalDate.now().plusYears(1));
			
			
			
			getUser(); //Update API so frontend can see
		}
	}
}

/*
 * ORDER CONTROLLER
 * Receives Order Data from Frontend
 * Needs to connect to database
 */


package apiPackage.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiPackage.model.Order;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class OrderController {

	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public void receiveOrder(@RequestBody Order newOrder) {
		//NEEDS CHANGE:
		//Functions receives an Order object from front end
		//Will need to database it or whatever
			System.out.println(newOrder.getEmail());
	}
}

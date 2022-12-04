/*
 * ORDER CONTROLLER
 * Receives Order Data from Frontend
 * Needs to connect to database
 */


package apiPackage.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiPackage.model.Order;
import apiPackage.model.User;
import apiPackage.repo.OrderRepo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class OrderController {
	
	@Autowired
	private OrderRepo orderRepo; //"Database" to refer to: Right now its just an arraylist 
	
	private List<Order> myOrders = new ArrayList<Order>();
	
	
	@GetMapping(value="/orders")
	public List<Order> getOrder() { //Sends loggedInUser to front end, will be null until user attempts to login
		return myOrders;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public void receiveOrder(@RequestBody Order newOrder) {
		//NEEDS CHANGE:
		//Functions receives an Order object from front end
		//Will need to database it or whatever
		//Needs to set OrderID and TicketID here
		myOrders.clear();
		System.out.println(newOrder.getOrderID());
		this.orderRepo.add(newOrder);
		getOrder();	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/cancel")
	public void cancelOrder(@RequestBody Order newOrder) {
		System.out.println("Cancelling Order");
		myOrders.clear();
		this.orderRepo.cancel(newOrder); //Removes instance newOrder from Database
		//REFUND LOGIC NEEDS TO BE IMPLEMENTED
		//CHANGING SEAT MAP NEEDS TO BE IMPLEMENTED
		getOrder();
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/validate")
	public void validateOrder(@RequestBody Order newOrder) {
		myOrders.clear();
		System.out.println("Entered ");
		String orderEmail = newOrder.getEmail();
		int orderID = newOrder.getOrderID();
		System.out.println(orderEmail + orderID);
		Order foundOrder = this.orderRepo.validate(orderEmail, orderID);
		if(foundOrder != null) {
			System.out.println("Found order");
			myOrders.add(foundOrder);
		}
		getOrder();
	}
}

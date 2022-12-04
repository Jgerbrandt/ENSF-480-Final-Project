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
		//Takes Regular User Orders
		System.out.println("Order Received");
		//System.out.println(newOrder.getTickets().get(0).getMovieName());
		myOrders.clear();
		//Check to see if order is cancellable!!
		this.orderRepo.add(newOrder);
		//IMPLEMENT: Generate TicketID and OrderID
		//IMPLEMENT: Add to database
		getOrder();	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/reg")
	public void receiveRegOrder(@RequestBody Order newOrder) {
		//NEEDS CHANGE:
		//Functions receives an Order object from front end
		//Will need to database it or whatever
		//Needs to set OrderID and TicketID here
		
		//Takes Registered User Orders (Logic same as normal users rn)
		myOrders.clear();
		//Check to see if order is cancellable!!
		this.orderRepo.add(newOrder);
		//IMPLEMENT: Add order to user
		//IMPLEMENT: Generate TicketID and OrderID
		//IMPLEMENT: Add to database
		getOrder();	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/cancel")
	public void cancelOrder(@RequestBody Order newOrder) {
		myOrders.clear();
		this.orderRepo.cancel(newOrder); //Removes instance newOrder from Database
		//IMPLEMENT: Generate discount code.
		//IMPLEMENT: Seat map update
		//IMPLEMENT: Check if order is cancellable
		getOrder();
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/validate")
	public void validateOrder(@RequestBody Order newOrder) {
		//Checks incoming order, returns matching order
		myOrders.clear();
		String orderEmail = newOrder.getEmail();
		int orderID = newOrder.getOrderID();
		Order foundOrder = this.orderRepo.validate(orderEmail, orderID);
		if(foundOrder != null) {
			myOrders.add(foundOrder);
		}
		getOrder();
	}
}

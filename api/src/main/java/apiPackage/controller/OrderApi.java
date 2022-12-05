/*
 * ORDER CONTROLLER
 * Receives Order Data from Frontend
 * Needs to connect to database
 */


package apiPackage.controller;



import java.io.FileNotFoundException;
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
import apiPackage.model.OrdinaryOrder;
import apiPackage.model.Theatre;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class OrderApi {
	private Theatre theatre = Theatre.getTheatre();
	
	
	
	private List<Order> myOrders = new ArrayList<Order>();
	
	
	@GetMapping(value="/orders")
	public List<Order> getOrder() { //Sends loggedInUser to front end, will be null until user attempts to login
		return myOrders;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public void receiveOrder(@RequestBody OrdinaryOrder newOrder) throws FileNotFoundException {
		//NEEDS CHANGE:
		//Functions receives an Order object from front end
		//Will need to database it or whatever
		//Needs to set OrderID and TicketID here
		//Takes Regular User Orders
		newOrder.setOrderID();
		System.out.println("Order Received");
		//System.out.println(newOrder.getTickets().get(0).getMovieName());
		myOrders.clear();
		//Check to see if order is cancellable!!
		newOrder.payForOrder();
		//IMPLEMENT: Generate TicketID and OrderID
		//IMPLEMENT: Add to database
		getOrder();	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/reg")
	public void receiveRegOrder(@RequestBody Order newOrder) throws FileNotFoundException {
		System.out.println("Reg order received");
		newOrder.setOrderID();
		//NEEDS CHANGE:
		//Functions receives an Order object from front end
		//Will need to database it or whatever
		//Needs to set OrderID and TicketID here
		
		//Takes Registered User Orders (Logic same as normal users rn)
		myOrders.clear();
		//Check to see if order is cancellable!!
		//theatre.addOrder(newOrder);
		newOrder.payForOrder();
		//IMPLEMENT: Add order to user
		//IMPLEMENT: Generate TicketID and OrderID
		//IMPLEMENT: Add to database
		getOrder();	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/cancel")
	public void cancelOrder(@RequestBody OrdinaryOrder newOrder) throws FileNotFoundException {
		System.out.println("Order cancel request");
		myOrders.clear();
		Boolean check = newOrder.cancelOrder(); //Removes instance newOrder from Database
		if(!check) {
			System.out.println("cancel failed");
			newOrder.setOrderID(-1);
			myOrders.add(newOrder);
		}
		//Returns true if cancelled
		//False if not cancelled



		getOrder();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/cancel/reg")
	public void cancelOrder(@RequestBody Order newOrder) throws FileNotFoundException {
		System.out.println("Order cancel request REGISTERED");
		myOrders.clear();
		Boolean check = newOrder.cancelOrder();//Removes instance newOrder from Database
		//Returns true if cancelled
		//False if not cancelled
		if(!check) {
			System.out.println("cancel failed");
			newOrder.setOrderID(-1);
			myOrders.add(newOrder);
		}
		
		
		
		getOrder();
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/orders/validate")
	public void validateOrder(@RequestBody Order newOrder) {
		//Only looks at reg orders
		System.out.println("Validating Order");
		System.out.println("Order ID:" + newOrder.getOrderID());
		System.out.println("Email:" + newOrder.getEmail());
		//Checks incoming order, returns matching order
		myOrders.clear();
		String orderEmail = newOrder.getEmail();
		int orderID = newOrder.getOrderID();
		System.out.println("Searching for Orders: " + orderEmail + " " + orderID);
		Order foundOrder = theatre.findOrder(orderID, orderEmail);
		if(foundOrder == null) {
			System.out.println("Order not found :(");
		}
		else {
			myOrders.add(foundOrder);
		}
		getOrder();
	}
	

}

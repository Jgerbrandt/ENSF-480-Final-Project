package apiPackage.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import apiPackage.model.Order;

@Repository
public class OrderRepo {
	private List<Order> orderList = new ArrayList<Order>();
	
	public List<Order> findAll(){
		return orderList; //Returns all normal showtimes
	}
	
	public void add(Order newOrder) {
		orderList.add(newOrder);
	}
	
	public void cancel(Order newOrder) {
		for(int i = 0; i < orderList.size(); i++) {
			Order tempOrder = orderList.get(i);
			if(tempOrder.getOrderID() == newOrder.getOrderID()) {
				orderList.remove(i);
				i = 0;
			}
		}
	}
	
	
	
	public Order validate(String email, int orderID) {
		for(int i = 0; i < orderList.size(); i++) {
			Order tempOrder = orderList.get(i);
			if(tempOrder.getEmail().equals(email) && tempOrder.getOrderID() == orderID) {
				return tempOrder;
			}
		}
		return null;
	}
	
	

}

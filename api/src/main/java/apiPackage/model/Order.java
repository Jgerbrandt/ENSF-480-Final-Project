/*
 * All classes in Model package are more or less copy pasted from classes you guys have built
 * Changes from Git: Added getters/setters to Order
 */

package apiPackage.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int OrderID;
    private String email;
    private List<Ticket> tickets;


    public static int OrderIDCounter;

    public Order(int id, String email) {
        OrderID = id;
        this.email = email;
        tickets = new ArrayList<Ticket>();
    }
    


    public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public static int getOrderIDCounter() {
		return OrderIDCounter;
	}

	public static void setOrderIDCounter(int orderIDCounter) {
		OrderIDCounter = orderIDCounter;
	}

	public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }



}
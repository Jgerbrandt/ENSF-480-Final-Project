/*
 * All classes in Model package are more or less copy pasted from classes you guys have built
 * Changes from Git: Added getters/setters to Order
 */
package apiPackage.model;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    protected int OrderID;
    protected String email;
    protected List<Ticket> tickets;
    protected double price;
    protected LocalDate purchaseDate;
    protected LocalDate refundDate; 
    public static int OrderIDCounter;


    
    public Order() {
    	System.out.println("In default ctor: " + this.getOrderID());

    }

    public Order(String email, List<Ticket> tickets) {
    	System.out.println("In 2 arg ctor: " + this.getEmail());
        this.OrderID = OrderIDCounter;
        OrderIDCounter++;
        this.email = email;
        this.tickets = tickets;
        for(Ticket t : tickets){
            t.setTicketID();
        }
        this.price = (double) tickets.size() * 10;
        this.purchaseDate = LocalDate.now();
        this.refundDate = tickets.get(0).getShowDate().minusDays(3);
    }

    public Order(int OrderID, String email, double price, String rd) {
        this.tickets = new ArrayList<Ticket>();
        this.OrderID = OrderID;
        this.email = email;
        this.price = price;
        this.refundDate = LocalDate.parse(rd, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void addTicket(int ticketID, String movie, int screen, int col, int row, String time, String date, int stID) {
        tickets.add(new Ticket(ticketID, movie, screen, col, row, time, date, stID));
    }

    public int getOrderID() {
        return this.OrderID;
    }

    public String getEmail() {
        return this.email;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public double getPrice() {
        return this.price;
    }

    public LocalDate getpurchaseDate() {
        return this.purchaseDate;
    }

    public String getRefundDate() {
        String formattedDate;
        if(this.refundDate != null){
            formattedDate = this.refundDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        else{
            formattedDate = null;
        }
        return formattedDate;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }
    
    public void setOrderID() {
    	this.OrderID = OrderIDCounter;
    	OrderIDCounter++;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = LocalDate.parse(refundDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void payForOrder() throws FileNotFoundException {
    	System.out.println(this.getOrderID());
        Theatre theatre = Theatre.getTheatre();
//        MovieDatabase movie = new MovieDatabase();
        theatre.addOrder(this);
//        movie.addOrderToDB(this); 
        Receipt receipt = new RegisteredReceipt(this.tickets, this.email, this);
        //this.refundDate = tickets.get(0).getShowDate().minusDays(3);
        this.refundDate = LocalDate.now().plusDays(2);
        
        receipt.createOrderReceipt();
    }

    public boolean cancelOrder() throws FileNotFoundException {
        Theatre theatre = Theatre.getTheatre();
        MovieDatabase movie = new MovieDatabase();
        if(!this.refundDate.isBefore(LocalDate.now())){
        	System.out.println("Unable to cancel");
            return false;
        }
        else {
        	System.out.println("We're cancelling!");
            theatre.removeOrder(this.OrderID);
            movie.removeOrder(this.OrderID);
            Receipt receipt = new RegisteredReceipt(this.tickets, this.email, this);
            receipt.createRefundReceipt();
            return true;
        }
    }

}
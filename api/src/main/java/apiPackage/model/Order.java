/*
 * All classes in Model package are more or less copy pasted from classes you guys have built
 * Changes from Git: Added getters/setters to Order
 */
package apiPackage.model;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Order {
    protected int OrderID;
    protected String email;
    protected List<Ticket> tickets;
    protected double price;
    protected LocalDate purchaseDate;
    protected LocalDate refundDate; 
    public static int OrderIDCounter;

    public Order(){}
    

    public Order(String email, List<Ticket> tickets) {
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

//    public void payForOrder() throws FileNotFoundException {
//        MovieDatabase movie = new MovieDatabase();
//        movie.addOrderToDB(this); 
//        Receipt receipt = new RegisteredReceipt(this.tickets, this.email, this);
//        receipt.createOrderReceipt();
//    }

//    public void cancelOrder() throws FileNotFoundException {
//        MovieDatabase movie = new MovieDatabase();
//        movie.removeOrder(this.OrderID);
//        Receipt receipt = new RegisteredReceipt(this.tickets, this.email, this);
//        receipt.createRefundReceipt();
//    }

}
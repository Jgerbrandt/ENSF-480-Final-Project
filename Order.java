/**
* Order class is a controller object that dynamicaaly makes tickets and 
* communicates with different users and the Theatre singlton for DB access
*
*/

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
    
    /**
    * default ctor
    */
    public Order(){}
    
    /**
    * ctro to dynamically create new orders when users book tickets
    * @param email  user email to send receipt and tickets
    * @param tickets    list of tickets user is buying
    */
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

    /**
    * ctro to re-init objects from database
    * @param OrderID    id of existing order
    * @param email     user email in existing order
    * @param price      totalcost of existing order
    * @param rd         refund date of existing order; determines if order is cancelable (outside of 72 hours)
    */
    public Order(int OrderID, String email, double price, String rd) {
        this.tickets = new ArrayList<Ticket>();
        this.OrderID = OrderID;
        this.email = email;
        this.price = price;
        this.refundDate = LocalDate.parse(rd, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    /**
    * adds tickets to existing order
    * parameters include all ticket information
    */
    public void addTicket(int ticketID, String movie, int screen, int col, int row, String time, String date, int stID) {
        tickets.add(new Ticket(ticketID, movie, screen, col, row, time, date, stID));
    }
    
    //getters
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
    
    //setters
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
    
    /**
    * confirms booking of tickets and makes user transaction
    * new order is saved to dynamic arrays and DB upon payment
    * Receipt is generated
    *
    */
    public void payForOrder() throws FileNotFoundException {
        Theatre theatre = Theatre.getTheatre();
        MovieDatabase movie = new MovieDatabase();
        theatre.addOrder(this);
        movie.addOrderToDB(this); 
        Receipt receipt = new RegisteredReceipt(this.tickets, this.email, this);
        receipt.createOrderReceipt();
    }
    
    /**
    * if order is cancelable (not wihtin 72 hours of showtime), order is cancelled
    * order is removed from dynamic arrays and DB
    * order confirmation receipt is sent to user
    *
    */
    public boolean cancelOrder() throws FileNotFoundException {
        Theatre theatre = Theatre.getTheatre();
        MovieDatabase movie = new MovieDatabase();
        if(!this.refundDate.isBefore(LocalDate.now())){
            return false;
        }
        else {
            theatre.removeOrder(this.OrderID);
            movie.removeOrder(this.OrderID);
            Receipt receipt = new RegisteredReceipt(this.tickets, this.email, this);
            receipt.createRefundReceipt();
            return true;
        }
    }

}

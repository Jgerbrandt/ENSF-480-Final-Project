import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Order {
    private int OrderID;
    private String email;
    private List<Ticket> tickets;
    private double price;
    private LocalDate purchaseDate;
    private LocalDate refundDate; 
    public static int OrderIDCounter;
    private boolean isPaid = false;

    public Order(String email, List<Ticket> tickets) {
        this.OrderID = OrderIDCounter;
        OrderIDCounter++;
        this.email = email;
        this.tickets = tickets;
        this.price = (double) tickets.size() * 10;
        this.refundDate = tickets.get(0).getShowtime().getShowDate().minusDays(3);
    }

    public Order(int OrderID, String email, double price, String rd) {
        this.OrderID = OrderID;
        this.email = email;
        this.price = price;
        this.refundDate = LocalDate.parse(rd, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
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
        String formattedDate = this.refundDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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

    public void payForOrder(){
        MovieDatabase movie = new MovieDatabase();
        this.isPaid = true;
        movie.addOrderToDB(this);    
    }

    public void cancelOrder(){
        MovieDatabase movie = new MovieDatabase();

    }

}
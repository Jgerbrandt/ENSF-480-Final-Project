import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int OrderID;
    private String email;
    private List<Ticket> tickets;
    private double price;
    private LocalDate purchaseDate;
    private String refundDate;
    private Duration cancelPeriod;
    private Receipt receipt;
    private Ticket ticket;    
    public static int OrderIDCounter;

    public Order(String email, List<Ticket> tickets) {
        this.email = email;
        this.tickets = tickets;
    }

    public Order(int OrderID, String email, double price, String refundDate) {
        this.OrderID = OrderID;
        this.email = email;
        this.price = price;
        this.refundDate = refundDate;
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
        return this.refundDate;
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
        this.refundDate = refundDate;
    }
}
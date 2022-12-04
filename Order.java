import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
class Order {
    private int OrderID;
    private String email;
    private List<Ticket> tickets;
    private double price;
    private LocalDate purchaseDate;
    private LocalDate refundDate;
    private Duration cancelPeriod;
    private Receipt receipt;    
    public static int OrderIDCounter;

    public Order(int OrderID, String email, List<Ticket> tickets, double price, LocalDate purchaseDate, LocalDate refundDate, Duration cancelPeriod, Receipt receipt) {
        this.OrderID = OrderID;
        this.email = email;
        this.tickets = (List<Ticket>) new Ticket(OrderID, email, OrderID, OrderID, OrderID, null);
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.refundDate = refundDate;
        this.cancelPeriod = cancelPeriod;
        this.receipt = receipt;
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

    public LocalDate getRefundDate() {
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

    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }
}
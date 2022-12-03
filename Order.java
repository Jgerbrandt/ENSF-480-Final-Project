import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Order {
    private int OrderID;
    private String email;
    private List<Ticket> tickets;
    private double refundAmount;
    private double price;
    private LocalDate purchaseDate;
    private LocalDate refundDate;
    public static int OrderIDCounter;

    public Order(int id, String email, List<Ticket> tickets, double refAmm, double price) {
        this.OrderID = id;
        this.email = email;
        this.tickets = new ArrayList<Ticket>();
        this.refundAmount = refAmm;
        this.price = price;
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

    public double getRefundAmount() {
        return this.refundAmount;
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

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
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
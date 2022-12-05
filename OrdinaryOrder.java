import java.io.FileNotFoundException;
import java.util.List;

public class OrdinaryOrder extends Order {

    private double discount;
    private DiscountCode discountCode;
    private double refund;

    //Ordanary creates discount code
    public OrdinaryOrder(){}

    public OrdinaryOrder(String email, List<Ticket> tickets) {
        super(email, tickets);
    }
    
    public OrdinaryOrder(int OrderID, String tickets, double price, String refundDate) {
        super(OrderID, tickets, price, refundDate);
        this.discount = price * 0.15;
        this.refund = price * 0.75;
    }

    public double getRefund() {
        double price = getPrice();
        refund = price * 0.75;
        return this.refund;
    }

    public double getDiscount() {
        double price = getPrice();
        discount = price * 0.15;
        return this.discount;
    }

    @Override
    public void payForOrder() throws FileNotFoundException {
        Theatre theatre = Theatre.getTheatre();
        MovieDatabase movie = new MovieDatabase();
        theatre.addOrder(this);
        movie.addOrderToDB(this); 
        Receipt receipt = new OrdinaryReceipt(this.tickets, this.email, this);
        receipt.createOrderReceipt();
    }

    @Override 
    public void cancelOrder() throws FileNotFoundException {
        Theatre theatre = Theatre.getTheatre();
        MovieDatabase movie = new MovieDatabase();
        theatre.removeOrder(this.OrderID);
        movie.removeOrder(this.OrderID);
        this.discountCode = new DiscountCode(discount);
        Receipt receipt = new OrdinaryReceipt(this.tickets, this.email, this, this.discountCode);
        receipt.createRefundReceipt();
        theatre.addDiscount(this.discount);
        movie.addDiscountCodeToDB(this.discountCode);
    }
}
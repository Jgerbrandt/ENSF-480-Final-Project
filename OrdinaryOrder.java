import java.io.FileNotFoundException;

public class OrdinaryOrder extends Order {

    private double discount;
    private DiscountCode discountCode;
    private double refund;

    //Ordanary creates discount code
    
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
        MovieDatabase movie = new MovieDatabase();
        movie.addOrderToDB(this); 
        Receipt receipt = new OrdinaryReceipt(this.tickets, this.email, this);
        receipt.createOrderReceipt();
    }

    @Override 
    public void cancelOrder() throws FileNotFoundException {
        MovieDatabase movie = new MovieDatabase();
        movie.removeOrder(this.OrderID);
        this.discountCode = new DiscountCode(discount);
        Receipt receipt = new OrdinaryReceipt(this.tickets, this.email, this, this.discountCode);
        receipt.createRefundReceipt();
        movie.addDiscountCodeToDB(this.discountCode);
    }
}
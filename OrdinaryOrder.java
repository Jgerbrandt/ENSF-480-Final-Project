import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class OrdinaryOrder extends Order {

    private double discount;
    private DiscountCode discountCode;
    private double refund;

    //Ordanary creates discount code
    
    public OrdinaryOrder(int OrderID, List<Ticket> tickets, double refundAmount, double price, LocalDate purchaseDate,
            LocalDate refundDate, Showtime showtime, Receipt receipt, Duration time) {
        super(OrderID, tickets, refundAmount, price, purchaseDate, refundDate, showtime, receipt, time);
        //TODO Auto-generated constructor stub
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
}

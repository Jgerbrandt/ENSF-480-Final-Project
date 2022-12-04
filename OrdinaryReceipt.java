import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OrdinaryReceipt implements Receipt {
    private Ticket ticket;
    private Order order;
    private Showtime showtime;
    
    @Override
    public void createOrderReceipt() throws FileNotFoundException {
        PrintWriter orderReceipt = new PrintWriter("OrderReceipt.txt");
        
        orderReceipt.println("Movie: " + ticket.getMovieName());
        orderReceipt.println("Screen: " + showtime.getScreen());
        orderReceipt.println("Date: " + showtime.getShowDate());
        orderReceipt.println("Time: " + showtime.getTime());
        orderReceipt.println("Email: " + order.getEmail());
        orderReceipt.println("Price: " + order.getPrice());
        orderReceipt.println("Date Purchased: " + order.getpurchaseDate());

        orderReceipt.close();
    }

    @Override
    public void createRefundReceipt() throws FileNotFoundException {
        PrintWriter refundReceipt = new PrintWriter("RefundReceipt.txt");
        
        refundReceipt.println("Movie: " + ticket.getMovieName());
        refundReceipt.println("Email: " + order.getEmail());
        refundReceipt.println("Refund Amount: " + order.getRefundAmount());
        refundReceipt.println("Date Refunded: " + order.getRefundDate());
        refundReceipt.println("Discount Code: " + order.getDiscountCode());

        refundReceipt.close();
    }
}

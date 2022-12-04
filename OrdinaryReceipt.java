import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.time.LocalDate;
import java.time.format.*;

public class OrdinaryReceipt implements Receipt {
    private List<Ticket> tickets;
    private String email;
    private OrdinaryOrder order;
    private DiscountCode discountCode;

    public OrdinaryReceipt(List<Ticket> t, String em, OrdinaryOrder o){
        this.tickets = t;
        this.email = em;
        this.order = o;
    }

    public OrdinaryReceipt(List<Ticket> t, String em, OrdinaryOrder o, DiscountCode dc){
        this.tickets = t;
        this.email = em;
        this.order = o;
        this.discountCode = dc;
    }
    
    @Override
    public void createOrderReceipt() throws FileNotFoundException {
        PrintWriter orderReceipt = new PrintWriter("OrderReceipt.txt");
        
        orderReceipt.println("Movie: " + tickets.get(0).getMovieName());
        orderReceipt.println("Screen: " + tickets.get(0).getShowtime().getScreen());
        orderReceipt.println("Date: " + tickets.get(0).getShowtime().getShowDate());
        orderReceipt.println("Time: " + tickets.get(0).getShowtime().getTime());
        orderReceipt.println("Email: " + email);
        orderReceipt.println("Tickets: " + tickets.size());
        orderReceipt.println("Price: " + order.getPrice());
        orderReceipt.println("Date Purchased: " + order.getpurchaseDate());

        orderReceipt.close();
    }

    @Override
    public void createRefundReceipt() throws FileNotFoundException {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        PrintWriter refundReceipt = new PrintWriter("RefundReceipt.txt");
        
        refundReceipt.println("Movie: " + tickets.get(0).getMovieName());
        refundReceipt.println("Email: " + email);
        refundReceipt.println("Refund Amount: " + order.getPrice());
        refundReceipt.println("Refund Date: " + formattedDate);


        refundReceipt.println("Discount Code: " + discountCode.getCode());

        refundReceipt.close();
    }
}
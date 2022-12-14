
/**
* OrdinaryReceipt implements Receipt interface
* creates text file of receipt info
*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.time.LocalDate;
import java.time.format.*;

public class RegisteredReceipt implements Receipt {
    private List<Ticket> tickets;
    private String email;
    private Order order;

    /**
    * Create new receipt object as for creating order receipt
    *
    * @param t  list of tickets included in order
    * @param em user email tied to order
    * @param o  order in which recipt is being made for
    */
    public RegisteredReceipt(List<Ticket> t, String em, Order o){
        this.tickets = t;
        this.email = em;
        this.order = o;
    }
    
    /**
    * create text file to send to user with order confimration details
    *
    */
    @Override
    public void createOrderReceipt() throws FileNotFoundException {
        PrintWriter orderReceipt = new PrintWriter("OrderReceipt.txt");
        
        orderReceipt.println("Movie: " + tickets.get(0).getMovieName());
        orderReceipt.println("Screen: " + tickets.get(0).getScreen());
        orderReceipt.println("Date: " + tickets.get(0).getShowDate());
        orderReceipt.println("Time: " + tickets.get(0).getTime());
        orderReceipt.println("Email: " + email);
        orderReceipt.println("Tickets: " + tickets.size());
        orderReceipt.println("Price: " + order.getPrice());
        orderReceipt.println("Date Purchased: " + order.getpurchaseDate());

        orderReceipt.close();
    }

    /**
    * create text file to send to user with order cancellation details
    *
    */
    @Override
    public void createRefundReceipt() throws FileNotFoundException {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        PrintWriter refundReceipt = new PrintWriter("RefundReceipt.txt");
        
        refundReceipt.println("Movie: " + tickets.get(0).getMovieName());
        refundReceipt.println("Email: " + email);
        refundReceipt.println("Refund Amount: " + order.getPrice());
        refundReceipt.println("Refund Date: " + formattedDate);

        refundReceipt.close();
    }
}

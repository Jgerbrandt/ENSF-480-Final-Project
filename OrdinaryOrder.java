/**
* OrdinaryOrder class is a controller object that dynamicaaly makes tickets and 
* communicates with different users and the Theatre singlton for DB access
* this class extends Order to implement additionally cancellation protocols
*
*/

import java.io.FileNotFoundException;
import java.util.List;
import java.time.LocalDate;

public class OrdinaryOrder extends Order {

    private double discount;
    private DiscountCode discountCode;
    private double refund;

    /**
    * default ctor
    */
    public OrdinaryOrder(){}

    /**
    * ctro to dynamically create new orders when users book tickets
    * @param email  user email to send receipt and tickets
    * @param tickets    list of tickets user is buying
    */    
    public OrdinaryOrder(String email, List<Ticket> tickets) {
        super(email, tickets);
    }
    
    /**
    * ctro to re-init objects from database
    * @param OrderID    id of existing order
    * @param email     user email in existing order
    * @param price      total cost of existing order
    * @param refund         refund date of existing order; determines if order is cancelable (outside of 72 hours)
    */
    public OrdinaryOrder(int OrderID, String tickets, double price, String refundDate) {
        super(OrderID, tickets, price, refundDate);
        this.discount = price * 0.15;
        this.refund = price * 0.75;
    }
    
    //getters
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
    
    /**
    * confirms booking of tickets and makes user transaction
    * new order is saved to dynamic arrays and DB upon payment
    * Receipt is generated
    *
    */
    @Override
    public void payForOrder() throws FileNotFoundException {
        Theatre theatre = Theatre.getTheatre();
        MovieDatabase movie = new MovieDatabase();
        theatre.addOrder(this);
        movie.addOrderToDB(this); 
        Receipt receipt = new OrdinaryReceipt(this.tickets, this.email, this);
        receipt.createOrderReceipt();
    }

    /**
    * if order is cancelable (not wihtin 72 hours of showtime), order is cancelled
    * order is removed from dynamic arrays and DB
    * order confirmation receipt is sent to user
    * User pays 15% admin fee, but can redeem it at next purchase
    * DiscountCode for admin fee is created and sent to user and saved in program/DB
    *
    * @return if order is cancelable
    */
    @Override 
    public boolean cancelOrder() throws FileNotFoundException {
        Theatre theatre = Theatre.getTheatre();
        MovieDatabase movie = new MovieDatabase();
        if(!this.refundDate.isBefore(LocalDate.now())){
            return false;
        }
        else {
            theatre.removeOrder(this.OrderID);
            movie.removeOrder(this.OrderID);
            this.discountCode = new DiscountCode(discount);
            Receipt receipt = new OrdinaryReceipt(this.tickets, this.email, this, this.discountCode);
            receipt.createRefundReceipt();
            theatre.addDiscount(this.discountCode);
            movie.addDiscountCodeToDB(this.discountCode);
            return true;
        }
    }
}

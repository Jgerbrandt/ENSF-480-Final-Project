import java.lang.String;
import java.time.LocalDate;

public class Registered extends User{
    
    private String name;
    private String creditCardNum;
    private String address;
    private String email;
    private boolean isPaid;
    private LocalDate registeredDate;
    private LocalDate renewalDate;
    private Order order;
    private int orderID = (int) Math.floor(Math.random() * 9_000_000_00) + 1_000_000_00;


    public Registered(String n, String ccn, String addr, String email, boolean isPaid, LocalDate regD, LocalDate renD) {
        this.name = n;
        this.creditCardNum = ccn;
        this.address = addr;
        this.email = email;
        this.isPaid = isPaid;
        this.registeredDate = regD;
        this.renewalDate = renD;
        this.order = new Order(orderID, email);
    }

    public String getName() {return this.name;}
    public String getCreditCardNum() {return this.creditCardNum;}
    public String getAddress() { return this.address;}
    public String getEmail() {return this.email;}
    public boolean getIsPaid() {return this.isPaid;}
    public LocalDate getregisteredDate() {return this.registeredDate;}
    public LocalDate getrenewalDate() {return this.renewalDate;}
    public Order getOrder() {return this.order;}

    public void setName(String name) {this.name = name;}
    public void setCreditCardNum(String creditCardNum) {this.creditCardNum = creditCardNum;}
    public void setAddress(String address) {this.address = address;}
    public void setEmail(String email) {this.email = email;}
    public void setIsPaid(boolean isPaid) {this.isPaid = isPaid;}
    public void setregisteredDate(LocalDate registeredDate) {this.registeredDate = registeredDate;}
    public void setrenewalDate(LocalDate renewalDate) {this.renewalDate = renewalDate;}

    @Override
    public void payForOrder() {

    }
}
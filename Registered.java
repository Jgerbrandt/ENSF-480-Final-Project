import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Registered{

    private String name;
    private String password;
    private String creditCardNum;
    private String address;
    private String email;
    private boolean isPaid;
    private String renewalDateString;
    private LocalDate renewalDate;
    private List<Order> orders;

    public Registered(String n, String pass, String ccn, String addr, String email, boolean isPaid, String time) {
        this.name = n;
        this.password = pass;
        this.creditCardNum = ccn;
        this.address = addr;
        this.email = email;
        this.isPaid = isPaid;
        this.renewalDateString = time;
        this.orders = new ArrayList<Order>();
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCreditCardNum() {
        return this.creditCardNum;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getIsPaid() {
        return this.isPaid;
    }

    public LocalDate getrenewalDate() {
        return this.renewalDate;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
    
    public void addOrder(Order order) {
		orders.add(order);
		
	}
	public void setRenewalDateString(String date) {
		this.renewalDateString = date;
    }
    
    public void setRenewalDate() { this.renewalDate = LocalDate.parse(renewalDateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));}
    
    public void display() {
		if (this.email == null) {	
			System.out.println("check");
		}
		else {
			System.out.println(email);
		}
		System.out.println(name);
		System.out.println(password);
		System.out.println(address);
		System.out.println(creditCardNum);
		System.out.println(renewalDate);
	}
    
    @Override
    public void payForOrder() {

    }

    @Override
    public void cancelOrder() {

    }
}

/**
* User object for registered patrons
* Holds orders and user information
*
*/

import java.io.FileNotFoundException;
import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class User{

    private String name;
    private String password;
    private String creditCardNum;
    private String address;
    private String email;
    private boolean isPaid;
    private LocalDate renewalDate;
    private List<Order> orders;
	
	/**
	* default ctor
	*/
    public User(){ }
	
	/**
	*ctor to re-init users from database
	*/
    public User(String n, String pass, String ccn, String addr, String email, boolean isPaid, String renDate) {
        this.name = n;
        this.password = pass;
        this.creditCardNum = ccn;
        this.address = addr;
        this.email = email;
        this.isPaid = isPaid;
        setRenewalDate(renDate);
        checkRenewal();
        this.orders = new ArrayList<Order>();
    }
	
	//getters
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
	
	//setters
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
    
    public void setRenewalDate(String renDate) { this.renewalDate = LocalDate.parse(renDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));}

    public void setRenewalDate(LocalDate today) {
        this.renewalDate = today;
    }
    
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
	
	/**
	*add orders to dynamic arrays and DB
	*
	*/
    public void addToDB(){      
        UserController uc = UserController.getLoginInstance();
        uc.signUp(this);
    }
	
	/**
	* cancel user order
	*
	* @param id 	ID to find order to cancel
	*/
    public void cancelOrder(int id) throws FileNotFoundException {
        for(Order o : orders){
            if(o.getOrderID() == id){
                o.cancelOrder();
                this.orders.remove(o);
            }
        }
    }
	
	/**
	* if user hasn't paid, make transaction and update paid flag
	* also updates renew date for next year
	*
	*/
    public void payAnnualFee(){
        this.isPaid = true;
        this.renewalDate = LocalDate.now().plusYears(1);
    }
	
	/**
	* check if user has paid annual fee
	*/
    private void checkRenewal(){
        if(this.renewalDate.isBefore(LocalDate.now())){
            this.isPaid = false;
        }
    }
}

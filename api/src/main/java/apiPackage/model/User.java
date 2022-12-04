
/*
 * All classes in Model package are more or less copy pasted from classes you guys have built
 * Changes from Git: Removed inheritance for testing without parent class
 * Renamed all refered to self User instead of Register
 * Removed payForOrder() as it was dependant on inheritance
 * Added Password member for login purposes
 */

package apiPackage.model;
import java.lang.String;
import java.time.LocalDate;

public class User{ //public class Registered extends User{
    
    private String name;
    private String creditCardNum;
    private String address;
    private String email;
    private boolean isPaid;
    private LocalDate registeredDate;
    private LocalDate renewalDate;
    private Order order;
    private String password;
    private int orderID = (int) Math.floor(Math.random() * 9_000_000_00) + 1_000_000_00;

    //public Register
    public User(String n, String ccn, String addr, String email, boolean isPaid, LocalDate regD, LocalDate renD, String password) {
        this.name = n;
        this.creditCardNum = ccn;
        this.address = addr;
        this.email = email;
        this.isPaid = isPaid;
        this.registeredDate = regD;
        this.renewalDate = renD;
        //this.order = new Order(orderID, email);
        this.password = password;
    }
    
    

    public String getName() {return this.name;}
    public String getCreditCardNum() {return this.creditCardNum;}
    public String getAddress() { return this.address;}
    public String getEmail() {return this.email;}
    public boolean getIsPaid() {return this.isPaid;}
    public LocalDate getregisteredDate() {return this.registeredDate;}
    public LocalDate getrenewalDate() {return this.renewalDate;}
    public Order getOrder() {return this.order;}
    public String getPassword() {return this.password;}

    public void setName(String name) {this.name = name;}
    public void setCreditCardNum(String creditCardNum) {this.creditCardNum = creditCardNum;}
    public void setAddress(String address) {this.address = address;}
    public void setEmail(String email) {this.email = email;}
    public void setIsPaid(boolean isPaid) {this.isPaid = isPaid;}
    public void setregisteredDate(LocalDate registeredDate) {this.registeredDate = registeredDate;}
    public void setrenewalDate(LocalDate renewalDate) {this.renewalDate = renewalDate;}
    public void setPassword(String password) {this.password = password;}

    //Caused errors as inheritance was needed
    /*
    @Override
    public void payForOrder() {

    }
    */
}
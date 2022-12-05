/**
* Discount Code object to be stoed in the data base when an order is cancelled
* The unique code can be used to find the corresponding discount (15% of cancelled order)
*
*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiscountCode {
    public static int CodeIDCounter;
	private int code;
	private double discount;
    private LocalDate exp;
	
	/**
	* default ctor
	*/
    public DiscountCode(){}
	
	/**
	* ctor to generate new code and return to user
	* @param discount 	associated discount (15% of cancelled order)
	*/	
	public DiscountCode(double discount) {
		this.code = CodeIDCounter;
        CodeIDCounter++;
		this.discount = discount;
        this.exp = LocalDate.now().plusYears(1);
	}
	
	/**
	* ctor to re-init object from the DB
	* @param code		unique code to be used at checkout to get discount
	* @param discount 	associated discount (15% of cancelled order)
	* @param date		expire date for discount code (1 year after code generation)
	*/
    public DiscountCode(int code, double discount, String date){
        this.code = code;
		this.discount = discount;
        this.exp = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
	
	//getters
    public int getCode(){ return this.code; }
    public double getDiscount(){ return this.discount; }
    public String getExp(){ 
        String formattedDate = this.exp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return formattedDate;
    }
}

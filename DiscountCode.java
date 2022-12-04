import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DiscountCode {
	private int code;
	private double discount;
	private LocalDate expirationDate;
	
	public static int CodeIDCounter;
	
	public DiscountCode(int code, double disc, String date) {
		this.code = code;
		this.discount = disc;
	}
	
	public void setExpirationDate(String expdate) {
		this.expirationDate = LocalDate.parse(expdate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	public void display(){
		System.out.println(code);
		System.out.println(discount);
	}

}

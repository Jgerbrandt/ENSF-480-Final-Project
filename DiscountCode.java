import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DiscountCode {
    public static int CodeIDCounter;
	private int code;
	private double discount;
    private LocalDate exp;
	
	public DiscountCode(double discount) {
		this.code = CodeIDCounter;
        CodeIDCounter++;
		this.discount = discount;
        this.exp = LocalDate.now().plusYears(1);
	}

    public DiscountCode(int code, double discount, String date){
        this.code = code;
		this.discount = discount;
        this.exp = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
	
    public int getCode(){ return this.code; }
    public double getDiscount(){ return this.discount; }
    public String getExp(){ 
        String formattedDate = this.exp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return formattedDate;
    }
}
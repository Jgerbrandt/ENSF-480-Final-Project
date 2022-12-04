package apiPackage.model;

public class DiscountCode {
	private int code;
	private double discount;
	
	public DiscountCode(int code, double disc) {
		this.code = code;
		this.discount = disc;
	}
	
	public int getCode() {
		return code;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void display(){
		System.out.println(code);
		System.out.println(discount);
	}
}
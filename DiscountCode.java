class DiscountCode {
	private int code;
	private double discount;
	
	public DiscountCode(int code, double disc) {
		this.code = code;
		this.discount = disc;
	}
	
	public void display(){
		System.out.println(code);
		System.out.println(discount);
	}

}
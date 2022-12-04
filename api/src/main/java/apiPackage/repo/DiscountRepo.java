package apiPackage.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import apiPackage.model.DiscountCode;

@Repository
public class DiscountRepo {
	private List<DiscountCode> list = new ArrayList<DiscountCode>();
	
	public double checkDiscounts(int code) {
		for(int i = 0; i < list.size(); i++) {
			DiscountCode tempCode = list.get(i);
			if(tempCode.getCode() == code) {
				Double returnValue = tempCode.getDiscount();
				list.remove(i);
				return returnValue;
				
			}
		}
		return 10;
	}
	
	public void add(DiscountCode code) {
		list.add(code);
	}
	
	public void remove(DiscountCode code) {
		list.remove(code);
	}
	

}

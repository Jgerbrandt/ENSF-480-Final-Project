package apiPackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiPackage.model.DiscountCode;
import apiPackage.repo.DiscountRepo;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class DiscountController {
	
	
		@Autowired
		private DiscountRepo discountRepo; //"Database" to refer to: Right now its just an arraylist 
		
		private double currentDiscount = 10;
		
		@GetMapping("discount")
		public Double getDiscount(){
			return this.currentDiscount; //Selection query to Database: Shows all normal user movies
		}
		
		@RequestMapping(method=RequestMethod.POST, value="/discount")
		public void checkDiscount(@RequestBody DiscountCode code) {
			//Check if discount code matches DB
			System.out.println("Checking for discount code");
			System.out.println(code.getCode());
			currentDiscount = discountRepo.checkDiscounts(code.getCode());
			getDiscount();
		}

}

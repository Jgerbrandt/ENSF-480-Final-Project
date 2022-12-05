package apiPackage.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiPackage.model.DiscountCode;
import apiPackage.model.Theatre;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class DiscountApi {
	private Theatre theatre = Theatre.getTheatre();
	

		
		private double currentDiscount = 10;
		
		@GetMapping("discount")
		public Double getDiscount(){
			return this.currentDiscount; //Selection query to Database: Shows all normal user movies
		}
		
		@RequestMapping(method=RequestMethod.POST, value="/discount")
		public void checkDiscount(@RequestBody DiscountCode code) {
			DiscountCode myCode = theatre.findDiscount(code.getCode());
			theatre.removeDiscount(code.getCode());
			currentDiscount = myCode.getDiscount();
			getDiscount();
		}

}

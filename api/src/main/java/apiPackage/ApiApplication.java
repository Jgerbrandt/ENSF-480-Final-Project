/*
 * MAIN FILE
 * Initializes API
 * Populates "Database" with test data
 */


package apiPackage;


import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import apiPackage.model.DiscountCode;
import apiPackage.model.Movie;
import apiPackage.model.Order;
import apiPackage.model.Theatre;
import apiPackage.model.Ticket;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}	
	



	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Theatre myTheatre = Theatre.getTheatre();


		Movie testMovie1 = new Movie("Lord of the Rings", "06-12-2020");
		Movie testMovie2 = new Movie("Avengers", "12-05-2022");
		


		//Method 1:
//		Showtime testShowtime = new Showtime(1, 2, "05-12-2022", "9:00pm", testMovie1);
//		testMovie1.addShowtime(testShowtime);
		
		//Method 2:
		testMovie1.addShowtime(1, 3, "12-06-2022", "9:00pm", LocalDate.of(2020, 06, 01));
		
		
		
//		testMovie1.addShowtime(2, 4,"06-12-2022", "10:00pm");
//		testMovie1.addShowtime(3, 5, "06-12-2022", "8:30pm");
//		
//		testMovie2.addShowtime(4, 6, "05-12-2022", "9:30pm");
//		testMovie2.addShowtime(5, 7, "07-12-2022", "9:30pm");
		
		myTheatre.addMovie(testMovie1);
		myTheatre.addMovie(testMovie2);
		
		DiscountCode testDiscount = new DiscountCode(5);
		myTheatre.addDiscount(testDiscount);
		
//		Order testOrder = new Order(0, "testEmail");
//		Ticket testTicket = new Ticket(0, "TestMovie", 0, 0, testShowtime);
//		testOrder.addTicket(testTicket);
//		
//		this.orderRepo.add(testOrder);
		

		
		
		
//		this.movieRepo.save(new Movie("10pm", "Lord of the Rings", 1, "06-01-2023", 1)); //Nobody
//		this.movieRepo.save(new Movie("10pm", "Lord of the Rings", 1, "01-06-2020", 1)); //Everybody
//		this.movieRepo.save(new Movie("10pm", "Lord of the Rings", 1, "05-12-2022", 1)); //Registered Only
		

		

		
	}

}

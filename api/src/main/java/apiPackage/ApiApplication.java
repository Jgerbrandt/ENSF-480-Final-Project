/*
 * MAIN FILE
 * Initializes API
 * Populates "Database" with test data
 */


package apiPackage;


import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import apiPackage.model.Movie;
import apiPackage.repo.MovieRepo;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	@Autowired
	private MovieRepo movieRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		
		Movie testMovie1 = new Movie("Lord of the Rings", "01-06-2020");
		Movie testMovie2 = new Movie("Avengers", "05-12-2022");
		
		testMovie1.addShowtime(1, "05-12-2022", "9:00pm");
		testMovie1.addShowtime(2, "06-12-2022", "10:00pm");
		testMovie1.addShowtime(3, "06-12-2022", "8:30pm");
		
		testMovie2.addShowtime(4, "05-12-2022", "9:30pm");
		testMovie2.addShowtime(5, "07-12-2022", "9:30pm");
		
		this.movieRepo.save(testMovie1);
		this.movieRepo.save(testMovie2);
		
		
//		this.movieRepo.save(new Movie("10pm", "Lord of the Rings", 1, "06-01-2023", 1)); //Nobody
//		this.movieRepo.save(new Movie("10pm", "Lord of the Rings", 1, "01-06-2020", 1)); //Everybody
//		this.movieRepo.save(new Movie("10pm", "Lord of the Rings", 1, "05-12-2022", 1)); //Registered Only
		

		

		
	}

}

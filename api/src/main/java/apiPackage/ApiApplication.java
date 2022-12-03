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

import apiPackage.model.Showtime;
import apiPackage.repo.ShowTimeRepo;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	@Autowired
	private ShowTimeRepo movieRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		this.movieRepo.save(new Showtime("10pm", "Lord of the Rings", 1, "06-01-2023", 1)); //Nobody
		this.movieRepo.save(new Showtime("10pm", "Lord of the Rings", 1, "01-06-2020", 1)); //Everybody
		this.movieRepo.save(new Showtime("10pm", "Lord of the Rings", 1, "05-12-2022", 1)); //Registered Only
		

		

		
	}

}

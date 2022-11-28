package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Movie;
import com.example.demo.model.User;
import com.example.demo.repo.MovieRepo;
import com.example.demo.repo.UserRepo;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		this.movieRepo.save(new Movie("Lord of the Rings", "12pm", "3"));
		this.movieRepo.save(new Movie("Harry Potter", "4pm", "5"));
		this.movieRepo.save(new Movie("Avengers", "2pm", "1"));
		
	}

}

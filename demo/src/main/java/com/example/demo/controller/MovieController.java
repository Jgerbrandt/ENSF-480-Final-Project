package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.model.User;
import com.example.demo.repo.MovieRepo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class MovieController {
	
	@Autowired
	private MovieRepo movieRepo;
	
	@GetMapping("movies")
	public List<Movie> getMovies(){
		return this.movieRepo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/movies")
	public void updateSeats(@RequestBody String[] MovieData) {
			String movieTitle = 
		}
		
		
	}
}

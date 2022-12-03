/*
 * Controller for Showtime Objects
 * Exports Showtimes from "database" to front end
 * Receives changes to seating map
 */

package apiPackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apiPackage.model.Showtime;
import apiPackage.model.User;
import apiPackage.repo.ShowTimeRepo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/")
public class ShowTimeController {
	
	@Autowired
	private ShowTimeRepo movieRepo; //"Database" to refer to: Right now its just an arraylist 
	
	@GetMapping("movies")
	public List<Showtime> getMovies(){
		return this.movieRepo.findAll(); //Selection query to Database: Shows all normal user movies
	}
	
	@GetMapping("movies/reg")
	public List<Showtime> getEarlyMovies(){
		return this.movieRepo.findEarly(); //Selection query to Database: Shows all early access movies
	}
	

	
	@RequestMapping(method=RequestMethod.POST, value="/movies")
	public void updateMovie(@RequestBody int[][] returnArray) {
		/*
		 * Method for receiving changes to movie seating map
		 * [[UniqueMovieID], [Row1,Seat1], [Row2,Seat2]...]
		 */
		int movieIndex = returnArray[0][0]; //Grabs Movie ID
		int[][] changedSeats = new int[returnArray.length - 1][2]; //Creates new array for seating map
		System.arraycopy(returnArray, 1, changedSeats, 0, returnArray.length - 1); //Creates new array for seating map
		this.movieRepo.update(movieIndex, changedSeats); //Updates "database" with new seating map
		getMovies(); //Updates API to return accurate movie data
		
	}
		
		
		
	}


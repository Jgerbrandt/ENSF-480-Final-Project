/*
 * REPO Classes work as my test databases
 * ArrayList of objects that do simple operations
 * Will flag oeprations we need to add 
 */

package apiPackage.repo;

import apiPackage.model.Movie;
import apiPackage.model.Showtime;

import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MovieRepo {
	private List<Movie> list = new ArrayList<Movie>(); //List of normal showtimes
	private List<Movie> earlyList = new ArrayList<Movie>(); //List of early showtimes
	
	public List<Movie> findAll(){
		return this.list; //Returns all normal showtimes
	}
	
	public List<Movie> findEarly(){
		return this.earlyList; //Returns all early showtimes
	}
	
	
	public void save(Movie newMovie) {
		//When a new show is added, decides if it should save to normal times list or early times list
		//NEEDS FIX: Won't save to either if the new showtime isn't within the early threshhold
		//ALSO: Need to update as time passes, no clue how we'll work on that one tbh
		if(newMovie.isReleased()) {
			list.add(newMovie);
		}
		if(newMovie.isEarly()) {
			earlyList.add(newMovie);
		}
		
	}
	
	public Showtime findShowTime(int s) {
		for(int i = 0; i < list.size(); i++) {
			Movie tempMovie = list.get(i);
			for(int j = 0; j < tempMovie.getShowtimes().size(); j++) {
				Showtime tempShowTime = tempMovie.getShowtimes().get(j);
				if(tempShowTime.getID() == s) {
					return tempShowTime;
				}
			}
		}
		return null;
	}
	
	public void update(Showtime targetShowTime, int[][] seatMap) {
		/*
		 * NEEDS TO BE ADAPATED TO REAL DB:
		 * Receives unique movie index and new seatingmap array
		 * Updates Showtimes Seatingmap with new seats
		 */
		for(int i = 0; i < seatMap.length; i++) {
			targetShowTime.getSeats().buySeats(seatMap[i]);
		}
	}

}

/*
 * REPO Classes work as my test databases
 * ArrayList of objects that do simple operations
 * Will flag oeprations we need to add 
 */

package apiPackage.repo;

import apiPackage.model.Showtime;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ShowTimeRepo {
	private List<Showtime> list = new ArrayList<Showtime>(); //List of normal showtimes
	private List<Showtime> earlyList = new ArrayList<Showtime>(); //List of early showtimes
	
	public List<Showtime> findAll(){
		return this.list; //Returns all normal showtimes
	}
	
	public List<Showtime> findEarly(){
		return this.earlyList; //Returns all early showtimes
	}
	
	
	public void save(Showtime newShow) {
		//When a new show is added, decides if it should save to normal times list or early times list
		//NEEDS FIX: Won't save to either if the new showtime isn't within the early threshhold
		//ALSO: Need to update as time passes, no clue how we'll work on that one tbh
		if(newShow.isReleased()) {
			list.add(newShow);
		}
		if(newShow.isEarly()) {
			earlyList.add(newShow);
		}
		
	}
	
	public void update(int movieIndex, int[][] seatMap) {
		/*
		 * NEEDS TO BE ADAPATED TO REAL DB:
		 * Receives unique movie index and new seatingmap array
		 * Updates Showtimes Seatingmap with new seats
		 */
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == movieIndex) {
				Showtime updatedShow = list.get(i);
				for(int j = 0; j < seatMap.length; j++) {
					updatedShow.getSeats().buySeats(seatMap[j]);
				}
				
			}
					
		}
	}
	

}

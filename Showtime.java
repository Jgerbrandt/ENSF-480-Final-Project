/**
* Showtime class holds information for specific movie showings
* includes seat map of available seats
* and time information
*
*/

import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Showtime {
    public static int ShowtimeIDCounter;
    private int id;
    private int screen;
    private LocalDate showDate;
    private String time; //hh:mmam/pm
    SeatingMap seatMap;
	
	/**
	* ctor to create new showtimes for each movie
	*
	* @param id	unique showtime id
	* @param s	screen where movie is showing
	* @param sd	showtime date
	* @param t	showtime time
	* @param rd	release date of shown movie
	*
	*/
    public Showtime(int id, int s, String sd, String t, LocalDate rd){
        this.id = id;
        this.screen = s;
        this.time = t;
        setShowDate(sd);
        this.seatMap = new SeatingMap(rd);
    }

    //getters
    public int getScreen() { return this.screen; }
    public String getTime() {return this.time; }
    public SeatingMap getSeats() { return this.seatMap; }
    public int getID() {return this.id;}
    public LocalDate getShowDate(){return this.showDate;}
    public static int getCounter() { return ShowtimeIDCounter; }

    //setters
    public void setScreen(int screen){ this.screen = screen; }
    public void setTime(String time){ this.time = time; }
    public void setShowDate(String sd){ this.showDate = LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd-MM-yyyy")); }

    public void display() {
		System.out.println(id);
		System.out.println(screen);
		System.out.println(showDate.toString());
		System.out.println(time);
	}
}

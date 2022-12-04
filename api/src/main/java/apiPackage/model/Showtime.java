package apiPackage.model;

import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Showtime {
    private static int counter = 1;
    private int id;
    private int screen;
    private LocalDate showDate;
    private String time; //hh:mmam/pm
    SeatingMap seatMap;
    
    public Showtime() {}

    public Showtime(int s, String sd, String t){
        setID();
        this.screen = s;
        this.time = t;
        setShowDate(sd);
        this.seatMap = new SeatingMap();
    }

    //getters
    public int getScreen() { return this.screen; }
    public String getTime() {return this.time; }
    public SeatingMap getSeats() { return this.seatMap; }
    public int getID() {return this.id; }
    public LocalDate getShowDate(){return this.showDate;}

    //setters
    public void setScreen(int screen){ this.screen = screen; }
    public void setTime(String time){ this.time = time; }
    public void setShowDate(String sd){ this.showDate = LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd-MM-yyyy")); }
    public void setID(){
        this.id = counter;
        incrementCounter();
    }

    private static void incrementCounter(){
        counter++;
    }


}
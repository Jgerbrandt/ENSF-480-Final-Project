import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Showtime {
    private static int counter = 1;
    private int id;
    private String movie;
    private int screen;
    private LocalDate releaseDate; //dd-mm-yyyy
    private LocalDate earlyAccess;
    private String time; //hh:mmam/pm
    SeatingMap seatMap;

    public Showtime(String t, String m, int s, String rd){
        this.movie = m;
        this.screen = s;
        this.time = t;
        setReleaseDate(rd);
        setEarlyAccess(rd);
        this.seatMap = new SeatingMap();
        setID();
    }

    //getters
    public String getMovie() { return this.movie; }
    public int getScreen() { return this.screen; }
    public LocalDate getReleaseDate() { return this.releaseDate; }
    public LocalDate getEarlyAccess() { return this.earlyAccess; }
    public String getTime() {return this.time; }
    public SeatingMap getSeats() { return this.seatMap; }
    public int getID() {return this.id; }

    //setters
    public void setMovie(String movie){ this.movie = movie; }
    public void setScreen(int screen){ this.screen = screen; }
    public void setReleaseDate(String rd){ this.releaseDate = LocalDate.parse(rd, DateTimeFormatter.ofPattern("dd-MM-yyyy")); }
    public void setEarlyAccess(String ea){ this.earlyAccess = this.releaseDate.minusWeeks(1); }
    public void setTime(String time){ this.time = time; }
    public void setID(){
        this.id = counter;
        incrementCounter();
    }

    private static void incrementCounter(){
        counter++;
    }

    public boolean isReleased(){
        if (this.releaseDate.isAfter(LocalDate.now())){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isEarly(){
        if (this.earlyAccess.isAfter(LocalDate.now())){
            return false;
        }
        else {
            return true;
        }
    }
}
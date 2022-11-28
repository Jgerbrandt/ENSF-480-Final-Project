import java.lang.String;
import java.util.Date;

class Showtime {
    private final String MOVIE;
    private int screen;
    private Date releaseDate;
    private Date earlyAccess;
    SeatingMap seatMap;

    public Showtime(){};

    //getters
    public String getMovie() { return this.movie; }
    public int getScreen() { return this.screen; }
    public Date getReleaseDate() { return this.releaseDate; }
    public Date getEarlyAccess() { return this.earlyAccess; }
    public SeatingMap getSeatMap() { return this.seatMap; }

    //setters
    public void setMovie(String movie){ this.movie = movie; }
    public void setScreen(int screen){ this.screen = screen; }
    public void setReleaseDate(Date rd){ this.releaseDate = rd; }
    public void setEarlyAccess(Date ea){ this.earlyAccess = ea; }
}
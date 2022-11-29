import java.lang.String;

class Showtime {
    private String movie;
    private int screen;
    private String releaseDate; //dd-mm-yyyy
    private String earlyAccess;
    private String time; //hh-mm
    SeatingMap seatMap;

    public Showtime(String m, String s, String rd, String ea, String t){
        this.movie = m;
        this.screen = Integer.valueOf(s);
        this.releaseDate = rd;
        this.earlyAccess = ea;
        this.time = t;
        this.seatMap = new SeatingMap();
    }

    //getters
    public String getMovie() { return this.movie; }
    public int getScreen() { return this.screen; }
    public String getReleaseDate() { return this.releaseDate; }
    public String getEarlyAccess() { return this.earlyAccess; }
    public String getTime() {return this.time; }
    public SeatingMap getSeatMap() { return this.seatMap; }

    //setters
    public void setMovie(String movie){ this.movie = movie; }
    public void setScreen(int screen){ this.screen = screen; }
    public void setReleaseDate(String rd){ this.releaseDate = rd; }
    public void setEarlyAccess(String ea){ this.earlyAccess = ea; }
    public void setTime(String time){ this.time = time; }
}
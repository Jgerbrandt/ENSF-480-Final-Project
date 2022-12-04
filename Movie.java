import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class Movie {
    private String title;
    private LocalDate releaseDate; //dd-mm-yyyy
    private LocalDate earlyAccess;
    private List<Showtime> showtimes = new ArrayList<Showtime>();

    public Movie(String title, String rd){
        this.title = title;
        setReleaseDate(rd);
        setEarlyAccess();
    }

    public String getTitle() { return this.title; }
    public LocalDate getReleaseDate() { return this.releaseDate; }
    public LocalDate getEarlyAccess() { return this.earlyAccess; }
    public Showtime getShowtime(int index){ return this.showtimes.get(index);}
    public List<Showtime> getShowtimes(){ return this.showtimes; }

    public void setTitle(String t){ this.title = t; }
    public void setReleaseDate(String rd){ this.releaseDate = LocalDate.parse(rd, DateTimeFormatter.ofPattern("dd-MM-yyyy")); }
    //early access date is one week before release
    public void setEarlyAccess(){ this.earlyAccess = this.releaseDate.minusWeeks(1); }
    

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

    public void addShowtime(int id, int screen, String date, String time, LocalDate rd){
        Showtime newShow = new Showtime(id, screen, date, time, rd);
        this.showtimes.add(newShow);
    }

    public void display() {
		System.out.println(title);
		System.out.println(releaseDate.toString());
		System.out.println(earlyAccess.toString());
		for(int i =0; i < showtimes.size(); i++) {
			showtimes.get(i).display();
		}
	}
}

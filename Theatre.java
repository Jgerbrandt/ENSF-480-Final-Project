import java.util.Vector;
import java.lang.String;

class Theatre{
    private Vector<Showtime> showtimes;
    private Vector<String> movies;

    public Theatre(){}

    public Vector<Showtime> getShowtimes(){
        return this.showtimes;
    }

    public Vector<String> getMovies(){
        return this.movies();
    }
}
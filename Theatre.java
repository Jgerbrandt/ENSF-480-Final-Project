import java.util.Vector;

class Theatre{
    private Vector<Showtime> showtimes;
    private Vector<String> movies;

    public Theatre(){}

    public Vector<Showtime> getShowtimes(){
        return this.showtimes;
    }
}
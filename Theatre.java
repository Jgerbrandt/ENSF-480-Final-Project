import java.util.Vector;

class Theatre{
    private Vector<Showtime> showtimes;
    private Vector<String> movies;

    public Theatre(){
        //call to movie DB to fill?
    }

    public Vector<Showtime> getShowtimes(){
        return this.showtimes;
    }
}
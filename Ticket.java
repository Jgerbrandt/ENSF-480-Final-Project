import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Ticket {
    private int ticketID;
    private String movieName;
    private int seatColumn;
    private int seatRow;
    private int showtimeID;
    private Showtime showtime;
    public static int TicketIDCounter;

    public Ticket(int id, String name, int col, int row, int showtimeID, Showtime showtime) {
        this.ticketID = id;
        this.movieName = name;
        this.seatColumn = col;
        this.seatRow = row;
        this.showtimeID = showtimeID;
        this.showtime = showtime;
    }

    public void createTicket() throws FileNotFoundException {
        PrintWriter ticket = new PrintWriter("Ticket.txt");

        ticket.println("Movie: " + getMovieName());
        ticket.println("Screen: " + showtime.getScreen());
        ticket.println("Date: " + showtime.getShowDate());
        ticket.println("Time: " + showtime.getTime());
    }

    public int getTicketID() {
        return this.ticketID;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public int getSeatColumn() {
        return this.seatColumn;
    }

    public int getSeatRow() {
        return this.seatRow;
    }

    public int getshowtimeID() {
        return this.showtimeID;
    }

    public Showtime getShowtime() {
        return this.showtime;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public void setShowtimeID(int showtimeID) {
        this.showtimeID = showtimeID;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
}
import java.time.*;

public class Ticket {
    private int ticketID;
    private String movieName;
    private int seatColumn;
    private int seatRow;
    private Duration time;

    public static int TicketIDCounter;

    public Ticket(int id, String name, int col, int row, Duration time) {
        this.ticketID = id;
        this.movieName = name;
        this.seatColumn = col;
        this.seatRow = row;
        this.time = time;
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

    public Duration getTime() {
        return this.time;
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

    public void setTime(Duration time) {
        this.time = time;
    }

}
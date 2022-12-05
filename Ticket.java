/**
* ticket object to summarize user transaction 
* and showtime information
*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private int ticketID;
    private String movieName;
    private int screen;
    private int seatColumn;
    private int seatRow;
    private String time;
    private LocalDate showDate;
    private int showtimeID;
    public static int TicketIDCounter;
    
    /**
    * default ctor
    */
    public Ticket(){}

     /**
    * ctor for creating tickets from DB
    */
    public Ticket(int ticketID, String movie, int screen, int col, int row, String time, String date, int showtimeID) {
        this.ticketID = ticketID;
        this.movieName = movie;
        this.screen = screen;
        this.seatColumn = col;
        this.seatRow = row;
        this.time = time;
        this.showDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.showtimeID = showtimeID;
    }
    
    /**
    * create ticket file to send to user
    */
    public void createTicket() throws FileNotFoundException {
        String formattedDate = this.showDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        PrintWriter ticket = new PrintWriter("Ticket.txt");

        ticket.println("Movie: " + this.movieName);
        ticket.println("Screen: " + this.screen);
        ticket.println("Date: " + formattedDate);
        ticket.println("Time: " + this.time);

        ticket.close();
    }
    
    //getters
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

    public LocalDate getShowDate() {
        return this.showDate;
    }

    public int getshowtimeID() {
        return this.showtimeID;
    }

    public int getScreen(){
        return this.screen;
    }

    public String getTime(){
        return this.time;
    }
    
    //setters
    public void setTicketID() {
        this.ticketID = TicketIDCounter++;
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

}

/*
 * All classes in Model package are more or less copy pasted from classes you guys have built
 * Changes from Git: None
 */

/*
//NEW: 
 * TicketID -> ticketID
 * orderID
 * showtimeID
 * 
 */

package apiPackage.model;

public class Ticket {
    private int TicketID;
    private String movieName;
    private int seatColumn;
    private int seatRow;
    private Showtime showtime;

    public static int TicketIDCounter;


    public Ticket(int id, String name, int col, int row, Showtime showtime) {
        this.TicketID = id;
        this.movieName = name;
        this.seatColumn = col;
        this.seatRow = row;
        this.showtime = showtime;
    }


	public int getTicketID() {
		return TicketID;
	}


	public void setTicketID(int ticketID) {
		TicketID = ticketID;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public int getSeatColumn() {
		return seatColumn;
	}


	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}


	public int getSeatRow() {
		return seatRow;
	}


	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}


	public Showtime getTime() {
		return showtime;
	}


	public void setTime(Showtime time) {
		this.showtime = time;
	}


	public static int getTicketIDCounter() {
		return TicketIDCounter;
	}


	public static void setTicketIDCounter(int ticketIDCounter) {
		TicketIDCounter = ticketIDCounter;
	}
    


}
class Ticket {
    private int TicketID;
    private int showtimeID;
    private int seatColumn;
    private int seatRow;

    public static int TicketIDCounter;


    public Ticket(int id, int col, int row, int showtimeID) {
        this.TicketID = id;
        this.showtimeID = showtimeID;
        this.seatColumn = col;
        this.seatRow = row;
    }

}

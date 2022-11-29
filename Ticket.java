class Ticket {
    private int TicketID;
    private String movieName;
    private int seatColumn;
    private int seatRow;
    private int time;

    public static int TicketIDCounter;


    public Ticket(int id, String name, int col, int row, int time) {
        this.TicketID = id;
        this.movieName = name;
        this.seatColumn = col;
        this.seatRow = row;
        this.time = time;
    }


}
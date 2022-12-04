class Order {
    private int OrderID;
    private String email;
    private List<Ticket> tickets;


    public static int OrderIDCounter;

    public Order(int id, String email) {
        OrderID = id;
        this.email = email;
        tickets = new ArrayList<Ticket>();
    }


    public void addTicket(int id, int col, int row. int showtimeID) {
	    Ticket ticket = new Ticket(id,col,row,showtimeID);
        tickets.add(ticket);
    }

}

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

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

}
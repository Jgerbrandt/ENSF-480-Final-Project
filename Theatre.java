import java.util.*;


class Theatre {
	//showtime has time/name/screen
	private List<Movie> movies = new ArrayList<Movie>();
	
	//order has orderid/ticketid/movie name/seat number/showtime time
	private List<Order> orders = new ArrayList<Order>();
	
	private List<DiscountCode> discounts = new ArrayList<DiscountCode>();
	
	//string arraylist for the movienames
	
	//private LoginServer loginserver = LoginServer.getOnlyInstance();
	
	
	
	public Theatre(MovieDatabase theatredb) {
		movies = theatredb.readMovies();
		orders = theatredb.readOrders();	
		discounts = theatredb.readDiscountCodes();
		theatredb.setMaxOrderID();
		theatredb.setMaxTicketID();
		theatredb.setMaxShowtimeID();
		theatredb.setMaxCodeCounter();
		//theatredb.addTicketToDB(1,2,3,3,3);
	}	
	
	public void display() {
		for (int i = 0; i < movies.size(); i++) {
			System.out.println("Movie");
			movies.get(i).display();
		}
		for (int i = 0; i < orders.size(); i++) {
			System.out.println("Order");
			orders.get(i).display();
		}
		for (int i =0; i < discounts.size(); i++) {
			System.out.println("Discount");
			discounts.get(i).display();
		}
		System.out.println(Order.OrderIDCounter);
		System.out.println(Ticket.TicketIDCounter);
		System.out.println(Showtime.ShowtimeIDCounter);
		System.out.println(DiscountCode.CodeIDCounter);
	}
	
	public static void main(String[] args) {
		MovieDatabase theatredb = new MovieDatabase();
		Theatre thistheatre = new Theatre(theatredb);
		//thistheatre.display();
		UserController loginserver = UserController.getLoginInstance();
		//loginserver.displayUsers();
		Registered user = new Registered("john", "pass","1111-1111-1111-1111","school","email@email.com",true,"11-11-2022");
		/*Registered user2 = loginserver.verifyUser(user);
		user2.display();*/
		Registered user3 = loginserver.parseInput(user);
		user3.display();
		Registered user4 = loginserver.signUp(user);
		user4.display();
	}
	
}



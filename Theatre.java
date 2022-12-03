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
		//discounts = MovieDatabase.readDiscounts();
		theatredb.setMaxOrderID();
		theatredb.setMaxTicketID();
		theatredb.setMaxShowtimeID();
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
	}
	
	public static void main(String[] args) {
		MovieDatabase theatredb = new MovieDatabase();
		Theatre thistheatre = new Theatre(theatredb);
		thistheatre.display();
	}
	
}



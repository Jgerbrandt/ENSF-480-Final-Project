
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
}

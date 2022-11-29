import java.util.*;

class Theatre {
	//showtime has time/name/screen
	private List<Showtime> showtimes = new ArrayList<Showtimes>();
	
	//order has orderid/ticketid/movie name/seat number/showtime time
	private List<Order> orders = new ArrayList<Order>();
	
	//string arraylist for the movienames
	private List<String> movieNames = new ArrayList<String>();
	
	private LoginServer loginserver = LoginServer.getOnlyInstance();
	
	
	
	public Theatre() {
		showtimes = MovieDatabase.readShowtimes();
		orders = MovieDatabase.readOrders();	
		movieNames = MovieDatabase.getMovies();
	}
	
	
	
	
}

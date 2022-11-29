import java.util.*;

class Theatre {
	//showtime has time/name/screen
	private List<Showtime> showtimes = new ArrayList<Showtimes>();
	
	//order has orderid/ticketid/movie name/seat number/showtime time
	private List<Order> orders = new ArrayList<Order>();
	
	
	
	public Theatre() {
		showtimes = MovieDatabase.readShowtimes();
		orders = MovieDatabase.readOrders();	
	}
	
	
	
}

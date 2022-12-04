
import java.util.*;


class Theatre {
	//showtime has time/name/screen
	private List<Movie> movies = new ArrayList<Movie>();
	
	//order has orderid/ticketid/movie name/seat number/showtime time
	private List<Order> orders = new ArrayList<Order>();
	
	private List<DiscountCode> discounts = new ArrayList<DiscountCode>();
	
	
	private UserController loginserver;
	
	
	
	public Theatre(MovieDatabase theatredb) {
		movies = theatredb.readMovies();
		orders = theatredb.readOrders();	
		discounts = theatredb.readDiscountCodes();
		theatredb.setMaxOrderID();
		theatredb.setMaxTicketID();
		theatredb.setMaxShowtimeID();
		theatredb.setMaxCodeCounter();
		loginserver = UserController.getLoginInstance();
	}		

	public List<Movie> getReleasedMovies(){ 	
		ArrayList<Movie> released = new ArrayList<Movie>();
		for (Movie m : this.movies){
			if(m.isReleased()){
				released.add(m);
			}
		}
		return released;
	}

	public List<Movie> getEarlyMovies(){
		ArrayList<Movie> early = new ArrayList<Movie>();
		for(Movie m : this.movies){
			if(m.isEarly()){
				early.add(m);
			}
		}
		return early;
	}
}
package apiPackage.model;

import java.util.*;

import java.util.*;


public class Theatre {
    private static Theatre theatre;
    //showtime has time/name/screen
    private List<Movie> movies = new ArrayList<Movie>();
    
    //order has orderid/ticketid/movie name/seat number/showtime time
    private List<Order> orders = new ArrayList<Order>();
    
    private List<DiscountCode> discounts = new ArrayList<DiscountCode>();
    
    
    private UserController loginserver;
    
    public Theatre() {
    	
    }

    public void addMovie(Movie newMovie) {movies.add(newMovie);}
    public void addOrder(Order newOrder) {orders.add(newOrder); System.out.println("Added order with Info: " + newOrder.getEmail() + " " + newOrder.getOrderID());}
    public void addDiscount(DiscountCode newDiscount) {discounts.add(newDiscount);}
    
    public void removeOrder(int orderID) {
		for(int i = 0; i < orders.size(); i++) {
			if(orderID == orders.get(i).getOrderID()) {
				updateSeatmap(orders.get(i));
				orders.remove(i);
			}
		}
	}
    
    public void removeDiscount(int discountCode) {
		for(int i = 0; i < discounts.size(); i++) {
			if(discountCode == discounts.get(i).getCode()) {
				discounts.remove(i);
			}
		}
	}
    
    public Order findOrder(int orderID, String email) {
    	for(int i = 0; i < orders.size(); i++) {
			if(orderID == orders.get(i).getOrderID() && email.equals(orders.get(i).getEmail())) {
				return orders.get(i);
			}
		}
    	return null;
    }
    
    public DiscountCode findDiscount(int discountCode) {
		for(int i = 0; i < discounts.size(); i++) {
			if(discountCode == discounts.get(i).getCode()) {
				return discounts.get(i);
			}
		}
		return null;
	}
    
    public Showtime findShowTime(int ID) {
    	for(int i = 0; i < movies.size(); i++) {
    		Movie checkMovie = movies.get(i);
    		for(int j = 0; j < checkMovie.getShowtimes().size(); j++) {
    			Showtime checkShowtime = checkMovie.getShowtime(j);
    			if(checkShowtime.getID() == ID) {
    				return checkShowtime;
    			}
    		}
    	}
    	return null;
    }
    
    
    private Theatre(int testIntRemoveLater) {
        MovieDatabase theatredb = new MovieDatabase();
        movies = theatredb.readMovies();
        orders = theatredb.readOrders();    
        discounts = theatredb.readDiscountCodes();
        theatredb.setMaxOrderID();
        theatredb.setMaxTicketID();
        theatredb.setMaxShowtimeID();
        theatredb.setMaxCodeCounter();
        loginserver = UserController.getLoginInstance();
    }        
    
    
    public static Theatre getTheatre() {
        if (theatre == null) {
            theatre = new Theatre();
        }
        return theatre;
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

	public void updateSeatmap(Order order){
		Movie movie = null;
		Showtime st = null;
		for(int i = 0; i < movies.size(); i++){
			if(movies.get(i).getTitle().equals(order.getTickets().get(0).getMovieName())){
				movie = movies.get(i);
				System.out.println(movie.getTitle());
				break;
			}
		}
		for(int i = 0; i < movie.getShowtimes().size(); i++){
			if(order.getTickets().get(0).getshowtimeID() == movie.getShowtime(i).getID()){
				st = movie.getShowtime(i);
				break;
			}
		}
		for(int i = 0; i < order.getTickets().size(); i++){
			st.getSeats().cancelSeats(order.getTickets().get(i).getSeatColumn(), order.getTickets().get(i).getSeatRow());
		}
	}
}
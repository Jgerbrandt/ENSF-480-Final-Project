/**
*Boundary class to interact with SQl database
*Implements FileOperations interface
* Can add, remove, and access data in SQL DB
*/

import java.util.*;
import java.sql.*;
class MovieDatabase implements FileOperations {
	
	/**
	* reads all existing order IDs and sets static incrementer for future orders
	*/
	public void setMaxOrderID() {
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT MAX(OrderID) FROM orders");
			if (results.next()) {
				Order.OrderIDCounter = results.getInt(1);
			}
			selectAllStatement.close();
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured in maxOrder.");
		}		
	}
	
	/**
	* reads all existing ticket IDs and sets static incrementer for future tickets
	*/
	public void setMaxTicketID() {
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT MAX(TicketID) FROM ticket");
			if (results.next()) {
				Ticket.TicketIDCounter = results.getInt(1);
			}
			selectAllStatement.close();
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured in maxTicket.");
		}		
	}
	
	/**
	* reads all existing discount code IDs and sets static incrementer for future discount codes
	*/
	public void setMaxCodeCounter() {
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT MAX(Code) FROM discountcodes");
			if (results.next()) {
				DiscountCode.CodeIDCounter = results.getInt(1);
			}
			selectAllStatement.close();
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured in maxCode.");
		}		
	}
	
	/**
	* reads all existing showtime IDs and sets static incrementer for future showtimes
	*/
	public void setMaxShowtimeID() {
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT MAX(ShowtimeID) FROM showtime");
			if (results.next()) {
				Showtime.ShowtimeIDCounter = results.getInt(1);
			}
			selectAllStatement.close();
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured in maxShowtime.");
		}		
	}
	
	/**
	* reads all existing discount cdoes and instatiates opbjects for program manipulation
	*
	* @return 	list of DiscountCodes from database
	*/
	public List<DiscountCode> readDiscountCodes() {
		List<DiscountCode> discounts = new ArrayList<DiscountCode>();
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT * FROM discountcodes");
			while (results.next()) {
				DiscountCode disc = new DiscountCode(results.getInt("Code"),results.getDouble("Discount"),results.getString("ExpirationDate"));
				discounts.add(disc);
			}
			selectAllStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL exception has occured in readCodes.");
		}
		return discounts;		
	}
	
	/**
	* reads all existing movies and instatiates opbjects for program manipulation
	*
	* @return 	list of Movies from database
	*/
	public List<Movie> readMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT * FROM movie");
			String query = "SELECT * FROM showtime WHERE MovieName IN (SELECT MovieName FROM movie WHERE MovieName = ?)";
			Connection con = initializeConnection();
			PreparedStatement selectShowtimes = con.prepareStatement(query);
			while (results.next()) {
				String movieName = results.getString("MovieName");
				Movie movie = new Movie(movieName,results.getString("ReleaseDate"));
				selectShowtimes.setString(1, movieName);
				ResultSet showtimes = selectShowtimes.executeQuery();
				while (showtimes.next()) {
					movie.addShowtime(showtimes.getInt("ShowtimeID"),
					showtimes.getInt("Screen"),
					showtimes.getString("Date"),
					showtimes.getString("Time"), movie.getReleaseDate());
				}
				movies.add(movie);
			}
			selectShowtimes.close();
			selectAllStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL exception has occured in readMovies.");
		}
		return movies;
	}
	
	/**
	* adds new ticket to DB for long-term storage and future access
	*
	* @param ticket		ticket to be added to DB
	* @param orderID	order ID of corresponding order
	*/
	public void addTicketToDB(Ticket ticket, int orderID) {
		try {
			String query = "INSERT INTO ticket VALUES (?,?,?,?,?)";
			Connection con = initializeConnection();
			PreparedStatement insertStatement = con.prepareStatement(query);
			insertStatement.setInt(1, ticket.getTicketID());
			insertStatement.setInt(2, orderID);
			insertStatement.setInt(3, ticket.getSeatRow());
			insertStatement.setInt(4, ticket.getSeatColumn());
			insertStatement.setInt(5, ticket.getshowtimeID());
			insertStatement.executeUpdate();
			insertStatement.close();
			closeConnection(con);
		} catch (SQLException ex) {
			System.out.println("Could not insert ticket " + orderID);
		}	
	}
	
	/**
	* adds new order to DB for long-term storage and future access
	*
	* @param order		order to be added to DB
	*/
	public void addOrderToDB(Order order) {
		try {
			String query = "INSERT INTO orders VALUES (?,?,?,?)";
			Connection con = initializeConnection();
			PreparedStatement insertStatement = con.prepareStatement(query);
			insertStatement.setInt(1, order.getOrderID());
			insertStatement.setString(2, order.getEmail());
			insertStatement.setDouble(3, order.getPrice());
			insertStatement.setString(4, order.getRefundDate());
			insertStatement.executeUpdate();
			insertStatement.close();
			closeConnection(con);
			Iterator<Ticket> tickets = order.getTickets().iterator();
			while (tickets.hasNext()) {
				addTicketToDB(tickets.next(), order.getOrderID());	
			}
		} catch (SQLException ex) {
			System.out.println("Could not insert order " + order.getOrderID());
		}	
	}
	
	/**
	* adds new discount code to DB for long-term storage and future access
	*
	* @param dc		dicount code to be added to DB
	*/
	public void addDiscountCodeToDB(DiscountCode dc) {
		try {
			String query = "INSERT INTO discountcodes VALUES (?,?,?)";
			Connection con = initializeConnection();
			PreparedStatement insertStatement = con.prepareStatement(query);
			insertStatement.setInt(1, dc.getCode());
			insertStatement.setDouble(2, dc.getDiscount());
			insertStatement.setString(3, dc.getExp());
			insertStatement.executeUpdate();
			insertStatement.close();
			closeConnection(con);
		} catch (SQLException ex) {
			System.out.println("Could not insert discount " + dc.getCode());
		}
	}
	
	/**
	* reads all existing orders and instatiates opbjects for program manipulation
	*
	* @return 	list of Orders from database
	*/
	public List<Order> readOrders() {
		List<Order> orders = new ArrayList<Order>();
		try {
			Statement selectOrders = initializeConnection().createStatement();
			ResultSet results = selectOrders.executeQuery("SELECT * FROM orders");
			String query = "SELECT * FROM ticket WHERE OrderID IN (SELECT OrderID FROM orders WHERE OrderID = ?)";
			Connection con = initializeConnection();
			PreparedStatement selectTickets = con.prepareStatement(query);
			String query2 = "SELECT * FROM showtime WHERE ShowtimeID IN (SELECT ShowtimeID FROM showtime WHERE ShowtimeID = ?)";
			Connection con2 = initializeConnection();
			PreparedStatement selectShowtime = con2.prepareStatement(query2);
			while (results.next()) {
				int orderID = results.getInt("OrderID");
				Order order = new Order(orderID, results.getString("Email"), results.getDouble("Price"), results.getString("RefundDate"));
				selectTickets.setInt(1, orderID);
				ResultSet tickets = selectTickets.executeQuery();
				while (tickets.next()) {
					int showtimeID = tickets.getInt("ShowtimeID");
					selectShowtime.setInt(1, showtimeID);
					ResultSet showtime = selectShowtime.executeQuery();
					showtime.next();
					order.addTicket(tickets.getInt("TicketID"),
					showtime.getString("MovieName"),
					showtime.getInt("Screen"),
					tickets.getInt("SColumn"),
					tickets.getInt("SRow"),
					showtime.getString("Time"),
					showtime.getString("Date"),
					showtimeID);
				}
				orders.add(order);
			}
			selectShowtime.close();
			selectTickets.close();
			selectOrders.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL exception has occured in readOrders.");
		}
		return orders;
	}	
	
	/**
	* removes order from DB after cancellation
	*
	* @param id		id of order to be found and rmeoved from DB
	*/
	public void removeOrder(int id) {
		try {
			String query = "DELETE FROM Order WHERE OrderID = ?";
			Connection con = initializeConnection();
			PreparedStatement deleteStatement = con.prepareStatement(query);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
			deleteStatement.close();
			closeConnection(con);
		} catch (SQLException ex) {
			System.out.println("Could not remove order " + id);
		}
	}	
}

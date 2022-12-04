import java.util.*;
import java.sql.*;
class MovieDatabase implements FileOperations {
	
	public void setMaxOrderID() {
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT MAX(OrderID) FROM orders");
			if (results.next()) {
				Order.OrderIDCounter = results.getInt(1);
			}

		} catch (SQLException ex) {
			System.out.println("SQL exception has occured in maxOrder.");
		}		
	}
	
	public void setMaxTicketID() {
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT MAX(TicketID) FROM ticket");
			if (results.next()) {
				Ticket.TicketIDCounter = results.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured in maxTicket.");
		}		
	}
	
	public void setMaxShowtimeID() {
		try {
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT MAX(ShowtimeID) FROM showtime");
			if (results.next()) {
				Showtime.ShowtimeIDCounter = results.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured in maxShowtime.");
		}		
	}
	
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
					showtimes.getString("Time"));
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
	
	public List<Order> readOrders() {
		List<Order> orders = new ArrayList<Order>();
		try {
			Statement selectOrders = initializeConnection().createStatement();
			ResultSet results = selectOrders.executeQuery("SELECT * FROM orders");
			String query = "SELECT * FROM ticket WHERE OrderID IN (SELECT OrderID FROM orders WHERE OrderID = ?)";
			Connection con = initializeConnection();
			PreparedStatement selectTickets = con.prepareStatement(query);
			while (results.next()) {
				Order order;
				int orderID = results.getInt("OrderID");
				String email = results.getString("RegisteredEmail");
				if (results.wasNull()) {
					order = new Order(orderID, results.getString("OrdinaryEmail"));
				}
				else {
					order = new Order(orderID, email);
				}
				selectTickets.setInt(1, orderID);
				ResultSet tickets = selectTickets.executeQuery();
				// while (tickets.next()) {
				// 	order.addTicket(new Ticket(tickets.getInt("TicketID"),
				// 	tickets.getInt("SColumn"),
				// 	tickets.getInt("SRow"),
				// 	tickets.getInt("ShowtimeID")));
				// }
				orders.add(order);
			}
			selectTickets.close();
			selectOrders.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL exception has occured in readOrders.");
		}
		return orders;
	}	
	
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
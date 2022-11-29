
import java.util.*;

class MovieDatabase implements FileOperations {
	
	public static List<Showtime> readShowtimes() {
		try {
			List<Showtime> showtimes = new ArrayList<Showtime>();
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT * FROM Showtime");
			while (results.next()) {
				Showtime showtime = new Showtime(results.getInt("Time"), results.getString("MovieName"), results.getInt("ScreenNumber"), results.getString("ReleaseDate"));
				showtimes.add(showtime);
			}
			selectAllStatement.close();
			return showtimes;
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured.");
		}
	}
	
	public static List<Order> readOrders() {
		try {
			List<Order> orders = new ArrayList<Order>();
			Statement selectOrders = initializeConnection().createStatement();
			ResultSet orders = selectOrders.executeQuery("SELECT * FROM Order");
			String query = "SELECT * FROM Ticket WHERE OrderID IN (SELECT OrderID FROM Order WHERE OrderID = ?";
			Connection con = initializeConnection();
			PreparedStatement selectTickets = con.prepareStatement(query);
			while (orders.next()) {
				int orderID = results.getInt("OrderID");
				Order order = new Order(orderID, results.getString("Email"));
				selectTickets.setInt(1, orderID);
				ResultSet tickets = selectTickets.executeStatement();
				while (tickets.next()) {
					order.addTicket(new Ticket(tickets.getInt("TicketID"),
					tickets.getString("MovieName"),
					tickets.getInt("SeatColumn"),
					tickets.getInt("SeatRow");
					tickets.getInt("Time")));
				}
				selectTickets.close();
			}
			selectOrders.close();
			return orders;
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured.");
		}
	}	
	
	public static List<String> getMovieNames() {
		try {
			List<String> movieNames = new ArrayList<String>();
			Statement selectAllStatement = initializeConnection().createStatement();
			ResultSet results = selectAllStatement.executeQuery("SELECT DISTINCT MovieName FROM Showtime");
			while (results.next()) {
				movieNames.add(results.getString("MovieName"));
			}
			selectAllStatement.close();
			return movieNames;
		} catch (SQLException ex) {
			System.out.println("SQL exception has occured.");
		}
		
	}
	
	public static void removeOrder(int id) {
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

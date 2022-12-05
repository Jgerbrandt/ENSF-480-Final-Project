package apiPackage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UserDatabase implements FileOperations{
    /**
     * Uses SQL to add a User to the User Database
     * @param email
     * @param password
     * @param name
     * @param address
     * @param cardInfo
     */
    public void addUser(String email, String password, String name, String address, String cardInfo, String annualDate, boolean paidfee){
        try{
            String query = "INSERT INTO registereduser VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            Connection con = initializeConnection();
            PreparedStatement addStatement = con.prepareStatement(query);
            addStatement.setString(1, email);
            addStatement.setString(2, password);
            addStatement.setString(3, name);
            addStatement.setString(4, address);
            addStatement.setString(5, cardInfo);
            addStatement.setString(6, annualDate);
            addStatement.setBoolean(7, paidfee);
            addStatement.executeUpdate();
            addStatement.close();
            closeConnection(con);

        } catch (SQLException ex){
            System.out.println("Failed to add user" + name);
            ex.printStackTrace();
        }
    }
    
    public List<User> readRegisteredUsers() {
		List<User> userlist = new ArrayList<User>();
        try {
            Statement selectAllStatement = initializeConnection().createStatement();
            ResultSet results = selectAllStatement.executeQuery("SELECT * FROM registereduser");
            String query = "SELECT * FROM orders WHERE Email IN (SELECT Email FROM orders WHERE Email = ?)";
            Connection con = initializeConnection();
            PreparedStatement selectOrders = con.prepareStatement(query);
			String query2 = "SELECT * FROM showtime WHERE ShowtimeID IN (SELECT ShowtimeID FROM showtime WHERE ShowtimeID = ?)";
			Connection con2 = initializeConnection();
			PreparedStatement selectShowtime = con2.prepareStatement(query2);
			String query3 = "SELECT * FROM ticket WHERE OrderID IN (SELECT OrderID FROM orders WHERE OrderID = ?)";
			Connection con3 = initializeConnection();
			PreparedStatement selectTickets = con3.prepareStatement(query3);
            while (results.next()) {
                String email = results.getString("Email");
                User user = new User(results.getString("Name"),
                results.getString("Password"),
                results.getString("CardInfo"),
                results.getString("Address"),
                email,
                results.getBoolean("PaidFee"),
                results.getString("AnnualDate"));
                selectOrders.setString(1, email);
                ResultSet orders = selectOrders.executeQuery();
                while (orders.next()) {
                    Order order = new Order(orders.getInt("OrderID"),
                    orders.getString("Email"), orders.getDouble("Price"), orders.getString("RefundDate"));
					selectTickets.setInt(1, orders.getInt("OrderID"));
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
                    user.addOrder(order);
                }
                userlist.add(user); 
            }
            selectOrders.close();
            selectAllStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL exception has occured in readUsers.");
        }
        return userlist;
    }
    
    public void removeUser(String email) {
		try {
			String query = "DELETE FROM registereduser WHERE Email = ?";
			Connection con = initializeConnection();
			PreparedStatement deleteStatement = con.prepareStatement(query);
			deleteStatement.setString(1, email);
			deleteStatement.executeUpdate();
			deleteStatement.close();
			closeConnection(con);
		} catch (SQLException ex) {
			System.out.println("Could not remove user " + email);
		}
	}

    
    
    /**
     * Uses SQL to check if email and password matches a User in the User Database.
     * If no User is found will return null.
     * @param email 
     * @param password
     * @return
     */
     
    /*public static User matchUser(String email, String password){
        try{
            String query = "SELECT email, password FROM Registered_User WHERE email = ? AND password = ?";
            Statement selectMatchingUser = initializeConnection().createStatement();
            ResultSet result = selectMatchingUser.executeQuery(query);
            if(result.next()){
                User u = new Registered(result.getString("Name"), result.getString("CardInfo"), result.getString("Address"), result.getString("Email"), false, LocalDate.now(), null);
                selectMatchingUser.close();
                return u;
            }
            else{
                selectMatchingUser.close();
                return null;
            }
        } catch(SQLException ex){
            System.out.println("Failed to match user with email: " + email + " to User Database");
        }
    }*/
}
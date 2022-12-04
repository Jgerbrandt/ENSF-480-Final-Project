import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.time.LocalDate;

public class UserDatabase implements FileOperations{
    /**
     * Uses SQL to add a User to the User Database
     * @param email
     * @param password
     * @param name
     * @param address
     * @param cardInfo
     */
    public static void addUser(String email, String password, String name, String address, String cardInfo){
        try{
            String query = "INSERT INTO Registered_User VALUES ( ?, ?, ?, ?, ?)";
            Connection con = initializeConnection();
            PreparedStatement addStatement = con.prepareStatement(query);
            addStatement.setString(1, email);
            addStatement.setString(2, password);
            addStatement.setString(3, name);
            addStatement.setString(4, address);
            addStatement.setString(5, cardInfo);
            addStatement.executeUpdate();
            addStatement.close();
            closeConnection(con);

        } catch (SQLException ex){
            System.out.println("Failed to add user" + name);
        }
    }
    
    /**
     * Uses SQL to check if email and password matches a User in the User Database.
     * If no User is found will return null.
     * @param email 
     * @param password
     * @return
     */
    public static User matchUser(String email, String password){
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
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

class UserDatabase implements FileOperations{
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
}

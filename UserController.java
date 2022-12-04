import java.util.*;
import java.util.regex.*;

public class UserController{
    private static UserController loginInstance; 
    private List<User> users;

    private UserController(){
        users = new ArrayList<>();
    }

    public static UserController getLoginInstance(){
        if(loginInstance == null){
            loginInstance = new UserController();
        }

        return loginInstance;
    }

    /*
     * Verifies if User is registered
     */
    public User verifyUser(String email, String password){
        User u = UserDatabase.matchUser(email, password);
    }

    /*
     * Adds an Ordinary User to the User Database
     */
    public void signUp(String email, String password, String name, String address, String cardInfo){
        Matcher m = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
        if(m.find() && cardInfo.length() == 16){
            UserDatabase.addUser(email, password, name, address, cardInfo);
        }
        else{
            System.out.println("Unable to add User " + name + " because email or cardInfo is wrong");
        }
    }

    public void addUser(User u){
        users.add(u);
        //signUp(u.getEmail() )
    }

}
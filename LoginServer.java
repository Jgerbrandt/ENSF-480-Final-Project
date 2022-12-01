import java.util.*;
import java.util.regex.*;

class LoginServer{
    private static LoginServer loginInstance; 

    private LoginServer(){

    }

    public static LoginServer getLoginInstance(){
        if(loginInstance == null){
            loginInstance = new LoginServer();
        }

        return loginInstance;
    }

    /*
     * Verifies if User is registered
     */
    public boolean verifyUser(String email, String password){
        User u = UserDatabase.matchUser(email, password);

        if(u != null){
            return true;
        } 
        else{
            return false;
        }
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

}
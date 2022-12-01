import java.util.*;

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
        
        return false;
    }

    /*
     * Adds an Ordinary User to the User Database
     */
    public void signUp(String email, String password, String name, String address, String cardInfo){
        
    }

}
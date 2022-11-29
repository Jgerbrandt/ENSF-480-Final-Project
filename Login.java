class Login{
    private static Login loginInstance;

    private Login(){

    }

    public static Login getLoginInstance(){
        if(loginInstance == null){
            loginInstance = new Login();
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
    public void signUp(String email, String cardInfo, String password){

    }
}
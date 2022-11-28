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
    public void verifyUser(String user, String password){

    }
}
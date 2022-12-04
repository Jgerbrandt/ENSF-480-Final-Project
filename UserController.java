import java.util.*;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserController{
    private static UserController loginInstance; 
    private List<Registered> users;
    UserDatabase userdb;

    private UserController(){
		userdb = new UserDatabase();
        users = userdb.readRegisteredUsers();
    }

    public static UserController getLoginInstance(){
        if(loginInstance == null){
            loginInstance = new UserController();
        }
        return loginInstance;
    }

    /*
     * takes a user object with just user and password
     * iterate through to match, return the correct user
     */
    public Registered verifyUser(Registered user){
		Iterator<Registered> userIter = users.iterator();
		while(userIter.hasNext()) {
			Registered curuser = userIter.next();
			if (curuser.getEmail().equals(user.getEmail()) && curuser.getPassword().equals(user.getPassword())) {
				return curuser;
			}
		}
		user.setEmail(null);
		return user;
    }

    /*
     * parse the user arguments
     */
    public Registered parseInput(Registered user) {
		String EMAIL_REGEX = "^[A-Z,a-z,.,0-9]{0,20}@[A-Z,a-z]{0,20}.com$";
		String CARD_REGEX = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$";
		Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
		Pattern CARD_PATTERN = Pattern.compile(CARD_REGEX);
		Matcher emailMatcher = EMAIL_PATTERN.matcher(user.getEmail());
		Matcher cardMatcher = CARD_PATTERN.matcher(user.getCreditCardNum());
		if (!emailMatcher.find()) {
			user.setEmail(null);
			return user;
		}
		if (!cardMatcher.find()) {
			user.setEmail(null);
			return user;
		}
		Iterator<Registered> userIter = users.iterator();
		while(userIter.hasNext()) {
			Registered curuser = userIter.next();
			if (curuser.getEmail().equals(user.getEmail())) {
				user.setEmail(null);
				return user;
			}
		}
		return user;		
	}
     //take user without dates, return registered with dates
    public Registered signUp(Registered user){
		LocalDate lt = LocalDate.now();
		String annualDate = lt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		user.setRenewalDateString(annualDate);
		user.setRenewalDate();
		user.setIsPaid(true);
		users.add(user);
		userdb.addUser(user.getEmail(), user.getPassword(), user.getName(), user.getAddress(), user.getCreditCardNum(), annualDate, user.getIsPaid());
		return user;
    }
    
    public void displayUsers() {
		Iterator<Registered> userIter = users.iterator();
		while(userIter.hasNext()) {
			Registered curuser = userIter.next();
			curuser.display();
		}
	}

}

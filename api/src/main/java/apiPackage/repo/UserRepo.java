/*
 * REPO Classes work as my test databases
 * ArrayList of objects that do simple operations
 * Will flag oeprations we need to add 
 */

package apiPackage.repo;

import apiPackage.model.User;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepo {
	private List<User> list = new ArrayList<User>(); //Stand in "DB" arraylist
	
	
	public User find(String email, String password) { //Search database for user (LOGIN)
		for(int i = 0; i < list.size(); i++) {
			String tempEmail = list.get(i).getEmail();
			String tempPass = list.get(i).getPassword();
			if((tempEmail.equals(email)) && (tempPass.equals(password))) {
				return this.list.get(i);
			}
		}
		return null; //Returns null if no matching user
	}
	
	public void save(User newUser) {
		list.add(newUser); //INSERT in to DB
	}
	

}

package testes;

import br.com.model.Usuario;

public class UserSingleTon {
  
	static UserSingleTon sharedInstance;
	  
	private UserSingleTon() {
		 UserSingleTon.sharedInstance = UserSingleton();
	}
	
	private UserSingleTon UserSingleton() {
		return UserSingleTon.sharedInstance;
	}
	
	Usuario user;
}

//UserSingleton.sharedInstance.loggedUser = new User();

package main.Login;

import main.FacadeClasses.AdminFacade;
import main.FacadeClasses.ClientFacade;
import main.FacadeClasses.CompanyFacade;
import main.FacadeClasses.CustomerFacade;
/**
 * LoginManager Class
 * @author chelogetic
 * Purpose of this class is to establish a relation status between the user and the database. Whether the user is admin, company or a customer, he is given
 * a permission to apply specific changes the DB depending on the client type he is. LoginManager class authorizes the user to apply changes if the user
 * is a true client on the server, and, depending on the user's client type a specific set of methods.
 */
public class LoginManager {

	private AdminFacade adminFacade = new AdminFacade();
	private CompanyFacade companyFacade = new CompanyFacade();
	private CustomerFacade customerFacade = new CustomerFacade();

	private static LoginManager instance = new LoginManager();

	private LoginManager() {}

	public static LoginManager getInstance() {
		return instance;
	}
/**
 * 
 * @param email Client's email
 * @param password Client's email
 * @param clientType the type of the client
 * @return the type of the client
 * @throws Exception In case something goes wrong in any sort of interaction with the DB
 */
	public ClientFacade login(String email, String password, ClientType clientType) throws Exception {

		switch(clientType) {
		
		case ADMINISTRATOR: if(adminFacade.login(email, password) == true) {
			System.out.println("Access Granted, Administrator Type");
			return adminFacade;
		}
		else {
			System.out.println("Access Denied");
			return null;
		}

		case COMPANY: if(companyFacade.login(email, password) == true) {
			System.out.println("Access Granted, Company Type");
			return companyFacade;
		}
		else {
			System.out.println("Access Denied");
			return null;
		}

		case CUSTOMER: if(customerFacade.login(email, password) == true) {
			System.out.println("Access Granted, Customer Type");
			return customerFacade;
		}
		else {
			System.out.println("Access Denied");
			return null;
		}

		default : return null;

		}
	}
}	
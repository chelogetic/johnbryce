package main.DAO;
import java.util.ArrayList;

import main.Beans.Customer;

public interface CustomersDAO {
	
	boolean isCustomerExists(String email, String password) throws Exception;
	void addCustomer(Customer customer) throws Exception;
	void updateCustomer(Customer customer) throws Exception;
	void deleteCustomer(int customerID) throws Exception;
	ArrayList<Customer> getAllCustomers() throws Exception;
	Customer getOneCustomer(int customerID) throws Exception;
	int getCustomerIDByEmail(String email) throws Exception;
}
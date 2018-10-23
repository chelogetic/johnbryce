package main.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import main.Beans.Category;
import main.Beans.Coupon;
import main.Beans.Customer;
/**
 * CustomersDBDAO has methods through which we can make changes to the Customers Table in the DB.
 * @author chelogetic
 *
 */
public class CustomersDBDAO implements CustomersDAO {
	
	private ConnectionPool instance = ConnectionPool.getInstance();
	
	public boolean isCustomerExists(String email, String password) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT Count(*) AS Count FROM CUSTOMERS WHERE EMAIL=? AND PASSWORD=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					int count = resultSet.getInt("Count");
					return count == 1;
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}	
	}
	
	public void addCustomer(Customer customer) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "INSERT INTO CUSTOMERS(FIRST_NAME,LAST_NAME,EMAIL,PASSWORD) VALUES(?,?,?,?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, customer.getFirstName());
				preparedStatement.setString(2, customer.getLastName());
				preparedStatement.setString(3, customer.getEmail());
				preparedStatement.setString(4, customer.getPassword());
				preparedStatement.executeUpdate();
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					customer.setId(id);
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void updateCustomer(Customer customer) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "UPDATE CUSTOMERS SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PASSWORD=? WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, customer.getFirstName());
				preparedStatement.setString(2, customer.getLastName());
				preparedStatement.setString(3, customer.getEmail());
				preparedStatement.setString(4, customer.getPassword());
				preparedStatement.setInt(5, customer.getId());
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void deleteCustomer(int customerID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "DELETE FROM CUSTOMERS WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerID);
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public Customer getOneCustomer(int customerID) throws Exception {
		Connection connection = instance.getConnection();
		try {	
			String sql = "SELECT * FROM CUSTOMERS WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerID);
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					String firstName = resultSet.getString("FIRST_NAME");
					String lastName = resultSet.getString("LAST_NAME");
					String email = resultSet.getString("EMAIL");
					String password = resultSet.getString("PASSWORD");
					ArrayList<Coupon> coupons = getCouponsByCustomerID(customerID);
					Customer customer = new Customer(customerID, firstName, lastName , email, password, coupons);
					return customer;
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public ArrayList<Customer> getAllCustomers() throws Exception {
		Connection connection = instance.getConnection();
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		try {
			String sql = "SELECT * FROM CUSTOMERS";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					while(resultSet.next()) {
						int id = resultSet.getInt("ID");
						String firstName = resultSet.getString("FIRST_NAME");
						String lastName = resultSet.getString("LAST_NAME");
						String email = resultSet.getString("EMAIL");
						String password = resultSet.getString("PASSWORD");
						ArrayList<Coupon> coupons = getCouponsByCustomerID(id);
						Customer customer = new Customer(id, firstName, lastName, email, password, coupons);
						allCustomers.add(customer);
					}	
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
		return allCustomers;
	}
	
	private ArrayList<Coupon> getCouponsByCustomerID(int customerID) throws Exception {
		Connection connection = instance.getConnection();
		ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
		try {
			String sql = "SELECT * FROM CUSTOMERS_VS_COUPONS, COUPONS WHERE CUSTOMER_ID=? AND COUPON_ID=ID";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerID);
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					while(resultSet.next()) {
						int id = resultSet.getInt("ID");
						int companyID = resultSet.getInt("COMPANY_ID");
						int category = resultSet.getInt("CATEGORY_ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						LocalDate startDate = LocalDate.parse(resultSet.getString("START_DATE"));
						LocalDate endDate = LocalDate.parse(resultSet.getString("END_DATE"));
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String image = resultSet.getString("IMAGE");
						Coupon coupon = new Coupon(id,companyID, Category.intToCategory(category)
								, title, description, startDate, endDate,
								amount, price, image);
						allCoupons.add(coupon);
					}
				}
			}	
		}	
		finally {
			instance.restoreConnection(connection);
		}
		return allCoupons;
	}
	
	public int getCustomerIDByEmail(String email) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT ID FROM CUSTOMERS WHERE EMAIL=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, email);
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					int id = resultSet.getInt("ID");			
					return id;
				}	
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
}
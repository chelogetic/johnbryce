package main.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import main.Beans.Category;
import main.Beans.Company;
import main.Beans.Coupon;
/**
 * CompaniesDBDAO has methods through which we can make changes to the Companies Table in the DB.
 * @author chelogetic
 *
 */
public class CompaniesDBDAO implements CompaniesDAO {
	static private ConnectionPool instance = ConnectionPool.getInstance();
	public boolean isCompanyExists(String email, String password) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT Count(*) AS Count FROM COMPANIES WHERE EMAIL=? AND PASSWORD=?";
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
	
	public void addCompany(Company company) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "INSERT INTO COMPANIES(NAME, EMAIL, PASSWORD) VALUES(?,?,?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, company.getName());
				preparedStatement.setString(2, company.getEmail());
				preparedStatement.setString(3, company.getPassword());
				preparedStatement.executeUpdate();
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					company.setId(id);
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void updateCompany(Company company) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "UPDATE COMPANIES SET NAME=?, EMAIL=?, PASSWORD=? WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, company.getName());
				preparedStatement.setString(2, company.getEmail());
				preparedStatement.setString(3, company.getPassword());
				preparedStatement.setInt(4, company.getId());
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void deleteCompany(int companyID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "DELETE FROM COMPANIES WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, companyID);
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public ArrayList<Company> getAllCompanies() throws Exception {
		Connection connection = instance.getConnection();
		try {	
			String sql = "SELECT * FROM COMPANIES";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					ArrayList<Company> allCompanies = new ArrayList<Company>();
					while(resultSet.next()) {
						int id = resultSet.getInt("ID");
						String name = resultSet.getString("NAME");
						String email = resultSet.getString("EMAIL");
						String password = resultSet.getString("PASSWORD");
						ArrayList<Coupon> coupons = getCouponsByCompanyID(id);
						Company company = new Company(id, name, email, password, coupons);
						allCompanies.add(company);
					}
					return allCompanies;
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public Company getOneCompany(int companyID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT * FROM COMPANIES WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, companyID);
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
					String name = resultSet.getString("NAME");
					String email = resultSet.getString("EMAIL");
					String password = resultSet.getString("PASSWORD");
					ArrayList<Coupon> coupons = getCouponsByCompanyID(companyID);
					Company company = new Company(companyID, name, email, password, coupons);
					return company;
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	private ArrayList<Coupon> getCouponsByCompanyID(int companyID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT * FROM COUPONS WHERE COMPANY_ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, companyID);
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
					while(resultSet.next()) {
						int id = resultSet.getInt("ID");
						int categoryID = resultSet.getInt("CATEGORY_ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						LocalDate startDate = LocalDate.parse(resultSet.getString("START_DATE"));
						LocalDate endDate = LocalDate.parse(resultSet.getString("END_DATE"));
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String image = resultSet.getString("IMAGE");
						Coupon coupon = new Coupon(id,companyID, Category.intToCategory(categoryID)
								, title, description, startDate, endDate,
								amount, price, image);
						allCoupons.add(coupon);
					}
					return allCoupons;
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public int getCompanyIDByEmail(String email) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT ID FROM COMPANIES WHERE EMAIL=?";
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
	
	public boolean isCompanyIDExists(int id) throws Exception {
		Connection connection = instance.getConnection();
		try {	
			String sql = "SELECT Count(*) AS Count FROM COMPANIES WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, id);
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
	
	public boolean isCompanyNameExists(String name) throws Exception {
		Connection connection = instance.getConnection();
		try {	
			String sql = "SELECT Count(*) AS Count FROM COMPANIES WHERE NAME=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, name);
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
}
package main.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import main.Beans.Category;
import main.Beans.Coupon;
/**
 * CouponsDBDAO has methods through which we can make changes to the Coupons Table in the DB.
 * @author chelogetic
 *
 */
public class CouponsDBDAO implements CouponsDAO {
	
	static private ConnectionPool instance = ConnectionPool.getInstance();
	
	public void addCoupon(Coupon coupon) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "INSERT INTO COUPONS(COMPANY_ID,CATEGORY_ID,TITLE,DESCRIPTION,START_DATE, END_DATE,AMOUNT,PRICE,IMAGE) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setInt(1, coupon.getCompanyID());
				preparedStatement.setInt(2, Category.categoryToInt(coupon.getCategory()));
				preparedStatement.setString(3, coupon.getTitle());
				preparedStatement.setString(4, coupon.getDescription());
				preparedStatement.setString(5, coupon.getStartDate().toString());
				preparedStatement.setString(6, coupon.getEndDate().toString());
				preparedStatement.setInt(7, coupon.getAmount());
				preparedStatement.setDouble(8, coupon.getPrice());
				preparedStatement.setString(9, coupon.getImage());
				preparedStatement.executeUpdate();
				try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					coupon.setId(id);
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void updateCoupon(Coupon coupon) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "UPDATE COUPONS SET COMPANY_ID=?, CATEGORY_ID=?, TITLE=?, DESCRIPTION=?,"+
					" START_DATE=?, END_DATE=?, AMOUNT=?, PRICE=?, IMAGE=? WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, coupon.getCompanyID());
				preparedStatement.setInt(2, Category.categoryToInt(coupon.getCategory()));
				preparedStatement.setString(3, coupon.getTitle());
				preparedStatement.setString(4, coupon.getDescription());
				preparedStatement.setString(5, coupon.getStartDate().toString());
				preparedStatement.setString(6, coupon.getEndDate().toString());
				preparedStatement.setInt(7, coupon.getAmount());
				preparedStatement.setDouble(8, coupon.getPrice());
				preparedStatement.setString(9, coupon.getImage());
				preparedStatement.setInt(10, coupon.getId());
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void deleteCoupon(int couponID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "DELETE FROM COUPONS WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, couponID);
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public ArrayList<Coupon> getAllCoupons() throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT * FROM COUPONS";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
					while(resultSet.next()) {
						int id = resultSet.getInt("ID");
						int companyID = resultSet.getInt("COMPANY_ID");
						int categoryID = resultSet.getInt("CATEGORY_ID");
						String title = resultSet.getString("TITLE");
						String description = resultSet.getString("DESCRIPTION");
						LocalDate startDate = LocalDate.parse(resultSet.getString("START_DATE"));
						LocalDate endDate = LocalDate.parse(resultSet.getString("END_DATE"));
						int amount = resultSet.getInt("AMOUNT");
						double price = resultSet.getDouble("PRICE");
						String image = resultSet.getString("IMAGE");
						Coupon coupon = new Coupon(id, companyID, Category.intToCategory(categoryID), title, description, startDate, endDate, amount, price, image);
						allCoupons.add(coupon);
						System.out.println(coupon.getCategory());
					}
					return allCoupons;
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public Coupon getOneCoupon(int couponID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "SELECT * FROM COUPONS WHERE ID=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, couponID);
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					resultSet.next();
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
					Coupon coupon = new Coupon(id, companyID, Category.intToCategory(category), title, description, startDate, endDate, amount, price, image);
					return coupon;
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void addCouponPurchase(int customerID, int couponID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "INSERT INTO CUSTOMERS_VS_COUPONS(CUSTOMER_ID, COUPON_ID) VALUES(?, ?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerID);
				preparedStatement.setInt(2, couponID);
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public void deleteCouponPurchase(int customerID, int couponID) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "DELETE FROM CUSTOMERS_VS_COUPONS WHERE CUSTOMER_ID=%d AND COUPON_ID=%d";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerID);
				preparedStatement.setInt(2, couponID);
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
}
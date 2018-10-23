package main.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CreateDB {
	
	static private ConnectionPool instance = ConnectionPool.getInstance();
	
	public static void createCompaniesTableDB() throws SQLException, InterruptedException {
		try {
			try(Connection connection = instance.getConnection()) {
				String sql = "CREATE TABLE COMPANIES("+ 
						"ID INTEGER NOT NULL PRIMARY KEY "+
						"GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
						"NAME VARCHAR(30) NOT NULL,"+
						"EMAIL VARCHAR(50) NOT NULL,"+
						"PASSWORD VARCHAR(30) NOT NULL)";
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.executeUpdate();
					System.out.println("Companies Table Has Been Created");
				}
			}
		} catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
	}
	
	public static void createCustomerTableDB() throws SQLException, InterruptedException {
		try {
			try(Connection connection = instance.getConnection()) {
				String sql = "CREATE TABLE CUSTOMERS("+
						"ID INTEGER NOT NULL PRIMARY KEY "+
						"GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
						"FIRST_NAME VARCHAR(30) NOT NULL,"+
						"LAST_NAME VARCHAR(30) NOT NULL,"+
						"EMAIL VARCHAR(30) NOT NULL,"+
						"PASSWORD VARCHAR(30) NOT NULL)";
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.executeUpdate();
					System.out.println("Customers Table Has Been Created");
				}
			}
		}catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
	}
	
	public static void createCouponsTable() throws SQLException, InterruptedException {
		try {
			try(Connection connection = instance.getConnection()) {
				String sql = "CREATE TABLE COUPONS("+
						"ID INTEGER NOT NULL PRIMARY KEY "+
						"GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
						"COMPANY_ID INTEGER NOT NULL REFERENCES COMPANIES(ID) ON DELETE CASCADE," +
						"CATEGORY_ID INTEGER NOT NULL REFERENCES CATEGORIES(ID)," + 
						"TITLE VARCHAR(30) NOT NULL,"+
						"DESCRIPTION VARCHAR(100) NOT NULL,"+
						"START_DATE DATE NOT NULL,"+
						"END_DATE DATE NOT NULL,"+
						"AMOUNT INTEGER NOT NULL,"+
						"PRICE DOUBLE NOT NULL,"+
						"IMAGE VARCHAR(30) NOT NULL)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.executeUpdate();
				System.out.println("Coupons Table Has Been Created");
			}
		}catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
	}
	
	public static void createCustomersVsCoupons() throws SQLException, InterruptedException {
		try {
			try(Connection connection = instance.getConnection()) {
				String sql = "CREATE TABLE CUSTOMERS_VS_COUPONS(" + 
						"CUSTOMER_ID INTEGER REFERENCES CUSTOMERS(ID) ON DELETE CASCADE," +
						"COUPON_ID INTEGER REFERENCES COUPONS(ID) ON DELETE CASCADE," + 
						"PRIMARY KEY (CUSTOMER_ID, COUPON_ID))";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.executeUpdate();
				System.out.println("Customers Versus Coupons Table Has Been Created");
			}
		}catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
	}
	
	public static void createCategoriesTable() throws SQLException, InterruptedException {
		try {
			try(Connection connection = instance.getConnection()) {

				String sql = "CREATE TABLE CATEGORIES(" + 
				"ID INTEGER NOT NULL PRIMARY KEY " +  
				"GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
				"NAME VARCHAR(30) NOT NULL)";
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.executeUpdate();
					System.out.println("Categories Table Has Been Created");
				}
			}
		} catch(SQLException ex) {
			if(ex.getSQLState().equals("X0Y32")) {
				return;
			}
			throw ex;
		}
	}
}
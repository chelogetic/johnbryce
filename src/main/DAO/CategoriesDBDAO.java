package main.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Beans.Category;
import main.Testing.IDsAndCategories;
public class CategoriesDBDAO {
	
	static private ConnectionPool instance = ConnectionPool.getInstance();
	
	public void addCategory(Category category) throws Exception {
		Connection connection = instance.getConnection();
		try {
			String sql = "INSERT INTO CATEGORIES(NAME) VALUES(?)";		
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, Category.categoryToString(category));
				preparedStatement.executeUpdate();
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
	}
	
	public ArrayList<IDsAndCategories> getAllCategories() throws Exception {
		ArrayList<IDsAndCategories> allCategories = new ArrayList<IDsAndCategories>();
		Connection connection = instance.getConnection();
		try {
			connection = instance.getConnection();
			String sql = "SELECT * FROM CATEGORIES";
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				try(ResultSet resultSet = preparedStatement.executeQuery()) {
					while(resultSet.next()) {
						int id = resultSet.getInt("ID");
						String name = resultSet.getString("NAME");
						IDsAndCategories idsAndCategories = new IDsAndCategories(id, Category.stringToCategory(name));
						allCategories.add(idsAndCategories);
					}	
				}
			}
		}
		finally {
			instance.restoreConnection(connection);
		}
		return allCategories;
	}
}
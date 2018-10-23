package main.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Stack;
/**
 * Connection Pool's Class purpose is creating and delivering connections so we can use them to interact with DB.
 * 
 * @author chelogetic
 *
 */
public class ConnectionPool {
	/**
	 * @param connections Our connections' stack
	 */
	private Stack<Connection> connections = new Stack<>();
	/**
	 * @param connectionStringDB our connection string
	 */
	private static final String connectionStringDB = "jdbc:derby://localhost:1527/CouponsProject;create=true";

	private static ConnectionPool instance = new ConnectionPool();

	private ConnectionPool() {
		for(int i = 1; i <= 10; i++) {
			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver");
				Connection conn = DriverManager.getConnection(connectionStringDB);
				connections.push(conn);
			}
			catch (Exception ex) {
				System.out.println("Error: "+ex);
			}
		}
	}
	/**
	 * Get ConnectionPool's Instance.
	 * @return ConnectionPool's Instance.
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}
	/**
	 * 
	 * @return This method gives us connection when at least one connection is unused, it is pushed out of the stack and then we can interact with the DB.
	 * @throws InterruptedException
	 */
	public Connection getConnection() throws InterruptedException {

		synchronized(connections) {

			if(connections.isEmpty()) {
				connections.wait();
			}

			return connections.pop();
		}
	}
	/**
	 * Whenever we finish applying or doing any changes we want to the DB, we want the connection to get back to the stack.
	 */
	public void restoreConnection(Connection conn) {

		synchronized(connections) {
			connections.push(conn);
			connections.notify();
		}
	}
	/**
	 * This method gathers all connections' stack and closes all connection in that stack.
	 */
	public void closeAllConnection() throws InterruptedException {

		synchronized(connections) {

			while(connections.size() < 10) {
				connections.wait();
			}

			for (Connection conn : connections) {
				try { 
					conn.close(); 
				} catch (Exception ex) {
					System.out.println("Error: "+ex);
				}
			}			
		}
	}
}
import java.sql.*;

interface FileOperations {
	/**
	 * mySQL username
	 */
	static final String DBURL = "jdbc:mysql://localhost:3306/ensf480theatredatabase";
	/**
	 * mySQL username
	 */
	static final String USERNAME = "root";
	/**
	 * mySQL password
	 */
	static final String PASSWORD = "";

	/**
	 * Default method to connect to the inventory.
	 * 
	 * @return Connection dbConnect used for extracting information from inventory
	 *         and manipulating information in inventory.
	 */
	public default Connection initializeConnection() {

		try {
			Connection dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			return dbConnect;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL exception has occured in initConnect.");
			return null;
		}
	}

	public default void closeConnection(Connection dbConnect) {
		try {
			dbConnect.close();
		} catch (SQLException e) {

		}
	}


}
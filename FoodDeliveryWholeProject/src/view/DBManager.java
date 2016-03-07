package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBManager {

	private static DBManager instance;

	private DBManager() {
		final String url = "jdbc:mysql://localhost:3306/hr?useSSL=false";
		final String user = "root";
		final String pass = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error:No such driver imported!");
		}

		try (Connection conn = DriverManager.getConnection(url, user, pass);) {
			executeSqlScript(conn, new File("../DB/fd-db-scheme.sql"));
		} catch (SQLException e) {
			System.out.println("Error:Either something wrong in the connection object or wrong query syntax!");
		}

	}

	public static DBManager getInstance() throws ClassNotFoundException,
			SQLException, FileNotFoundException, IOException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public void executeSqlScript(Connection conn, File inputFile) {

		// Delimiter
		String delimiter = ";";

		// Create scanner
		try (Scanner scanner = new Scanner(inputFile).useDelimiter(delimiter);) {

			// Loop through the SQL file statements
			Statement currentStatement = null;
			while (scanner.hasNext()) {

				// Get statement
				String rawStatement = scanner.next() + delimiter;
				try {
					// Execute statement
					currentStatement = conn.createStatement();
					currentStatement.execute(rawStatement);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// Release resources
					if (currentStatement != null) {
						try {
							currentStatement.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					currentStatement = null;
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println("No such file in this directory!");
		}
	}
}

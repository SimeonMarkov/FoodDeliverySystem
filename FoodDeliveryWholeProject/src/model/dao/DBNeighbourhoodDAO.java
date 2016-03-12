package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Neighbourhood;
import model.User;
import model.db.DBManager;

public class DBNeighbourhoodDAO implements INeighbourhoodDAO{
	
	private static DBNeighbourhoodDAO instance;
	private DBManager manager;

	private DBNeighbourhoodDAO() {
		manager = DBManager.getInstance();
	}

	public static DBNeighbourhoodDAO getInstance() {
		if (instance == null) {
			instance = new DBNeighbourhoodDAO();
		}
		return instance;
	}

	@Override
	public List<String> getAllNeighbourhoods() throws SQLException {
		String query = "SELECT neighbourhood_name FROM fd_db.neighbourhood;";
		List<String> neighbourhoods = new ArrayList<>();
		Statement st = manager.getConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		System.out.println("result = " + result);
		if (result == null) {
			st.close();
			return neighbourhoods;
		}
		while (result.next()) {
			System.out.println("user added");
			neighbourhoods.add(result.getString(1));
		}
		st.close();
		return neighbourhoods;
	}


}

package model.dao;

import java.sql.PreparedStatement;
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
	private List<String> neighbourhoods;

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
		loadNeighbourhoods();
		String query = "SELECT neighbourhood_name FROM fd_db.Neighbourhood;";
		
		Statement st = manager.getConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		System.out.println("result = " + result);
		neighbourhoods = new ArrayList<String>();
		if (result == null) {
			st.close();
			return neighbourhoods;
		}
		while (result.next()) {
			neighbourhoods.add(result.getString(1));
		}
		st.close();
		return neighbourhoods;
	}
	
	public void loadNeighbourhoods(){
		String query = "INSERT INTO fd_db.Neighbourhood (neighbourhood_name) VALUES (?)";
		for(Neighbourhood n : Neighbourhood.values()){
			try (PreparedStatement stmt = manager.getConnection()
					.prepareStatement(query);) {
				stmt.setString(1, n.toString());
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

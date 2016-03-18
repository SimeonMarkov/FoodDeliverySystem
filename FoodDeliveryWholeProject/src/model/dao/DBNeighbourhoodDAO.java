package model.dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import model.Ingredient;
import model.Meal;
import model.Neighbourhood;
import model.User;
import model.db.DBManager;

public class DBNeighbourhoodDAO implements INeighbourhoodDAO {

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
	public ArrayList<Neighbourhood> getAllNeighbourhoods() throws SQLException {
		ArrayList<Neighbourhood> rv = new ArrayList<>();
		String query = "SELECT neighbourhood_id,neighbourhood_name FROM fd_db.Neighbourhood;";

		try (Statement st = manager.getConnection().createStatement()) {
			ResultSet result = st.executeQuery(query);
			System.out.println("result = " + result);

			if (result != null) {
				while (result.next()) {
					rv.add(new Neighbourhood().setId(result.getLong(1)).setName(result.getString(2)));
				}
			}
		}
		return rv;

	}

	@Override
	public Neighbourhood getNeighbourhoodByID(long id) {
		String query = "select neighbourhood_id,neighbourhood_name from neighbourhood where neighbourhood_id=?";
		Neighbourhood rv = null;
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)) {
			pst.setLong(1, id);
			ResultSet result = pst.executeQuery();
			if (result != null) {
				while (result.next()) {
					rv = new Neighbourhood().setId(result.getLong(1)).setName(result.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

}

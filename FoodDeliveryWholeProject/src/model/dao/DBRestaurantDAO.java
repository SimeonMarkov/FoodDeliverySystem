package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Restaurant;
import model.User;
import model.db.DBManager;

public class DBRestaurantDAO implements IRestaurantDAO {

	private static DBRestaurantDAO instance;
	private DBManager manager;

	private DBRestaurantDAO() {
		manager = DBManager.getInstance();
	}

	public static DBRestaurantDAO getInstance() {
		if (instance == null) {
			instance = new DBRestaurantDAO();
		}
		return instance;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		String query = String.join("\n",
				"SELECT r.restaurant_id,r.restaurant_name,r.photo,rt.restaurant_type_id,rt.restaurant_type_name",
				"FROM restaurant r join restaurant_by_type rbt on(rbt.restaurant_id=r.restaurant_id)",
				"join restaurant_type rt on (rbt.restaurant_type_id=rt.restaurant_type_id);");
		List<Restaurant> rv = new ArrayList<>();
		try (Statement st = manager.getConnection().createStatement()) {
			rv = getByResultSet(st.executeQuery(query));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;

	}

	@Override
	public List<Restaurant> getAllRestaurantsByType(long id) {
		String query = String.join("\n",
				"SELECT r.restaurant_id,r.restaurant_name,r.photo,rt.restaurant_type_id,rt.restaurant_type_name",
				"FROM restaurant r join restaurant_by_type rbt on(rbt.restaurant_id=r.restaurant_id)",
				"join restaurant_type rt on (rbt.restaurant_type_id=rt.restaurant_type_id)" ,
				"where EXISTS(select 1 from restaurant_by_type where restaurant_id = r.restaurant_id and restaurant_type_id=?);");
		List<Restaurant> rv = new ArrayList<>();
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)){
			pst.setLong(1, id);
			rv = getByResultSet(pst.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

	private List<Restaurant> getByResultSet(ResultSet result) throws SQLException {
		List<Restaurant> rv = new ArrayList<>();
		if (result == null) {
			return rv;
		}
		Restaurant r = null;
		Long oldID = -1l;
		while (result.next()) {
			if(result.getLong(1)!=oldID) {
				r = new Restaurant().setName(result.getString(2)).setRestId(result.getLong(1));
				oldID=result.getLong(1);
				rv.add(r);
			}
			r.addType(result.getString(5));
		}
		
		return rv;
	}

	@Override
	public List<Restaurant> getAllRestaurantsByHood(long id)  {
		String query = String.join("\n",
				"SELECT r.restaurant_id,r.restaurant_name,r.photo,rt.restaurant_type_id,rt.restaurant_type_name",
				"FROM restaurant r join restaurant_by_type rbt on(rbt.restaurant_id=r.restaurant_id)",
				"join restaurant_type rt on (rbt.restaurant_type_id=rt.restaurant_type_id)" ,
				"where EXISTS(select 1 from serviced_neighbourhoods where restaurant_id = r.restaurant_id and neighbourhood_id=?);");
		List<Restaurant> rv = new ArrayList<>();
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)){
			pst.setLong(1, id);
			rv = getByResultSet(pst.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

	@Override
	public List<Restaurant> getAllRestaurantsByHoodByType(long typeId, long hoodId)  {
		String query = String.join("\n",
				"SELECT r.restaurant_id,r.restaurant_name,r.photo,rt.restaurant_type_id,rt.restaurant_type_name",
				"FROM restaurant r join restaurant_by_type rbt on(rbt.restaurant_id=r.restaurant_id)",
				"join restaurant_type rt on (rbt.restaurant_type_id=rt.restaurant_type_id)" ,
				"where EXISTS(select 1 from serviced_neighbourhoods where restaurant_id = r.restaurant_id and neighbourhood_id=?)" ,
				"and EXISTS(select 1 from restaurant_by_type where restaurant_id = r.restaurant_id and restaurant_type_id=?);");
		List<Restaurant> rv = new ArrayList<>();
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)){
			pst.setLong(1, hoodId);
			pst.setLong(2, typeId);
			rv = getByResultSet(pst.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

}

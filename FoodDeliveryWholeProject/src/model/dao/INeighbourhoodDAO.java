package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.Neighbourhood;
import model.User;
import model.dao.INeighbourhoodDAO.DataSource;

public interface INeighbourhoodDAO {
	
	enum DataSource {
		DB, JSON, XML, CSV
	}
	
	List<String> getAllNeighbourhoods() throws SQLException;
	
	static INeighbourhoodDAO getDAO(DataSource ds) {
		switch (ds) {
		case DB:
			return DBNeighbourhoodDAO.getInstance();
		default:
			throw new IllegalArgumentException();
		}
	}
}

package com.delivery.dao.mysql;

import com.delivery.dao.CityDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCityDAO implements CityDAO {
	private static MySqlCityDAO instance;

	private MySqlCityDAO() {
	}

	public static synchronized MySqlCityDAO getInstance() {
		if (instance == null) {
			instance = new MySqlCityDAO();
		}
		return instance;
	}

	@Override
	public City getByPK(Connection connection, long id) throws SQLException {
		City city = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_CITY_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				city = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return city;
	}

	@Override
	public City getByCityTitleAndRegionId(Connection connection, String cityTitle, int regionId) throws SQLException {
		City city = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_CITY_BY_CITY_TITLE_AND_REGION_ID);
			int k = 0;
			st.setString(++k, cityTitle);
			st.setInt(++k, regionId);
			rs = st.executeQuery();
			if (rs.next()) {
				city = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return city;
	}

	@Override
	public List<City> getAll(Connection connection) throws SQLException {
		List<City> cities = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_CITY);
			while (rs.next()) {
				cities.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return cities;
	}

	@Override
	public List<City> getByRegionId(Connection connection, int regionId) throws SQLException {
		List<City> cities = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_CITY_BY_REGION_ID);
			st.setInt(1, regionId);
			rs = st.executeQuery();
			while (rs.next()) {
				cities.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return cities;
	}

	// returns inserted city with id
	@Override
	public City insert(Connection connection, City city) throws SQLException {
		City insertedCity = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.INSERT_INTO_CITY, Statement.RETURN_GENERATED_KEYS);
			int k = 0;
			st.setString(++k, city.getCityTitle());
			st.setInt(++k, city.getRegionId());
			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					// the inserted city will be returned
					insertedCity = getByPK(connection, rs.getInt(1));
				}
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return insertedCity;
	}

	private City parseResultSet(ResultSet rs) throws SQLException {
		City city = new City();
		int k = 0;
		city.setId(rs.getLong(++k));
		city.setCityTitle(rs.getString(++k));
		city.setRegionId(rs.getInt(++k));
		return city;
	}
}

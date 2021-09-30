package com.delivery.dao;

import com.delivery.entity.City;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CityDAO {
	City getByPK(Connection connection, long id) throws SQLException;

	City getByCityTitleAndRegionId(Connection connection, String cityTitle, int regionId) throws SQLException;

	List<City> getByRegionId(Connection connection, int regionId) throws SQLException;

	List<City> getAll(Connection connection) throws SQLException;

	List<City> getCities(Connection connection, String sortBy, int regionId, String itemsOnPage, int page) throws SQLException;

	City insert(Connection connection, City city) throws SQLException;
}

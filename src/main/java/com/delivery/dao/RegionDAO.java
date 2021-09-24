package com.delivery.dao;

import com.delivery.entity.Region;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RegionDAO {
	Region getByPK(Connection connection, int id) throws SQLException;

	Region getByRegionTitle(Connection connection, String regionTitle) throws SQLException;

	List<Region> getAll(Connection connection) throws SQLException;
}

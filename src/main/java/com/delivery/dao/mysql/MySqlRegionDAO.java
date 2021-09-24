package com.delivery.dao.mysql;

import com.delivery.dao.RegionDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRegionDAO implements RegionDAO {
	private static MySqlRegionDAO instance;

	private MySqlRegionDAO() {
	}

	public static synchronized MySqlRegionDAO getInstance() {
		if (instance == null) {
			instance = new MySqlRegionDAO();
		}
		return instance;
	}

	@Override
	public Region getByPK(Connection connection, int id) throws SQLException {
		Region region = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_REGION_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				region = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return region;
	}

	@Override
	public Region getByRegionTitle(Connection connection, String regionTitle) throws SQLException {
		Region region = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_REGION_BY_REGION_TITLE);
			st.setString(1, regionTitle);
			rs = st.executeQuery();
			if (rs.next()) {
				region = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return region;
	}

	@Override
	public List<Region> getAll(Connection connection) throws SQLException {
		List<Region> regions = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_REGION);
			while (rs.next()) {
				regions.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return regions;
	}

	private Region parseResultSet(ResultSet rs) throws SQLException {
		Region region = new Region();
		region.setId(rs.getInt(1));
		region.setRegionTitle(rs.getString(2));
		return region;
	}
}

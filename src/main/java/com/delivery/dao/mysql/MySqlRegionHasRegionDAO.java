package com.delivery.dao.mysql;

import com.delivery.dao.RegionHasRegionDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.RegionHasRegion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRegionHasRegionDAO implements RegionHasRegionDAO {
	private static MySqlRegionHasRegionDAO instance;

	private MySqlRegionHasRegionDAO() {
	}

	public static synchronized MySqlRegionHasRegionDAO getInstance() {
		if (instance == null) {
			instance = new MySqlRegionHasRegionDAO();
		}
		return instance;
	}

	@Override
	public RegionHasRegion getByPK(Connection connection, int regionId1, int regionId2) throws SQLException {
		RegionHasRegion regionHasRegion = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_REGION_HAS_REGION_BY_ID);
			int k = 0;
			if (regionId1 < regionId2) {
				st.setInt(++k, regionId1);
				st.setInt(++k, regionId2);
			} else {
				st.setInt(++k, regionId2);
				st.setInt(++k, regionId1);
			}
			rs = st.executeQuery();
			if (rs.next()) {
				regionHasRegion = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return regionHasRegion;
	}

	@Override
	public List<RegionHasRegion> getByTariffZoneId(Connection connection, int tariffZoneId) throws SQLException {
		List<RegionHasRegion> regionHasRegions = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_REGION_HAS_REGION_BY_TARIFF_ZONE);
			st.setInt(1, tariffZoneId);
			rs = st.executeQuery();
			while (rs.next()) {
				regionHasRegions.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return regionHasRegions;
	}

	@Override
	public List<RegionHasRegion> getAll(Connection connection) throws SQLException {
		List<RegionHasRegion> regionHasRegions = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_REGION_HAS_REGION);
			while (rs.next()) {
				regionHasRegions.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return regionHasRegions;
	}

	private RegionHasRegion parseResultSet(ResultSet rs) throws SQLException {
		RegionHasRegion regionHasRegion = new RegionHasRegion();
		int k = 0;
		regionHasRegion.setRegionId1(rs.getInt(++k));
		regionHasRegion.setRegionId2(rs.getInt(++k));
		regionHasRegion.setTariffZoneId(rs.getInt(++k));
		return regionHasRegion;
	}
}

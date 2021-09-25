package com.delivery.dao.mysql;

import com.delivery.dao.DAOFactory;
import com.delivery.dao.TariffZoneDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.RegionHasRegion;
import com.delivery.entity.TariffZone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTariffZoneDAO implements TariffZoneDAO {
	private static MySqlTariffZoneDAO instance;

	private MySqlTariffZoneDAO() {
	}

	public static synchronized MySqlTariffZoneDAO getInstance() {
		if (instance == null) {
			instance = new MySqlTariffZoneDAO();
		}
		return instance;
	}

	@Override
	public TariffZone getByPK(Connection connection, long id) throws SQLException {
		TariffZone tariffZone = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_TARIFF_ZONE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				tariffZone = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return tariffZone;
	}

	@Override
	public TariffZone getByTariffZoneTitle(Connection connection, TariffZone.TariffZoneTitle tariffZoneTitle) throws SQLException {
		TariffZone tariffZone = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_TARIFF_ZONE_BY_TARIFF_ZONE_TITLE);
			st.setString(1, tariffZoneTitle.toString());
			rs = st.executeQuery();
			if (rs.next()) {
				tariffZone = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return tariffZone;
	}

	@Override
	public List<TariffZone> getAll(Connection connection) throws SQLException {
		List<TariffZone> cities = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_TARIFF_ZONE);
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
	public TariffZone getByCityFromIdAndCityToId(Connection con, long cityFromId, long cityToId) throws SQLException {
		if (cityFromId == cityToId) {
			return DAOFactory.getDAOFactory().getTariffZoneDAO()
					.getByTariffZoneTitle(con, TariffZone.TariffZoneTitle.ZONE_CITY);
		}

		int regionFrom;
		int regionTo;
		RegionHasRegion regionHasRegion = null;

		regionFrom = DAOFactory.getDAOFactory().getCityDAO().getByPK(con, cityFromId).getRegionId();
		regionTo = DAOFactory.getDAOFactory().getCityDAO().getByPK(con, cityToId).getRegionId();

		// log
		System.out.println("regionFrom in getByCityFromIdAndCityToId ==> " + regionFrom);
		System.out.println("regionTo in getByCityFromIdAndCityToId ==> " + regionTo);

		if (regionFrom == regionTo) {
			return DAOFactory.getDAOFactory().getTariffZoneDAO()
					.getByTariffZoneTitle(con, TariffZone.TariffZoneTitle.ZONE_REGION);
		}

		regionHasRegion = DAOFactory.getDAOFactory().getRegionHasRegionDAO().getByPK(
				con, regionFrom, regionTo);

		// log
		System.out.println("regionHasRegion in getByCityFromIdAndCityToId ==> " + regionHasRegion);

		return DAOFactory.getDAOFactory().getTariffZoneDAO().getByPK(con, regionHasRegion.getTariffZoneId());
	}

	private TariffZone parseResultSet(ResultSet rs) throws SQLException {
		TariffZone tariffZone = new TariffZone();
		int k = 0;
		tariffZone.setId(rs.getLong(++k));
		tariffZone.setTariffZoneTitle(TariffZone.TariffZoneTitle.fromString(rs.getString(++k)));
		tariffZone.setTerm(rs.getInt(++k));
		return tariffZone;
	}
}

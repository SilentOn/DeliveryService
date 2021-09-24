package com.delivery.dao.mysql;

import com.delivery.dao.TariffZoneHasWeightTariffDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.TariffZoneHasWeightTariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTariffZoneHasWeightTariffDAO implements TariffZoneHasWeightTariffDAO {
	private static MySqlTariffZoneHasWeightTariffDAO instance;

	private MySqlTariffZoneHasWeightTariffDAO() {
	}

	public static synchronized MySqlTariffZoneHasWeightTariffDAO getInstance() {
		if (instance == null) {
			instance = new MySqlTariffZoneHasWeightTariffDAO();
		}
		return instance;
	}

	@Override
	public TariffZoneHasWeightTariff getByPK(Connection connection, long tariffZoneId, long weightTariffId) throws SQLException {
		TariffZoneHasWeightTariff tariffZoneHasWeightTariff = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_TARIFF_ZONE_HAS_WEIGHT_TARIFF_BY_ID);
			int k = 0;
			st.setLong(++k, tariffZoneId);
			st.setLong(++k, weightTariffId);
			rs = st.executeQuery();
			if (rs.next()) {
				tariffZoneHasWeightTariff = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return tariffZoneHasWeightTariff;
	}

	@Override
	public List<TariffZoneHasWeightTariff> getAll(Connection connection) throws SQLException {
		List<TariffZoneHasWeightTariff> tariffZoneHasWeightTariffs = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_TARIFF_ZONE_HAS_WEIGHT_TARIFF);
			while (rs.next()) {
				tariffZoneHasWeightTariffs.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return tariffZoneHasWeightTariffs;
	}

	private TariffZoneHasWeightTariff parseResultSet(ResultSet rs) throws SQLException {
		TariffZoneHasWeightTariff tariffZoneHasWeightTariff = new TariffZoneHasWeightTariff();
		int k = 0;
		tariffZoneHasWeightTariff.setTariffZoneId(rs.getLong(++k));
		tariffZoneHasWeightTariff.setWeightTariffId(rs.getLong(++k));
		tariffZoneHasWeightTariff.setPrice(rs.getFloat(++k));
		return tariffZoneHasWeightTariff;
	}
}

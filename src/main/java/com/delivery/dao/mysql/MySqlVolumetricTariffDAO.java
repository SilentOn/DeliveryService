package com.delivery.dao.mysql;

import com.delivery.dao.VolumetricTariffDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.VolumetricTariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVolumetricTariffDAO implements VolumetricTariffDAO {
	private static MySqlVolumetricTariffDAO instance;

	private MySqlVolumetricTariffDAO() {
	}

	public static synchronized MySqlVolumetricTariffDAO getInstance() {
		if (instance == null) {
			instance = new MySqlVolumetricTariffDAO();
		}
		return instance;
	}

	@Override
	public VolumetricTariff getByPK(Connection connection, int id) throws SQLException {
		VolumetricTariff volumetricTariff = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_VOLUMETRIC_TARIFF_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				volumetricTariff = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return volumetricTariff;
	}

	@Override
	public List<VolumetricTariff> getAll(Connection connection) throws SQLException {
		List<VolumetricTariff> volumetricTariffs = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_VOLUMETRIC_TARIFF);
			while (rs.next()) {
				volumetricTariffs.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return volumetricTariffs;
	}

	private VolumetricTariff parseResultSet(ResultSet rs) throws SQLException {
		VolumetricTariff volumetricTariff = new VolumetricTariff();
		int k = 0;
		volumetricTariff.setId(rs.getInt(++k));
		volumetricTariff.setVolume(rs.getInt(++k));
		volumetricTariff.setPrice(rs.getFloat(++k));
		return volumetricTariff;
	}
}

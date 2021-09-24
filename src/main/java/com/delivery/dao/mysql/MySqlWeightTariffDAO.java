package com.delivery.dao.mysql;

import com.delivery.dao.WeightTariffDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.WeightTariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlWeightTariffDAO implements WeightTariffDAO {
	private static MySqlWeightTariffDAO instance;

	private MySqlWeightTariffDAO() {
	}

	public static synchronized MySqlWeightTariffDAO getInstance() {
		if (instance == null) {
			instance = new MySqlWeightTariffDAO();
		}
		return instance;
	}

	@Override
	public WeightTariff getByPK(Connection connection, int id) throws SQLException {
		WeightTariff weightTariff = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_WEIGHT_TARIFF_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				weightTariff = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return weightTariff;
	}

	@Override
	public List<WeightTariff> getAll(Connection connection) throws SQLException {
		List<WeightTariff> weightTariffs = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_WEIGHT_TARIFF);
			while (rs.next()) {
				weightTariffs.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return weightTariffs;
	}

	private WeightTariff parseResultSet(ResultSet rs) throws SQLException {
		WeightTariff weightTariff = new WeightTariff();
		int k = 0;
		weightTariff.setId(rs.getInt(++k));
		weightTariff.setWeight(rs.getFloat(++k));
		return weightTariff;
	}
}

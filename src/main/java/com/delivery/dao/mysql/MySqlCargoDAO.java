package com.delivery.dao.mysql;

import com.delivery.dao.CargoDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.Cargo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCargoDAO implements CargoDAO {
	private static MySqlCargoDAO instance;

	private MySqlCargoDAO() {
	}

	public static synchronized MySqlCargoDAO getInstance() {
		if (instance == null) {
			instance = new MySqlCargoDAO();
		}
		return instance;
	}

	@Override
	public Cargo getByPK(Connection connection, int id) throws SQLException {
		Cargo cargo = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_CARGO_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				cargo = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return cargo;
	}

	@Override
	public Cargo getByType(Connection connection, Cargo.Type type) throws SQLException {
		Cargo cargo = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_CARGO_BY_TYPE);
			st.setString(1, type.toString());
			rs = st.executeQuery();
			if (rs.next()) {
				cargo = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return cargo;
	}

	@Override
	public List<Cargo> getAll(Connection connection) throws SQLException {
		List<Cargo> cargos = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_CARGO);
			while (rs.next()) {
				cargos.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return cargos;
	}

	private Cargo parseResultSet(ResultSet rs) throws SQLException {
		Cargo cargo = new Cargo();
		cargo.setId(rs.getInt(1));
		cargo.setType(Cargo.Type.fromString(rs.getString(2)));
		return cargo;
	}
}

package com.delivery.dao.mysql;

import com.delivery.dao.UserDetailsDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.UserDetails;

import java.sql.*;

public class MySQLUserDetailsDAO implements UserDetailsDAO {
	private static MySQLUserDetailsDAO instance;

	private MySQLUserDetailsDAO() {
	}

	public static synchronized MySQLUserDetailsDAO getInstance() {
		if (instance == null) {
			instance = new MySQLUserDetailsDAO();
		}
		return instance;
	}

	@Override
	public UserDetails getByPK(Connection connection, long id) throws SQLException {
		UserDetails userDetails = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_USER_DETAILS_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				userDetails = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return userDetails;
	}

	@Override
	public UserDetails getByEmail(Connection connection, String email) throws SQLException {
		UserDetails userDetails = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_USER_DETAILS_BY_EMAIL);
			st.setString(1, email);
			rs = st.executeQuery();
			if (rs.next()) {
				userDetails = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return userDetails;
	}

	@Override
	public void insert(Connection connection, UserDetails userDetails, long userId) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.INSERT_INTO_USER_DETAILS);
			int k = 0;
			st.setLong(++k, userId);
			st.setString(++k, userDetails.getEmail());
			st.setString(++k, userDetails.getFirstName());
			st.setString(++k, userDetails.getLastName());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	@Override
	public void update(Connection connection, UserDetails userDetails) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.UPDATE_USER_DETAILS);
			int k = 0;
			st.setString(++k, userDetails.getEmail());
			st.setString(++k, userDetails.getFirstName());
			st.setString(++k, userDetails.getLastName());
			st.setLong(++k, userDetails.getId());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	private UserDetails parseResultSet(ResultSet rs) throws SQLException {
		UserDetails userDetails = new UserDetails();
		int k = 0;
		userDetails.setId(rs.getLong(++k));
		userDetails.setEmail(rs.getString(++k));
		userDetails.setFirstName(rs.getString(++k));
		userDetails.setLastName(rs.getString(++k));
		return userDetails;
	}
}

package com.delivery.dao.mysql;


import com.delivery.dao.UserDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySqlUserDAO implements UserDAO {
	private static MySqlUserDAO instance;

	private MySqlUserDAO() {
	}

	public static synchronized MySqlUserDAO getInstance() {
		if (instance == null) {
			instance = new MySqlUserDAO();
		}
		return instance;
	}

	@Override
	public User getByPK(Connection connection, long id) throws SQLException {
		User user = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_USER_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				user = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return user;
	}

	@Override
	public User getByPhoneNumber(Connection connection, String phoneNumber) throws SQLException {
		User user = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_USER_BY_PHONE_NUMBER);
			st.setString(1, phoneNumber);
			rs = st.executeQuery();
			if (rs.next()) {
				user = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return user;
	}

	@Override
	public boolean validate(Connection connection, String phoneNumber, String password) throws SQLException {
		boolean status;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_USER_BY_PHONE_NUMBER_AND_PASSWORD);
			st.setString(1, phoneNumber);
			st.setString(2, password);
			rs = st.executeQuery();
			status = rs.next();
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return status;
	}

	@Override
	public List<User> getAll(Connection connection) throws SQLException {
		List<User> users = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_USER);
			while (rs.next()) {
				users.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return users;
	}

	// returns inserted user with id
	@Override
	public User insert(Connection connection, User user) throws SQLException {
		User insertedUser = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.INSERT_INTO_USER, Statement.RETURN_GENERATED_KEYS);
			int k = 0;
			st.setString(++k, user.getPhoneNumber());
			st.setString(++k, user.getPassword());
			st.setInt(++k, user.getRoleId());
			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					// the inserted user will be returned
					insertedUser = getByPK(connection, rs.getLong(1));
				}
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return insertedUser;
	}

	@Override
	public void update(Connection connection, User user) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.UPDATE_USER);
			int k = 0;
			st.setString(++k, user.getPassword());
			st.setInt(++k, user.getRoleId());
			st.setLong(++k, user.getId());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	@Override
	public void delete(Connection connection, User user) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.DELETE_USER);
			st.setLong(1, user.getId());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	private User parseResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(1));
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setPassword(rs.getString("password"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}
}
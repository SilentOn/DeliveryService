package com.delivery.dao.mysql;

import com.delivery.dao.RoleDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoleDAO implements RoleDAO {
	private static MySqlRoleDAO instance;

	private MySqlRoleDAO() {
	}

	public static synchronized MySqlRoleDAO getInstance() {
		if (instance == null) {
			instance = new MySqlRoleDAO();
		}
		return instance;
	}

	@Override
	public Role getByPK(Connection connection, int id) throws SQLException {
		Role role = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_ROLE_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				role = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return role;
	}

	@Override
	public Role getByName(Connection connection, Role.RoleName name) throws SQLException {
		Role role = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_ROLE_BY_NAME);
			st.setString(1, name.toString());
			rs = st.executeQuery();
			if (rs.next()) {
				role = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return role;
	}

	@Override
	public List<Role> getAll(Connection connection) throws SQLException {
		List<Role> roles = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_ROLE);
			while (rs.next()) {
				roles.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return roles;
	}

	private Role parseResultSet(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt(1));
		role.setName(Role.RoleName.fromString(rs.getString(2)));
		return role;
	}
}

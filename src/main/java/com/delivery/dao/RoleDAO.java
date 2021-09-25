package com.delivery.dao;

import com.delivery.entity.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
	Role getByPK(Connection connection, int id) throws SQLException;

	Role getByName(Connection connection, Role.RoleName name) throws SQLException;

	List<Role> getAll(Connection connection) throws SQLException;
}

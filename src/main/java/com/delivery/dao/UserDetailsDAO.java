package com.delivery.dao;

import com.delivery.entity.UserDetails;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDetailsDAO {
	UserDetails getByPK(Connection connection, long id) throws SQLException;

	UserDetails getByEmail(Connection connection, String email) throws SQLException;

	void insert(Connection connection, UserDetails userDetails, long userId) throws SQLException;

	void update(Connection connection, UserDetails userDetails) throws SQLException;
}
package com.delivery.dao;

import com.delivery.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
	User getByPK(Connection connection, long id) throws SQLException;

	User getByPhoneNumber(Connection connection, String phoneNumber) throws SQLException;

	boolean validate(Connection connection, String phoneNumber, String password) throws SQLException;

	List<User> getAll(Connection connection) throws SQLException;

	User insert(Connection connection, User user) throws SQLException;

	void update(Connection connection, User user) throws SQLException;

	void delete(Connection connection, User user) throws SQLException;
}
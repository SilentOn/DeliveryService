package com.delivery.dao;

import com.delivery.entity.Cargo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CargoDAO {
	Cargo getByPK(Connection connection, int id) throws SQLException;

	Cargo getByType(Connection connection, Cargo.Type type) throws SQLException;

	List<Cargo> getAll(Connection connection) throws SQLException;
}

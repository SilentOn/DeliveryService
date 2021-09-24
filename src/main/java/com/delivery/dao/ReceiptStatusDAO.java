package com.delivery.dao;

import com.delivery.entity.ReceiptStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReceiptStatusDAO {
	ReceiptStatus getByPK(Connection connection, int id) throws SQLException;

	ReceiptStatus getByName(Connection connection, ReceiptStatus.Status name) throws SQLException;

	List<ReceiptStatus> getAll(Connection connection) throws SQLException;
}

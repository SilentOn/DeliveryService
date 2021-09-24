package com.delivery.dao;

import com.delivery.entity.InvoiceStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InvoiceStatusDAO {
	InvoiceStatus getByPK(Connection connection, int id) throws SQLException;

	InvoiceStatus getByName(Connection connection, InvoiceStatus.Status name) throws SQLException;

	List<InvoiceStatus> getAll(Connection connection) throws SQLException;
}

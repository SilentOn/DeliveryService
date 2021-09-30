package com.delivery.dao;

import com.delivery.entity.Invoice;
import com.delivery.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InvoiceDAO {
	Invoice getByPK(Connection connection, long id) throws SQLException;

	List<Invoice> getByUser(Connection connection, long userId) throws SQLException;

	List<Invoice> getAll(Connection connection) throws SQLException;

	Invoice insert(Connection connection, Invoice invoice) throws SQLException;

	void process(Connection connection, Invoice invoice) throws SQLException;

	void update(Connection connection, Invoice invoice) throws SQLException;

	List<Invoice> getInvoices(Connection connection, User user, String sortBy, int statusId, String itemsOnPage, int page) throws SQLException;
}
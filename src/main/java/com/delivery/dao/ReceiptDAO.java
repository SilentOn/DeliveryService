package com.delivery.dao;

import com.delivery.entity.Receipt;
import com.delivery.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReceiptDAO {
	Receipt getByPK(Connection connection, long id) throws SQLException;

	List<Receipt> getByReceiptStatusId(Connection connection, int receiptStatusId) throws SQLException;

	List<Receipt> getAll(Connection connection) throws SQLException;

	void insert(Connection connection, Receipt receipt) throws SQLException;

	void update(Connection connection, Receipt receipt) throws SQLException;

	List<Receipt> getReceipts(Connection connection, User user, String sortBy, int statusId, String itemsOnPage, int page) throws SQLException;
}

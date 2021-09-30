package com.delivery.dao.mysql;

import com.delivery.dao.ReceiptDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.Receipt;
import com.delivery.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlReceiptDAO implements ReceiptDAO {
	private static MySqlReceiptDAO instance;

	private MySqlReceiptDAO() {
	}

	public static synchronized MySqlReceiptDAO getInstance() {
		if (instance == null) {
			instance = new MySqlReceiptDAO();
		}
		return instance;
	}

	@Override
	public Receipt getByPK(Connection connection, long id) throws SQLException {
		Receipt receipt = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_RECEIPT_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				receipt = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return receipt;
	}

	@Override
	public List<Receipt> getAll(Connection connection) throws SQLException {
		List<Receipt> receipts = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_RECEIPT);
			while (rs.next()) {
				receipts.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return receipts;
	}

	@Override
	public List<Receipt> getByReceiptStatusId(Connection connection, int receiptStatusId) throws SQLException {
		List<Receipt> receipts = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_RECEIPT_BY_RECEIPT_STATUS_ID);
			st.setInt(1, receiptStatusId);
			rs = st.executeQuery();
			while (rs.next()) {
				receipts.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return receipts;
	}

	// returns inserted receipt with id
	@Override
	public void insert(Connection connection, Receipt receipt) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.INSERT_INTO_RECEIPT);
			int k = 0;
			st.setLong(++k, receipt.getId());
			st.setDouble(++k, receipt.getToPay());
			st.setInt(++k, receipt.getReceiptStatusId());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	@Override
	public void update(Connection connection, Receipt receipt) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.UPDATE_RECEIPT);
			int k = 0;
			st.setDouble(++k, receipt.getToPay());
			st.setInt(++k, receipt.getReceiptStatusId());
			st.setLong(++k, receipt.getId());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	@Override
	public List<Receipt> getReceipts(Connection connection, User user, String sortBy, int statusId, String itemsOnPage, int page) throws SQLException {
		List<Receipt> receipts = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;

		String query = MySqlQueries.SELECT_RECEIPT_JOIN_INVOICE;
		if (statusId != -1) {
			query += MySqlQueries.RECEIPT_BY_STATUS_ID;
		}
		if (user != null) {
			query += MySqlQueries.RECEIPT_BY_USER_ID;
		}
		switch (sortBy) {
			case "ID asc":
				query += MySqlQueries.ORDER_BY_ID_ASC;
				break;
			case "ID desc":
				query += MySqlQueries.ORDER_BY_ID_DESC;
				break;
			case "to pay asc":
				query += MySqlQueries.ORDER_BY_TO_PAY_ASC;
				break;
			case "to pay desc":
				query += MySqlQueries.ORDER_BY_TO_PAY_DESC;
				break;
		}
		if (!"all".equals(itemsOnPage)) {
			query += MySqlQueries.LIMIT;
		}

		// log
		System.out.println("query = " + query);

		try {
			st = connection.prepareStatement(query);
			int k = 0;
			if (statusId != -1) {
				st.setInt(++k, statusId);
			}
			if (user != null) {
				st.setLong(++k, user.getId());
			}
			if (!"all".equals(itemsOnPage)) {
				st.setInt(++k, Integer.parseInt(itemsOnPage) * (page - 1));
				st.setInt(++k, Integer.parseInt(itemsOnPage));
			}
			rs = st.executeQuery();
			while (rs.next()) {
				receipts.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return receipts;
	}

	private Receipt parseResultSet(ResultSet rs) throws SQLException {
		Receipt receipt = new Receipt();
		int k = 0;
		receipt.setId(rs.getLong(++k));
		receipt.setToPay(rs.getDouble(++k));
		receipt.setReceiptStatusId(rs.getInt(++k));
		return receipt;
	}
}

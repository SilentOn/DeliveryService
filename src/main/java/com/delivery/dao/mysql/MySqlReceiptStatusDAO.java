package com.delivery.dao.mysql;

import com.delivery.dao.ReceiptStatusDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.ReceiptStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlReceiptStatusDAO implements ReceiptStatusDAO {
	private static MySqlReceiptStatusDAO instance;

	private MySqlReceiptStatusDAO() {
	}

	public static synchronized MySqlReceiptStatusDAO getInstance() {
		if (instance == null) {
			instance = new MySqlReceiptStatusDAO();
		}
		return instance;
	}

	@Override
	public ReceiptStatus getByPK(Connection connection, int id) throws SQLException {
		ReceiptStatus receiptStatus = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_RECEIPT_STATUS_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				receiptStatus = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return receiptStatus;
	}

	@Override
	public ReceiptStatus getByName(Connection connection, ReceiptStatus.Status name) throws SQLException {
		ReceiptStatus receiptStatus = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_RECEIPT_STATUS_BY_NAME);
			st.setString(1, name.toString());
			rs = st.executeQuery();
			if (rs.next()) {
				receiptStatus = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return receiptStatus;
	}

	@Override
	public List<ReceiptStatus> getAll(Connection connection) throws SQLException {
		List<ReceiptStatus> receiptStatuses = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_RECEIPT_STATUS);
			while (rs.next()) {
				receiptStatuses.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return receiptStatuses;
	}

	private ReceiptStatus parseResultSet(ResultSet rs) throws SQLException {
		ReceiptStatus receiptStatus = new ReceiptStatus();
		receiptStatus.setId(rs.getInt(1));
		receiptStatus.setName(ReceiptStatus.Status.fromString(rs.getString(2)));
		return receiptStatus;
	}
}

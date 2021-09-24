package com.delivery.dao.mysql;

import com.delivery.dao.InvoiceStatusDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.InvoiceStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlInvoiceStatusDAO implements InvoiceStatusDAO {
	private static MySqlInvoiceStatusDAO instance;

	private MySqlInvoiceStatusDAO() {
	}

	public static synchronized MySqlInvoiceStatusDAO getInstance() {
		if (instance == null) {
			instance = new MySqlInvoiceStatusDAO();
		}
		return instance;
	}

	@Override
	public InvoiceStatus getByPK(Connection connection, int id) throws SQLException {
		InvoiceStatus invoiceStatus = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_INVOICE_STATUS_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				invoiceStatus = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoiceStatus;
	}

	@Override
	public InvoiceStatus getByName(Connection connection, InvoiceStatus.Status name) throws SQLException {
		InvoiceStatus invoiceStatus = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_INVOICE_STATUS_BY_NAME);
			st.setString(1, name.toString());
			rs = st.executeQuery();
			if (rs.next()) {
				invoiceStatus = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoiceStatus;
	}

	@Override
	public List<InvoiceStatus> getAll(Connection connection) throws SQLException {
		List<InvoiceStatus> invoiceStatuses = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_INVOICE_STATUS);
			while (rs.next()) {
				invoiceStatuses.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoiceStatuses;
	}

	private InvoiceStatus parseResultSet(ResultSet rs) throws SQLException {
		InvoiceStatus invoiceStatus = new InvoiceStatus();
		invoiceStatus.setId(rs.getInt(1));
		invoiceStatus.setName(InvoiceStatus.Status.valueOf(rs.getString(2).toUpperCase()));
		return invoiceStatus;
	}
}

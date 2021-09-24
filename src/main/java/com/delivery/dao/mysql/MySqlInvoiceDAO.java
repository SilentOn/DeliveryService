package com.delivery.dao.mysql;


import com.delivery.dao.InvoiceDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySqlInvoiceDAO implements InvoiceDAO {
	private static MySqlInvoiceDAO instance;

	private MySqlInvoiceDAO() {
	}

	public static synchronized MySqlInvoiceDAO getInstance() {
		if (instance == null) {
			instance = new MySqlInvoiceDAO();
		}
		return instance;
	}

	@Override
	public Invoice getByPK(Connection connection, long id) throws SQLException {
		Invoice invoice = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_INVOICE_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				invoice = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoice;
	}

	@Override
	public List<Invoice> getByUser(Connection connection, long userId) throws SQLException {
		List<Invoice> invoices = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_INVOICE_BY_USER_ID);
			st.setLong(1, userId);
			rs = st.executeQuery();
			while (rs.next()) {
				invoices.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoices;
	}


	@Override
	public List<Invoice> getAll(Connection connection) throws SQLException {
		List<Invoice> invoices = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_INVOICE);
			while (rs.next()) {
				invoices.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoices;
	}

	// returns inserted invoice with id and createTime
	@Override
	public Invoice insert(Connection connection, Invoice invoice) throws SQLException {
		Invoice insertedInvoice = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.INSERT_INTO_INVOICE, Statement.RETURN_GENERATED_KEYS);
			int k = 0;
			st.setLong(++k, invoice.getUserId());
			st.setLong(++k, invoice.getAddressFromId());
			st.setLong(++k, invoice.getAddressToId());
			st.setLong(++k, invoice.getTariffZoneId());
			st.setLong(++k, invoice.getInvoiceStatusId());
			st.setDouble(++k, invoice.getEstimate());
			st.setInt(++k, invoice.getDeliveryTerm());
			st.setFloat(++k, invoice.getTotalWeight());
			st.setFloat(++k, invoice.getTotalVolume());
			st.setInt(++k, invoice.getNumberOfCargo());
			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					// the inserted invoice will be returned
					insertedInvoice = getByPK(connection, rs.getLong(1));
				}
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return insertedInvoice;
	}

	@Override
	public void process(Connection connection, Invoice invoice) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.UPDATE_INVOICE_BY_PROCESSED);
			int k = 0;
			st.setLong(++k, invoice.getProcessedById());
			st.setLong(++k, invoice.getInvoiceStatusId());
			st.setLong(++k, invoice.getId());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	@Override
	public void update(Connection connection, Invoice invoice) {
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	private Invoice parseResultSet(ResultSet rs) throws SQLException {
		Invoice invoice = new Invoice();
		int k = 0;
		invoice.setId(rs.getLong(++k));
		invoice.setUserId(rs.getLong(++k));
		invoice.setAddressFromId(rs.getLong(++k));
		invoice.setAddressToId(rs.getLong(++k));
		invoice.setTariffZoneId(rs.getLong(++k));
		invoice.setProcessedById(rs.getLong(++k));
		invoice.setInvoiceStatusId(rs.getLong(++k));
		invoice.setCreateTime(rs.getTimestamp(++k));
		invoice.setEstimate(rs.getDouble(++k));
		invoice.setDeliveryTerm(rs.getInt(++k));
		invoice.setTotalWeight(rs.getFloat(++k));
		invoice.setTotalVolume(rs.getFloat(++k));
		invoice.setNumberOfCargo(rs.getInt(++k));
		return invoice;
	}
}
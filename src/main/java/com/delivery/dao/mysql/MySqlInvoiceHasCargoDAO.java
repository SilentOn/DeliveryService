package com.delivery.dao.mysql;

import com.delivery.dao.InvoiceHasCargoDAO;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.InvoiceHasCargo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlInvoiceHasCargoDAO implements InvoiceHasCargoDAO {
	private static MySqlInvoiceHasCargoDAO instance;

	private MySqlInvoiceHasCargoDAO() {
	}

	public static synchronized MySqlInvoiceHasCargoDAO getInstance() {
		if (instance == null) {
			instance = new MySqlInvoiceHasCargoDAO();
		}
		return instance;
	}

	@Override
	public InvoiceHasCargo getByPK(Connection connection, long id) throws SQLException {
		InvoiceHasCargo invoiceHasCargo = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_INVOICE_HAS_CARGO_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				invoiceHasCargo = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoiceHasCargo;
	}

	@Override
	public List<InvoiceHasCargo> getAll(Connection connection) throws SQLException {
		List<InvoiceHasCargo> invoiceHasCargos = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_INVOICE_HAS_CARGO);
			while (rs.next()) {
				invoiceHasCargos.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoiceHasCargos;
	}

	@Override
	public List<InvoiceHasCargo> getByInvoiceId(Connection connection, long invoiceId) throws SQLException {
		List<InvoiceHasCargo> invoiceHasCargos = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_INVOICE_HAS_CARGO_BY_INVOICE_ID);
			st.setLong(1, invoiceId);
			rs = st.executeQuery();
			while (rs.next()) {
				invoiceHasCargos.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return invoiceHasCargos;
	}

	// returns inserted invoiceHasCargo with id
	@Override
	public InvoiceHasCargo insert(Connection connection, InvoiceHasCargo invoiceHasCargo) throws SQLException {
		InvoiceHasCargo insertedInvoiceHasCargo = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.INSERT_INTO_INVOICE_HAS_CARGO, Statement.RETURN_GENERATED_KEYS);
			int k = 0;
			st.setLong(++k, invoiceHasCargo.getInvoiceId());
			st.setLong(++k, invoiceHasCargo.getCargoId());
			st.setFloat(++k, invoiceHasCargo.getWeight());
			st.setFloat(++k, invoiceHasCargo.getLength());
			st.setFloat(++k, invoiceHasCargo.getWidth());
			st.setFloat(++k, invoiceHasCargo.getHeight());
			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					// the inserted invoiceHasCargo will be returned
					insertedInvoiceHasCargo = getByPK(connection, rs.getInt(1));
				}
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return insertedInvoiceHasCargo;
	}

	// returns inserted invoiceHasCargos with id
	@Override
	public List<InvoiceHasCargo> insert(Connection connection, List<InvoiceHasCargo> invoiceHasCargos) throws SQLException {
		List<InvoiceHasCargo> insertedInvoiceHasCargos = new ArrayList<>();
		for (InvoiceHasCargo invoiceHasCargo : invoiceHasCargos) {
			insertedInvoiceHasCargos.add(insert(connection, invoiceHasCargo));
		}
		return insertedInvoiceHasCargos;
	}

	@Override
	public void update(Connection connection, InvoiceHasCargo invoiceHasCargo) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.UPDATE_INVOICE_HAS_CARGO);
			int k = 0;
			st.setLong(++k, invoiceHasCargo.getCargoId());
			st.setFloat(++k, invoiceHasCargo.getWeight());
			st.setFloat(++k, invoiceHasCargo.getLength());
			st.setFloat(++k, invoiceHasCargo.getWidth());
			st.setFloat(++k, invoiceHasCargo.getHeight());
			st.setLong(++k, invoiceHasCargo.getId());
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	@Override
	public void delete(Connection connection, long id) throws SQLException {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(MySqlQueries.DELETE_INVOICE_HAS_CARGO);
			st.setLong(1, id);
			st.executeUpdate();
		} finally {
			MySqlDAOFactory.getInstance().close(st);
		}
	}

	private InvoiceHasCargo parseResultSet(ResultSet rs) throws SQLException {
		InvoiceHasCargo invoiceHasCargo = new InvoiceHasCargo();
		int k = 0;
		invoiceHasCargo.setId(rs.getLong(++k));
		invoiceHasCargo.setInvoiceId(rs.getLong(++k));
		invoiceHasCargo.setCargoId(rs.getLong(++k));
		invoiceHasCargo.setWeight(rs.getFloat(++k));
		invoiceHasCargo.setLength(rs.getFloat(++k));
		invoiceHasCargo.setWidth(rs.getFloat(++k));
		invoiceHasCargo.setHeight(rs.getFloat(++k));
		return invoiceHasCargo;
	}
}

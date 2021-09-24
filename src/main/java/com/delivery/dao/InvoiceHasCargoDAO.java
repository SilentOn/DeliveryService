package com.delivery.dao;


import com.delivery.entity.InvoiceHasCargo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InvoiceHasCargoDAO {
	InvoiceHasCargo getByPK(Connection connection, long id) throws SQLException;

	List<InvoiceHasCargo> getByInvoiceId(Connection connection, long invoiceId) throws SQLException;

	List<InvoiceHasCargo> getAll(Connection connection) throws SQLException;

	InvoiceHasCargo insert(Connection connection, InvoiceHasCargo invoiceHasCargo) throws SQLException;

	List<InvoiceHasCargo> insert(Connection connection, List<InvoiceHasCargo> invoiceHasCargos) throws SQLException;

	void update(Connection connection, InvoiceHasCargo invoiceHasCargo) throws SQLException;

	void delete(Connection connection, long id) throws SQLException;
}

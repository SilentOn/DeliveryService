package com.delivery.dao.mysql;

import com.delivery.dao.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDAOFactory extends DAOFactory {

	private static MySqlDAOFactory instance;

	public static synchronized MySqlDAOFactory getInstance() {
		if (instance == null) {
			instance = new MySqlDAOFactory();
		}
		return instance;
	}

	private final DataSource ds;

	private MySqlDAOFactory() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TestDB");
		} catch (NamingException ex) {
			throw new IllegalStateException("Cannot obtain a data source", ex);
		}
	}

	@Override
	public UserDAO getUserDAO() {
		return MySqlUserDAO.getInstance();
	}

	@Override
	public UserDetailsDAO getUserDetailsDAO() {
		return MySQLUserDetailsDAO.getInstance();
	}

	@Override
	public InvoiceDAO getInvoiceDAO() {
		return MySqlInvoiceDAO.getInstance();
	}

	@Override
	public RoleDAO getRoleDAO() {
		return MySqlRoleDAO.getInstance();
	}

	@Override
	public InvoiceStatusDAO getInvoiceStatusDAO() {
		return MySqlInvoiceStatusDAO.getInstance();
	}

	@Override
	public InvoiceHasCargoDAO getInvoiceHasCargoDAO() {
		return MySqlInvoiceHasCargoDAO.getInstance();
	}

	@Override
	public ReceiptDAO getReceiptDAO() {
		return MySqlReceiptDAO.getInstance();
	}

	@Override
	public ReceiptStatusDAO getReceiptStatusDAO() {
		return MySqlReceiptStatusDAO.getInstance();
	}

	@Override
	public CargoDAO getCargoDAO() {
		return MySqlCargoDAO.getInstance();
	}

	@Override
	public RegionDAO getRegionDAO() {
		return MySqlRegionDAO.getInstance();
	}

	@Override
	public CityDAO getCityDAO() {
		return MySqlCityDAO.getInstance();
	}

	@Override
	public TariffZoneDAO getTariffZoneDAO() {
		return MySqlTariffZoneDAO.getInstance();
	}

	@Override
	public WeightTariffDAO getWeightTariffDAO() {
		return MySqlWeightTariffDAO.getInstance();
	}

	@Override
	public VolumetricTariffDAO getVolumetricTariffDAO() {
		return MySqlVolumetricTariffDAO.getInstance();
	}

	@Override
	public TariffZoneHasWeightTariffDAO getTariffZoneHasWeightTariffDAO() {
		return MySqlTariffZoneHasWeightTariffDAO.getInstance();
	}

	@Override
	public AddressDAO getAddressDAO() {
		return MySqlAddressDAO.getInstance();
	}

	@Override
	public RegionHasRegionDAO getRegionHasRegionDAO() {
		return MySqlRegionHasRegionDAO.getInstance();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	@Override
	public void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				// log
				System.err.println("Can't rollback " + ex);
			}
		}
	}

	@Override
	public void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
				ac = null;
			} catch (Exception ex) {
				// write to log
				System.err.println("Can't close " + ex);
			}
		}
	}
}

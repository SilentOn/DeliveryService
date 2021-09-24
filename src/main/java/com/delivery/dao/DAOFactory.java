package com.delivery.dao;

import com.delivery.dao.mysql.MySqlDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
	public static final String MYSQL = "MySQL"; // change to fqn initialized by session init listener!!!!!!!!!!!!

	public static DAOFactory getDAOFactory() {
		return MySqlDAOFactory.getInstance();
	}

	public static DAOFactory getDAOFactory(String name) {
		if (MYSQL.equalsIgnoreCase(name)) {
			return MySqlDAOFactory.getInstance();
		}
		throw new RuntimeException("Unknown factory");
	}

	public abstract Connection getConnection() throws SQLException;

	public abstract void rollback(Connection con);

	public abstract void close(AutoCloseable ac);

	public abstract UserDAO getUserDAO();

	public abstract RoleDAO getRoleDAO();

	public abstract UserDetailsDAO getUserDetailsDAO();

	public abstract InvoiceDAO getInvoiceDAO();

	public abstract InvoiceStatusDAO getInvoiceStatusDAO();

	public abstract InvoiceHasCargoDAO getInvoiceHasCargoDAO();

	public abstract ReceiptDAO getReceiptDAO();

	public abstract ReceiptStatusDAO getReceiptStatusDAO();

	public abstract CargoDAO getCargoDAO();

	public abstract RegionDAO getRegionDAO();

	public abstract CityDAO getCityDAO();

	public abstract AddressDAO getAddressDAO();

	public abstract RegionHasRegionDAO getRegionHasRegionDAO();

	public abstract TariffZoneDAO getTariffZoneDAO();

	public abstract WeightTariffDAO getWeightTariffDAO();

	public abstract VolumetricTariffDAO getVolumetricTariffDAO();

	public abstract TariffZoneHasWeightTariffDAO getTariffZoneHasWeightTariffDAO();
}
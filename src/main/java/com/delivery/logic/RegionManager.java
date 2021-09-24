package com.delivery.logic;


import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RegionManager {
	private static RegionManager instance;

	private final DAOFactory daoFactory;

	private RegionManager(DAOFactory daoFactory) throws DAOException {
		this.daoFactory = daoFactory;
	}

	public static synchronized RegionManager getInstance(DAOFactory daoFactory) throws DAOException {
		if (instance == null) {
			instance = new RegionManager(daoFactory);
		}
		return instance;
	}

	public List<Region> getAllRegions() throws DAOException {
		List<Region> regions;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			regions = daoFactory.getRegionDAO().getAll(con);
			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all regions", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return regions;
	}

	public List<City> getAllCities() throws DAOException {
		List<City> cities;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			cities = daoFactory.getCityDAO().getAll(con);
			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all cities", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return cities;
	}

	public List<Address> getAllAddresses() throws DAOException {
		List<Address> addresses;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			addresses = daoFactory.getAddressDAO().getAll(con);
			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all addresses", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return addresses;
	}

	public Address getAddress(String region, String city, String address) throws DAOException {
		Address addressEntity = null;

		Connection con = null;
		try {
			con = daoFactory.getConnection();

			addressEntity = daoFactory.getAddressDAO().getByTitlesOfRegionCityAddress(con, region, city, address);

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't get address", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}

		return addressEntity;
	}

	public TariffZone getTariffZone(long cityFromId, long cityToId) throws DAOException {
		TariffZone tariffZone = null;

		Connection con = null;
		try {
			con = daoFactory.getConnection();

			tariffZone = daoFactory.getTariffZoneDAO().getByCityFromIdAndCityToId(con, cityFromId, cityToId);

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't get tariffZone", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}

		return tariffZone;
	}
}

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

	public List<City> getCities(String sortBy, String filterBy, String itemsOnPage, int page) throws DAOException {
		List<City> cities;
		int regionId = -1;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			if (!"all".equals(filterBy)) {
				regionId = daoFactory.getRegionDAO().getByRegionTitle(con, filterBy).getId();
			}
			cities = daoFactory.getCityDAO().getCities(con, sortBy, regionId, itemsOnPage, page);
			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain cities", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return cities;
	}

	public int getPagesCountForCities(String filterBy, String itemsOnPage) throws DAOException {
		int pagesCount;
		List<City> cities;
		int regionId = -1;
		Connection con = null;
		try {
			con = daoFactory.getConnection();

			if ("all".equals(itemsOnPage)) {
				pagesCount = 1;
			} else {
				if (!"all".equals(filterBy)) {
					regionId = daoFactory.getRegionDAO().getByRegionTitle(con, filterBy).getId();
				}
				cities = daoFactory.getCityDAO().getCities(con, "none", regionId, "all", 1);
				pagesCount = (int) Math.ceil(cities.size() / Double.parseDouble(itemsOnPage));
			}

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't count pages for cities", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return pagesCount;
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

	public List<TariffZone> getAllTariffZones() throws DAOException {
		List<TariffZone> tariffZones;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			tariffZones = daoFactory.getTariffZoneDAO().getAll(con);
			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all tariffZones", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return tariffZones;
	}

	public List<TariffZoneHasWeightTariff> getAllTariffZoneHasWeightTariffs() throws DAOException {
		List<TariffZoneHasWeightTariff> tariffZoneHasWeightTariffs;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			tariffZoneHasWeightTariffs = daoFactory.getTariffZoneHasWeightTariffDAO().getAll(con);
			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all tariffZoneHasWeightTariffs", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return tariffZoneHasWeightTariffs;
	}

	public List<WeightTariff> getAllWeightTariffs() throws DAOException {
		List<WeightTariff> weightTariffs;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			weightTariffs = daoFactory.getWeightTariffDAO().getAll(con);
			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all weightTariffs", ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return weightTariffs;
	}
}

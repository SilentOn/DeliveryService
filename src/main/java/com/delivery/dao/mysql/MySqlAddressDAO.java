package com.delivery.dao.mysql;

import com.delivery.dao.AddressDAO;
import com.delivery.dao.DAOFactory;
import com.delivery.dao.mysql.queries.MySqlQueries;
import com.delivery.entity.Address;
import com.delivery.entity.City;
import com.delivery.entity.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlAddressDAO implements AddressDAO {
	private static MySqlAddressDAO instance;

	private MySqlAddressDAO() {
	}

	public static synchronized MySqlAddressDAO getInstance() {
		if (instance == null) {
			instance = new MySqlAddressDAO();
		}
		return instance;
	}

	@Override
	public Address getByPK(Connection connection, long id) throws SQLException {
		Address address = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_ADDRESS_BY_ID);
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				address = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return address;
	}

	@Override
	public Address getByAddressTitleAndCityId(Connection connection, String addressTitle, long cityId) throws SQLException {
		Address address = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_ADDRESS_BY_ADDRESS_TITLE_AND_CITY_ID);
			int k = 0;
			st.setString(++k, addressTitle);
			st.setLong(++k, cityId);
			rs = st.executeQuery();
			if (rs.next()) {
				address = parseResultSet(rs);
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return address;
	}

	@Override
	public List<Address> getAll(Connection connection) throws SQLException {
		List<Address> addresses = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(MySqlQueries.SELECT_ALL_ADDRESS);
			while (rs.next()) {
				addresses.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return addresses;
	}

	@Override
	public List<Address> getByCityId(Connection connection, long cityId) throws SQLException {
		List<Address> addresses = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.SELECT_ADDRESS_BY_REGION_ID);
			st.setLong(1, cityId);
			rs = st.executeQuery();
			while (rs.next()) {
				addresses.add(parseResultSet(rs));
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return addresses;
	}

	// returns inserted address with id
	@Override
	public Address insert(Connection connection, Address address) throws SQLException {
		Address insertedAddress = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(MySqlQueries.INSERT_INTO_ADDRESS, Statement.RETURN_GENERATED_KEYS);
			int k = 0;
			st.setString(++k, address.getAddressTitle());
			st.setLong(++k, address.getCityId());
			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					// the inserted address will be returned
					insertedAddress = getByPK(connection, rs.getLong(1));
				}
			}
		} finally {
			MySqlDAOFactory.getInstance().close(rs);
			MySqlDAOFactory.getInstance().close(st);
		}
		return insertedAddress;
	}

	@Override
	public Address getByTitlesOfRegionCityAddress(Connection con, String region, String city, String address) throws SQLException {
		Address addressEntity = null;
		Region regionEntity = null;
		City cityEntity = null;

		regionEntity = DAOFactory.getDAOFactory().getRegionDAO().getByRegionTitle(con, region);

		// log
		System.out.println("regionEntity in getByTitlesOfRegionCityAddress ==> " + regionEntity);

		cityEntity = DAOFactory.getDAOFactory().getCityDAO().getByCityTitleAndRegionId(con, city, regionEntity.getId());
		if (cityEntity == null) {
			cityEntity = new City();
			cityEntity.setRegionId(regionEntity.getId());
			cityEntity.setCityTitle(city);
			cityEntity = DAOFactory.getDAOFactory().getCityDAO().insert(con, cityEntity);
		} else {
			addressEntity = DAOFactory.getDAOFactory().getAddressDAO().getByAddressTitleAndCityId(con, address, cityEntity.getId());
		}

		if (addressEntity == null) {
			addressEntity = new Address();
			addressEntity.setCityId(cityEntity.getId());
			addressEntity.setAddressTitle(address);
			addressEntity = DAOFactory.getDAOFactory().getAddressDAO().insert(con, addressEntity);
		}

		// log
		System.out.println("addressEntity in getByTitlesOfRegionCityAddress ==> " + addressEntity);

		return addressEntity;
	}

	private Address parseResultSet(ResultSet rs) throws SQLException {
		Address address = new Address();
		int k = 0;
		address.setId(rs.getLong(++k));
		address.setAddressTitle(rs.getString(++k));
		address.setCityId(rs.getLong(++k));
		return address;
	}
}

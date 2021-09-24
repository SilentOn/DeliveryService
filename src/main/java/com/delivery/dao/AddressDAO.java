package com.delivery.dao;

import com.delivery.entity.Address;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {
	Address getByPK(Connection connection, long id) throws SQLException;

	Address getByAddressTitleAndCityId(Connection connection, String addressTitle, long cityId) throws SQLException;

	List<Address> getByCityId(Connection connection, long cityId) throws SQLException;

	List<Address> getAll(Connection connection) throws SQLException;

	Address insert(Connection connection, Address address) throws SQLException;

	Address getByTitlesOfRegionCityAddress(Connection con, String region, String city, String address) throws SQLException;
}

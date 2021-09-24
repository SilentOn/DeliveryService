package com.delivery.dao;

import com.delivery.entity.VolumetricTariff;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VolumetricTariffDAO {
	VolumetricTariff getByPK(Connection connection, int id) throws SQLException;

	List<VolumetricTariff> getAll(Connection connection) throws SQLException;
}

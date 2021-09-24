package com.delivery.dao;

import com.delivery.entity.WeightTariff;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface WeightTariffDAO {
	WeightTariff getByPK(Connection connection, int id) throws SQLException;

	List<WeightTariff> getAll(Connection connection) throws SQLException;
}

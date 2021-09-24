package com.delivery.dao;

import com.delivery.entity.TariffZoneHasWeightTariff;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TariffZoneHasWeightTariffDAO {
	TariffZoneHasWeightTariff getByPK(Connection connection, long tariffZoneId, long weightTariffId) throws SQLException;

	List<TariffZoneHasWeightTariff> getAll(Connection connection) throws SQLException;
}

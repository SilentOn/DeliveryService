package com.delivery.dao;

import com.delivery.entity.TariffZone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TariffZoneDAO {
	TariffZone getByPK(Connection connection, long id) throws SQLException;

	TariffZone getByTariffZoneTitle(Connection connection, TariffZone.TariffZoneTitle tariffZoneTitle) throws SQLException;

	List<TariffZone> getAll(Connection connection) throws SQLException;

	TariffZone getByCityFromIdAndCityToId(Connection con, long cityFromId, long cityToId) throws SQLException;
}

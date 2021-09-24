package com.delivery.dao;

import com.delivery.entity.RegionHasRegion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RegionHasRegionDAO {
	RegionHasRegion getByPK(Connection connection, int regionId1, int regionId2) throws SQLException;

	List<RegionHasRegion> getByTariffZoneId(Connection connection, int tariffZoneId) throws SQLException;

	List<RegionHasRegion> getAll(Connection connection) throws SQLException;
}

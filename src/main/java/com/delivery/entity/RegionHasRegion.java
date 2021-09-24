package com.delivery.entity;

import java.io.Serializable;

public class RegionHasRegion implements Serializable {

	private int regionId1;

	private int regionId2;

	private int tariffZoneId;

	public int getRegionId1() {
		return regionId1;
	}

	public void setRegionId1(int regionId1) {
		this.regionId1 = regionId1;
	}

	public int getRegionId2() {
		return regionId2;
	}

	public void setRegionId2(int regionId2) {
		this.regionId2 = regionId2;
	}

	public int getTariffZoneId() {
		return tariffZoneId;
	}

	public void setTariffZoneId(int tariffZoneId) {
		this.tariffZoneId = tariffZoneId;
	}
}

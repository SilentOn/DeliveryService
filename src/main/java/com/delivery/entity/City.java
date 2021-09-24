package com.delivery.entity;

import java.io.Serializable;

public class City implements Serializable {

	private long id;

	private String cityTitle;

	private int regionId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCityTitle() {
		return cityTitle;
	}

	public void setCityTitle(String cityTitle) {
		this.cityTitle = cityTitle;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "City{" +
				"id=" + id +
				", cityTitle='" + cityTitle + '\'' +
				", regionId=" + regionId +
				'}';
	}
}

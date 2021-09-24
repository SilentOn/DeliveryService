package com.delivery.entity;

import java.io.Serializable;

public class Address implements Serializable {

	private long id;

	private String addressTitle;

	private long cityId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddressTitle() {
		return addressTitle;
	}

	public void setAddressTitle(String addressTitle) {
		this.addressTitle = addressTitle;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", addressTitle='" + addressTitle + '\'' +
				", cityId=" + cityId +
				'}';
	}
}

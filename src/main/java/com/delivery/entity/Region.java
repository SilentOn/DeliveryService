package com.delivery.entity;

import java.io.Serializable;

public class Region implements Serializable {

	private int id;

	private String regionTitle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegionTitle() {
		return regionTitle;
	}

	public void setRegionTitle(String regionTitle) {
		this.regionTitle = regionTitle;
	}
}

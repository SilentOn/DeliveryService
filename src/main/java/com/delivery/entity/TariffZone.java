package com.delivery.entity;

import java.io.Serializable;

public class TariffZone implements Serializable {

	private long id;

	private String tariffZoneTitle;

	private int term;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTariffZoneTitle() {
		return tariffZoneTitle;
	}

	public void setTariffZoneTitle(String tariffZoneTitle) {
		this.tariffZoneTitle = tariffZoneTitle;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "TariffZone{" +
				"id=" + id +
				", tariffZoneTitle='" + tariffZoneTitle + '\'' +
				", term=" + term +
				'}';
	}
}

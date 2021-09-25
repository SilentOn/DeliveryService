package com.delivery.entity;

import java.io.Serializable;

public class TariffZone implements Serializable {

	private long id;

	private TariffZoneTitle tariffZoneTitle;

	private int term;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TariffZoneTitle getTariffZoneTitle() {
		return tariffZoneTitle;
	}

	public void setTariffZoneTitle(TariffZoneTitle tariffZoneTitle) {
		this.tariffZoneTitle = tariffZoneTitle;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public enum TariffZoneTitle {
		ZONE_1("1"), ZONE_2("2"),ZONE_3("3"), ZONE_4("4"),ZONE_CITY("city"), ZONE_REGION("region");
		private final String tariffZoneTitle;

		TariffZoneTitle(String tariffZoneTitle) {
			this.tariffZoneTitle = tariffZoneTitle;
		}

		@Override
		public String toString() {
			return tariffZoneTitle;
		}

		public static TariffZoneTitle fromString(String text) {
			for (TariffZoneTitle s : TariffZoneTitle.values()) {
				if (s.tariffZoneTitle.equalsIgnoreCase(text)) {
					return s;
				}
			}
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "TariffZone{" +
				"id=" + id +
				", tariffZoneTitle='" + tariffZoneTitle.toString() + '\'' +
				", term=" + term +
				'}';
	}
}

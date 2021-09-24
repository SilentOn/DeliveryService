package com.delivery.entity;

import java.io.Serializable;

public class TariffZoneHasWeightTariff implements Serializable {

	private long tariffZoneId;

	private long weightTariffId;

	private float price;

	public long getTariffZoneId() {
		return tariffZoneId;
	}

	public void setTariffZoneId(long tariffZoneId) {
		this.tariffZoneId = tariffZoneId;
	}

	public long getWeightTariffId() {
		return weightTariffId;
	}

	public void setWeightTariffId(long weightTariffId) {
		this.weightTariffId = weightTariffId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TariffZoneHasWeightTariff{" +
				"tariffZoneId=" + tariffZoneId +
				", weightTariffId=" + weightTariffId +
				", price=" + price +
				'}';
	}
}

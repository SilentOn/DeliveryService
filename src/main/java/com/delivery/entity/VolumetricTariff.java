package com.delivery.entity;

import java.io.Serializable;

public class VolumetricTariff implements Serializable {

	private long id;

	private int volume;

	private float price;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "VolumetricTariff{" +
				"id=" + id +
				", volume=" + volume +
				", price=" + price +
				'}';
	}
}

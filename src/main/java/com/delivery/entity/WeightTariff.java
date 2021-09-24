package com.delivery.entity;

import java.io.Serializable;

public class WeightTariff implements Serializable {

	private long id;

	private float weight;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "WeightTariff{" +
				"id=" + id +
				", weight=" + weight +
				'}';
	}
}

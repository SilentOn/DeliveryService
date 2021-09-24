package com.delivery.entity;

import java.io.Serializable;

public class InvoiceHasCargo implements Serializable {

	private long id;

	private long invoiceId;

	private long cargoId;

	private float weight;

	private float length;

	private float width;

	private float height;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public long getCargoId() {
		return cargoId;
	}

	public void setCargoId(long cargoId) {
		this.cargoId = cargoId;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "InvoiceHasCargo{" +
				"id=" + id +
				", invoiceId=" + invoiceId +
				", cargoId=" + cargoId +
				", weight=" + weight +
				", length=" + length +
				", width=" + width +
				", height=" + height +
				'}';
	}
}

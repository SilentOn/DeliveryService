package com.delivery.entity;

import java.io.Serializable;

public class Receipt implements Serializable {

	private long id;

	private double toPay;

	private int receiptStatusId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getToPay() {
		return toPay;
	}

	public void setToPay(double toPay) {
		this.toPay = toPay;
	}

	public int getReceiptStatusId() {
		return receiptStatusId;
	}

	public void setReceiptStatusId(int receiptStatusId) {
		this.receiptStatusId = receiptStatusId;
	}

	@Override
	public String toString() {
		return "Receipt{" +
				"id=" + id +
				", toPay=" + toPay +
				", receiptStatusId=" + receiptStatusId +
				'}';
	}
}

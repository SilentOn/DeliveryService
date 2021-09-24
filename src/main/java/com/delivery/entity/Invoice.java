package com.delivery.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Invoice implements Serializable {
	private long id;
	private long userId;
	private long addressFromId;
	private long addressToId;
	private long tariffZoneId;
	private long processedById;
	private long invoiceStatusId;
	private Timestamp createTime;
	private Double estimate;
	private int deliveryTerm;
	private float totalWeight;
	private float totalVolume;
	private int numberOfCargo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAddressFromId() {
		return addressFromId;
	}

	public void setAddressFromId(long addressFromId) {
		this.addressFromId = addressFromId;
	}

	public long getAddressToId() {
		return addressToId;
	}

	public void setAddressToId(long addressToId) {
		this.addressToId = addressToId;
	}

	public long getTariffZoneId() {
		return tariffZoneId;
	}

	public void setTariffZoneId(long tariffZoneId) {
		this.tariffZoneId = tariffZoneId;
	}

	public long getProcessedById() {
		return processedById;
	}

	public void setProcessedById(long processedById) {
		this.processedById = processedById;
	}

	public long getInvoiceStatusId() {
		return invoiceStatusId;
	}

	public void setInvoiceStatusId(long invoiceStatusId) {
		this.invoiceStatusId = invoiceStatusId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Double getEstimate() {
		return estimate;
	}

	public void setEstimate(Double estimate) {
		this.estimate = estimate;
	}

	public int getDeliveryTerm() {
		return deliveryTerm;
	}

	public void setDeliveryTerm(int deliveryTerm) {
		this.deliveryTerm = deliveryTerm;
	}

	public float getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(float totalWeight) {
		this.totalWeight = totalWeight;
	}

	public float getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(float totalVolume) {
		this.totalVolume = totalVolume;
	}

	public int getNumberOfCargo() {
		return numberOfCargo;
	}

	public void setNumberOfCargo(int numberOfCargo) {
		this.numberOfCargo = numberOfCargo;
	}

	@Override
	public String toString() {
		return "Invoice{" +
				"id=" + id +
				", userId=" + userId +
				", addressFromId=" + addressFromId +
				", addressToId=" + addressToId +
				", tariffZoneId=" + tariffZoneId +
				", processedById=" + processedById +
				", invoiceStatusId=" + invoiceStatusId +
				", createTime=" + createTime +
				", estimate=" + estimate +
				", deliveryTerm=" + deliveryTerm +
				", totalWeight=" + totalWeight +
				", totalVolume=" + totalVolume +
				", numberOfCargo=" + numberOfCargo +
				'}';
	}
}

package com.delivery.entity;

import java.io.Serializable;

public class InvoiceStatus implements Serializable {

	private int id;

	private Status name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status getName() {
		return name;
	}

	public void setName(Status name) {
		this.name = name;
	}

	public enum Status {
		NEW("new"), CANCELED("canceled"), PROCESSED("processed"), PAID("paid"), DELIVERED("delivered");
		private final String status;

		Status(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return status;
		}

		/*public static Status fromString(String text) {
			for (Status s : Status.values()) {
				if (s.status.equalsIgnoreCase(text)) {
					return s;
				}
			}
			throw new IllegalArgumentException();
		}*/
	}

	@Override
	public String toString() {
		return "InvoiceStatus{" +
				"id=" + id +
				", name=" + name +
				'}';
	}
}

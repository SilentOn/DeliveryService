package com.delivery.entity;

import java.io.Serializable;

public class Cargo implements Serializable {

	private int id;

	private Type type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		PARCELS("parcels and cargoes"), DOCUMENTS("documents");
		private final String cargoType;

		Type(String cargoType) {
			this.cargoType = cargoType;
		}

		@Override
		public String toString() {
			return cargoType;
		}

		public static Type fromString(String text) {
			for (Type s : Type.values()) {
				if (s.cargoType.equalsIgnoreCase(text)) {
					return s;
				}
			}
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Cargo{" +
				"id=" + id +
				", type=" + type.toString() +
				'}';
	}
}

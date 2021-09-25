package com.delivery.entity;

import java.io.Serializable;

public class Role implements Serializable {

	private int id;

	private RoleName name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

	public enum RoleName {
		USER("user"), MANAGER("manager");
		private final String roleName;

		RoleName(String roleName) {
			this.roleName = roleName;
		}

		@Override
		public String toString() {
			return roleName;
		}

		public static RoleName fromString(String text) {
			for (RoleName s : RoleName.values()) {
				if (s.roleName.equalsIgnoreCase(text)) {
					return s;
				}
			}
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", name=" + name.toString() +
				'}';
	}
}

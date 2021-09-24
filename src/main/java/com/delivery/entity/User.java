package com.delivery.entity;

import java.io.Serializable;

public class User implements Serializable {

	private long id;

	private String phoneNumber;

	private String password;

	private int roleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", phoneNumber='" + phoneNumber + '\'' +
				", roleId=" + roleId +
				'}';
	}
}

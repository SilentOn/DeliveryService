package com.delivery.dao;

import com.delivery.AppException;

public class DAOException extends AppException {
	public DAOException(String message) {
		super(message);
	}
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
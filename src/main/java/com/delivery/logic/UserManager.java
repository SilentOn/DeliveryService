package com.delivery.logic;


import com.delivery.dao.*;
import com.delivery.entity.Role;
import com.delivery.entity.User;
import com.delivery.entity.UserDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserManager {
	private static UserManager instance;

	private final DAOFactory daoFactory;

	private UserManager(DAOFactory daoFactory) throws DAOException {
		this.daoFactory = daoFactory;
	}

	public static synchronized UserManager getInstance(DAOFactory daoFactory) throws DAOException {
		if (instance == null) {
			instance = new UserManager(daoFactory);
		}
		return instance;
	}

	/*public List<User> getAllUsers() throws DAOException {
		List<User> users;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			users = daoFactory.getUserDAO().getAll(con);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all users", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return users;
	}*/

	public User getByPhoneNumber(String phoneNumber) throws DAOException {
		User user = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			user = daoFactory.getUserDAO().getByPhoneNumber(con, phoneNumber);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain user", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return user;
	}

	/*public void insertUser(User user) throws DAOException {
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			daoFactory.getUserDAO().insert(con, user);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());
			e.printStackTrace();

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't insert user", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
	}*/

	public User registerUser(String phoneNumber, String password, String role,
	                         String email, String firstName, String lastName) throws DAOException {
		int roleId;

		User user = new User();
		user.setPhoneNumber(phoneNumber);
		user.setPassword(password);

		UserDetails userDetails = new UserDetails();
		userDetails.setEmail(email);
		userDetails.setFirstName(firstName);
		userDetails.setLastName(lastName);

		Connection con = null;
		try {
			con = daoFactory.getConnection();

			roleId = daoFactory.getRoleDAO().getByName(con, role).getId();
			user.setRoleId(roleId);

			// change to user with id
			user = daoFactory.getUserDAO().insert(con, user);

			daoFactory.getUserDetailsDAO().insert(con, userDetails, user.getId());

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());
			ex.printStackTrace();

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return user;
	}

	public void editUser(User user, UserDetails userDetails) throws DAOException {
		Connection con = null;
		try {
			con = daoFactory.getConnection();

			try {
				daoFactory.getUserDAO().update(con, user);
			} catch (SQLException ex) {
				throw new SQLException("phone number already registered", ex);
			}

			try {
				daoFactory.getUserDetailsDAO().update(con, userDetails);
			} catch (SQLException ex) {
				throw new SQLException("email already registered", ex);
			}

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());
			ex.printStackTrace();

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
	}

	public void deleteUser(User user) throws DAOException {
		Connection con = null;
		try {
			con = daoFactory.getConnection();

			daoFactory.getUserDAO().delete(con, user);

			con.commit();
		} catch (SQLException ex) {
			// log
			System.err.println(ex.getMessage());
			ex.printStackTrace();

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			// close connection
			daoFactory.close(con);
		}
	}

	public UserDetails getUserDetails(long userId) throws DAOException {
		UserDetails userDetails = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			userDetails = daoFactory.getUserDetailsDAO().getByPK(con, userId);

			//log
			System.out.println("userDetails in UserManager ==> " + userDetails);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain userDetails", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return userDetails;
	}

	public UserDetails getUserDetailsByEmail(String email) throws DAOException {
		UserDetails userDetails = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			userDetails = daoFactory.getUserDetailsDAO().getByEmail(con, email);

			//log
			System.out.println("userDetails in UserManager ==> " + userDetails);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain userDetails", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return userDetails;
	}

	public boolean validate(String phoneNumber, String password) throws DAOException {
		boolean status = false;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			status = daoFactory.getUserDAO().validate(con, phoneNumber, password);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't validate", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return status;
	}

	public Role getRole(int id) throws DAOException {
		Role role = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			role = daoFactory.getRoleDAO().getByPK(con, id);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain role", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return role;
	}

	public Role getRole(String name) throws DAOException {
		Role role = null;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			role = daoFactory.getRoleDAO().getByName(con, name);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain role", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return role;
	}

	public List<Role> getAllRoles() throws DAOException {
		List<Role> roles;
		Connection con = null;
		try {
			con = daoFactory.getConnection();
			roles = daoFactory.getRoleDAO().getAll(con);
			con.commit();
		} catch (SQLException e) {
			// log
			System.err.println(e.getMessage());

			// rollback
			daoFactory.rollback(con);

			// throw my own exception
			throw new DAOException("Can't obtain all roles", e);
		} finally {
			// close connection
			daoFactory.close(con);
		}
		return roles;
	}
}

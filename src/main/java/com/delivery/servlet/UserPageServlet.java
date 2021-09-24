package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Role;
import com.delivery.entity.User;
import com.delivery.entity.UserDetails;
import com.delivery.listener.ContextListener;
import com.delivery.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserPageServlet", value = "/userPage")
public class UserPageServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(ContextListener.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String address;

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		//log
		System.out.println("user ==> " + user);

		UserDetails userDetails = null;
		Role role = null;
		List<Role> roles = null;


		if (getServletContext().getAttribute("messageEditUser") != null) {
			userDetails = (UserDetails) getServletContext().getAttribute("userDetails");
		} else {
			try {
				userDetails = UserManager.getInstance(DAOFactory.getDAOFactory()).getUserDetails(user.getId());

				//log
				System.out.println("userDetails ==> " + userDetails);
			} catch (DAOException ex) {
				// log
				log.error("can not obtain userDetails!", ex);
			}
		}

		if ("true".equals(request.getParameter("edit"))) {
			address = "jsp/editUser.jsp";
			try {
				roles = UserManager.getInstance(DAOFactory.getDAOFactory()).getAllRoles();

				//log
				System.out.println("roles ==> " + roles);
			} catch (DAOException ex) {
				// log
				System.err.println("can not obtain roles!");
			}
		} else {
			address = "jsp/userPage.jsp";
			try {
				role = UserManager.getInstance(DAOFactory.getDAOFactory()).getRole(user.getRoleId());

				//log
				System.out.println("role ==> " + role);
			} catch (DAOException ex) {
				// log
				System.err.println("can not obtain role!");
			}
		}

		request.setAttribute("userDetails", userDetails);
		request.setAttribute("roles", roles);
		request.setAttribute("role", role);
		request.getRequestDispatcher(address).forward(request, response);
	}
}

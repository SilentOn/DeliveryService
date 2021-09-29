package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Role;
import com.delivery.entity.User;
import com.delivery.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("LoginServlet#doPost");
		HttpSession session = request.getSession();

		String redirect = "jsp/login.jsp";
		String message = "";
		boolean valid;

		String phoneNumber = request.getParameter("userNumber");
		String password = request.getParameter("userPass");

		try {
			valid = UserManager.getInstance(DAOFactory.getDAOFactory()).validate(phoneNumber, password);
			if (valid) {
				redirect = "jsp/userPage.jsp";

				User user = UserManager.getInstance(DAOFactory.getDAOFactory()).getByPhoneNumber(phoneNumber);
				Role role = UserManager.getInstance(DAOFactory.getDAOFactory()).getRole(user.getRoleId());
				boolean isManager = role != null && role.getName() == Role.RoleName.MANAGER;

				// put user and isManager into session
				session.setAttribute("user", user);
				session.setAttribute("isManager", isManager);
				log.info("user " + user.getPhoneNumber() + " logged in");
			} else {
				message = "mismatch phone number or password!";
			}
		} catch (DAOException ex) {
			log.error("can't login", ex);
			message = ex.getMessage();
		}

		// if log in was needed for access to some page
		String redirectAttr = (String) session.getAttribute("redirect");
		session.removeAttribute("redirect");
		if (redirectAttr != null) {
			redirect = redirectAttr;
			session.removeAttribute("redirect");
		}

		log.debug("In LoginServlet for " + request.getContextPath() + request.getServletPath() +
				" redirect to: " + request.getContextPath() + redirect);

		session.setAttribute("messageLogin", message);
		response.sendRedirect(redirect);
	}
}
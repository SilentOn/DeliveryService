package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
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

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("LoginServlet#doPost");

		String redirect = "jsp/login.jsp";
		String message = "";
		boolean valid;

		String phoneNumber = request.getParameter("userNumber");
		String password = request.getParameter("userPass");

		try {
			valid = UserManager.getInstance(DAOFactory.getDAOFactory()).validate(phoneNumber, password);
			if (valid) {
				redirect = "jsp/userPage.jsp";

				// put user into session
				User user = UserManager.getInstance(DAOFactory.getDAOFactory()).getByPhoneNumber(phoneNumber);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				log.info("user " + user.getPhoneNumber() + " logged in");
			} else {
				message = "mismatch phone number or password!";
			}
		} catch (DAOException ex) {
			log.error("error",ex);

			message = ex.getMessage();
		}

		// if log in was needed for access to some page
		String redirectAttr = (String) request.getSession().getAttribute("redirect");
		if (redirectAttr != null) {
			redirect = redirectAttr;
			request.getSession().removeAttribute("redirect");
		}

		log.debug("In LoginServlet for " + request.getContextPath() + request.getServletPath() +
				" redirect to: " + request.getContextPath() + redirect);

		request.getSession().setAttribute("messageLogin", message);
		response.sendRedirect(redirect);
	}
}
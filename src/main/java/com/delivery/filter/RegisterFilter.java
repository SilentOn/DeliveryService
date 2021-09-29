package com.delivery.filter;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
import com.delivery.entity.UserDetails;
import com.delivery.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterFilter implements Filter {
	private static final Logger log = LogManager.getLogger(RegisterFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.trace("RegisterFilter#doFilter");
		HttpSession session = ((HttpServletRequest) request).getSession();

		String redirect = "jsp/register.jsp";
		String message;

		String phoneNumber = request.getParameter("userNumber");
		String password = request.getParameter("userPass");
		String passwordConf = request.getParameter("userPassConf");
		String email = request.getParameter("userEmail");
		String firstName = request.getParameter("userFirstName");
		String lastName = request.getParameter("userLastName");

		try {
			User user = UserManager.getInstance(DAOFactory.getDAOFactory()).getByPhoneNumber(phoneNumber);
			UserDetails userDetails = UserManager.getInstance(DAOFactory.getDAOFactory())
					.getUserDetailsByEmail(email);

			if (!password.equals(passwordConf)) {
				message = "The passwords entered do not match!";
			} else if (user != null) {
				// if in DB already present user with this phoneNumber
				message = "Phone number already registered!";
			} else if (userDetails != null) {
				// if in DB already present user with this email
				message = "Email already registered!";
			} else {
				log.debug("In RegisterFilter#doFilter for " + ((HttpServletRequest) request).getServletPath() + " chain.doFilter");
				chain.doFilter(request, response);
				return;
			}
		} catch (DAOException ex) {
			log.error("Can't obtain user or userDetails to check", ex);
			message = ex.getMessage();
		}

		session.setAttribute("messageRegister", message);
		session.setAttribute("userNumber", phoneNumber);
		session.setAttribute("userPass", password);
		session.setAttribute("userEmail", email);
		session.setAttribute("userFirstName", firstName);
		session.setAttribute("userLastName", lastName);

		((HttpServletResponse) response).sendRedirect(redirect);
	}
}
package com.delivery.filter;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Role;
import com.delivery.entity.User;
import com.delivery.entity.UserDetails;
import com.delivery.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserDetailsFilter implements Filter {
	private static final Logger log = LogManager.getLogger(UserDetailsFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.trace("UserDetailsFilter#doFilter");

		HttpSession session = ((HttpServletRequest) request).getSession(false);
		User user = (User) session.getAttribute("user");

		log.debug("user ==> " + user);

		UserDetails userDetails = null;
		Role role = null;


		if (request.getServletContext().getAttribute("messageEditUser") != null) {
			userDetails = (UserDetails) request.getServletContext().getAttribute("userDetails");
		} else {
			try {
				userDetails = UserManager.getInstance(DAOFactory.getDAOFactory()).getUserDetails(user.getId());

				log.debug("userDetails ==> " + userDetails);
			} catch (DAOException ex) {
				log.error("can not obtain userDetails!", ex);
			}
		}

		try {
			role = UserManager.getInstance(DAOFactory.getDAOFactory()).getRole(user.getRoleId());

			log.debug("role ==> " + role);
		} catch (DAOException ex) {
			log.error("can not obtain role", ex);
		}

		request.setAttribute("userDetails", userDetails);
		request.setAttribute("role", role);

		chain.doFilter(request, response);
	}
}
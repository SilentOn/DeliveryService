package com.delivery.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoggedRoleUserFilter implements Filter {
	private static final Logger log = LogManager.getLogger(LoggedRoleUserFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.trace("LoggedRoleUserFilter#doFilter");

		HttpServletRequest req = ((HttpServletRequest) request);
		HttpSession session = req.getSession();
		String forward = "/jsp/login.jsp";

		/*User user = (User) session.getAttribute("user");
		if (user != null) {
			try {
				Role role = UserManager.getInstance(DAOFactory.getDAOFactory()).getRole(Role.RoleName.USER);
				if (user.getRoleId() == role.getId()) {
					log.debug("In LoggedRoleUserFilter for " + req.getServletPath() + " chain.doFilter");
					chain.doFilter(request, response);
					return;
				}
			} catch (DAOException ex) {
				log.error("can't obtain role", ex);
			}
			log.debug("user logged not as 'user'");
		}*/


		Boolean isManager = (Boolean) session.getAttribute("isManager");
		if (isManager != null && !isManager) {
			log.debug("In LoggedRoleUserFilter for " + req.getServletPath() + " chain.doFilter");
			chain.doFilter(request, response);
			return;
		}


		log.debug("In LoggedRoleUserFilter for " + req.getContextPath() + req.getServletPath() +
				" forward to: " + req.getContextPath() + forward);
		session.setAttribute("redirect", req.getContextPath() + req.getServletPath());
		request.setAttribute("messageLogin", "You must be logged as 'user'!");
		request.getRequestDispatcher(forward).forward(request, response);
	}
}
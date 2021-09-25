package com.delivery.filter;

import com.delivery.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoggedFilter",
		urlPatterns = {"/jsp/userPage.jsp", "/jsp/editUser.jsp", "/invoiceListPage", "/deleteUser", "/receiptListPage"})
public class LoggedFilter implements Filter {
	private static final Logger log = LogManager.getLogger(LoggedFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.trace("LoggedFilter#doFilter");

		HttpServletRequest req = ((HttpServletRequest) request);
		HttpSession session = req.getSession();
		String forward = "/jsp/login.jsp";

		User user = (User) session.getAttribute("user");
		if (user != null) {
			log.debug("In LoggedFilter for " + req.getServletPath() + " chain.doFilter");
			chain.doFilter(request, response);
			return;
		}


		log.debug("In LoggedFilter for " + req.getContextPath() + req.getServletPath() +
				" forward to: " + req.getContextPath() + forward);
		session.setAttribute("redirect", req.getContextPath() + req.getServletPath());
		request.setAttribute("messageLogin", "You must be logged first!");
		request.getRequestDispatcher(forward).forward(request, response);
	}
}
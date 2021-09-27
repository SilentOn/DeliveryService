package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
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

@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(DeleteUserServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("DeleteUserServlet#doPost");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String redirect = "index.jsp";

		try {
			UserManager.getInstance(DAOFactory.getDAOFactory()).deleteUser(user);
			log.info("user " + user.getPhoneNumber() + " deleted");
		} catch (DAOException ex) {
			log.error("can't delete user", ex);
			session.setAttribute("errorMessage", ex.getMessage());
			redirect = "jsp/error.jsp";
		}

		session.invalidate();
		response.sendRedirect(redirect);
	}
}
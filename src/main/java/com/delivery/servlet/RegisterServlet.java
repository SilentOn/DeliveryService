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

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(RegisterServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("RegisterServlet#doPost");
		HttpSession session = request.getSession();

		String redirect = "jsp/userPage.jsp";

		String phoneNumber = request.getParameter("userNumber");
		String password = request.getParameter("userPass");
		String email = request.getParameter("userEmail");
		String firstName = request.getParameter("userFirstName");
		String lastName = request.getParameter("userLastName");

		try {
			User user = UserManager.getInstance(DAOFactory.getDAOFactory())
					.registerUser(phoneNumber, password, email, firstName, lastName);

			session.setAttribute("user", user);
		} catch (DAOException ex) {
			log.error("Can't register user", ex);
			session.setAttribute("errorMessage", "Can't register user");
			redirect = "jsp/error.jsp";
		}

		response.sendRedirect(redirect);
	}
}
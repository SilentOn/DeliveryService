package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
import com.delivery.entity.UserDetails;
import com.delivery.logic.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String redirect = "registerpage";
		String message = "success!";

		String phoneNumber = request.getParameter("userNumber");
		String password = request.getParameter("userPass");
		String passwordConf = request.getParameter("userPassConf");
		String role = request.getParameter("userRole");
		String email = request.getParameter("userEmail");
		String firstName = request.getParameter("userFirstName");
		String lastName = request.getParameter("userLastName");

		try {
			User user = UserManager.getInstance(DAOFactory.getDAOFactory()).getByPhoneNumber(phoneNumber);
			UserDetails userDetails = UserManager.getInstance(DAOFactory.getDAOFactory())
					.getUserDetailsByEmail(email);
			if (!password.equals(passwordConf)) {
				message = "the passwords entered do not match";
			} else if (user != null) {
				// if in DB already present user with this phoneNumber
				message = "phone number already registered";
			} else if (userDetails != null) {
				// if in DB already present user with this email
				message = "email already registered";
			} else {

				user = UserManager.getInstance(DAOFactory.getDAOFactory())
						.registerUser(phoneNumber, password, role, email, firstName, lastName);

				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				redirect = "userPage";
			}

		} catch (DAOException ex) {
			// log
			ex.printStackTrace();

			message = ex.getMessage();
		}

		// context???
		getServletContext().setAttribute("messageRegister", message);
		getServletContext().setAttribute("userNumber", phoneNumber);
		getServletContext().setAttribute("userPass", password);
		getServletContext().setAttribute("userRole", role);
		getServletContext().setAttribute("userEmail", email);
		getServletContext().setAttribute("userFirstName", firstName);
		getServletContext().setAttribute("userLastName", lastName);

		response.sendRedirect(redirect);
	}
}
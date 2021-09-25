package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
import com.delivery.entity.UserDetails;
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

@WebServlet(name = "EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(EditUserServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "editUser.jsp";
		String message = "success!";

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		User editUser;
		UserDetails editUserDetails;

		String password = request.getParameter("userPass");
		String passwordConf = request.getParameter("userPassConf");
		String email = request.getParameter("userEmail");
		String firstName = request.getParameter("userFirstName");
		String lastName = request.getParameter("userLastName");

		editUser = new User();
		editUser.setId(user.getId());
		editUser.setPhoneNumber(user.getPhoneNumber());
		editUser.setPassword(password);

		editUserDetails = new UserDetails();
		editUserDetails.setId(user.getId());
		editUserDetails.setEmail(email);
		editUserDetails.setFirstName(firstName);
		editUserDetails.setLastName(lastName);

		try {
			UserDetails userDetails = UserManager.getInstance(DAOFactory.getDAOFactory())
					.getUserDetailsByEmail(email);
			if (!password.equals(passwordConf)) {
				message = "the passwords entered do not match";
			} else if (userDetails != null && userDetails.getId() != user.getId()) {
				// if in DB already present user with this email and it's not this user
				message = "email already registered";
			} else {
				UserManager.getInstance(DAOFactory.getDAOFactory())
						.editUser(editUser, editUserDetails);

				session.setAttribute("user", editUser);
				redirect = "userPage.jsp";
			}
		} catch (DAOException ex) {
			// log
			ex.printStackTrace();

			message = ex.getMessage();
		}


		System.out.println("===");
		System.out.println("editUserDetails " + editUserDetails);
		System.out.println("message " + message);
		System.out.println("redirect " + redirect);
		System.out.println("===");
		getServletContext().setAttribute("userDetails", editUserDetails);
		getServletContext().setAttribute("messageEditUser", message);

		response.sendRedirect(redirect);
	}
}
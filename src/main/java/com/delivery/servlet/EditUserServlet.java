package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
import com.delivery.entity.UserDetails;
import com.delivery.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("EditUserServlet#doPost");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String redirect = "jsp/userPage.jsp";

		User editUser;
		UserDetails editUserDetails;

		String password = request.getParameter("userPass");
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
			UserManager.getInstance(DAOFactory.getDAOFactory())
					.editUser(editUser, editUserDetails);

			// change user in session to editedUser
			session.setAttribute("user", editUser);
		} catch (DAOException ex) {
			log.error("Can't edit user", ex);
			session.setAttribute("errorMessage", "Can't edit user");
			redirect = "jsp/error.jsp";
		}

		log.debug("In EditUserServlet#doPost redirect to: " + request.getContextPath() + redirect);
		response.sendRedirect(redirect);
	}
}
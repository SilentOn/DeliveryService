package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
import com.delivery.logic.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
	/*@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("jsp/login.jsp");
	}*/

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "jsp/login.jsp";
		String message = "";
		boolean valid = false;

		String phoneNumber = request.getParameter("userNumber");
		String password = request.getParameter("userPass");

		try {
			valid = UserManager.getInstance(DAOFactory.getDAOFactory()).validate(phoneNumber, password);
			if (valid) {
				redirect = "userPage";

				//long id= UserManager.getInstance(DAOFactory.getDAOFactory()).getByPhoneNumber(phoneNumber).getId();
				User user = UserManager.getInstance(DAOFactory.getDAOFactory()).getByPhoneNumber(phoneNumber);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			} else {
				message = "mismatch phone number or password!";
			}
		} catch (DAOException ex) {
			// log
			ex.printStackTrace();

			message = ex.getMessage();
		}

		// context???
		getServletContext().setAttribute("messageLogin", message);

		response.sendRedirect(redirect);
		/*request.setAttribute("redirect",);
		doGet(request, response);*/
	}
}
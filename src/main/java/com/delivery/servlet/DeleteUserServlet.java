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

@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		try {
			UserManager.getInstance(DAOFactory.getDAOFactory()).deleteUser(user);
		} catch (DAOException ex) {
			// log
			ex.printStackTrace();
		}

		session.invalidate();
		response.sendRedirect("index.jsp");
	}
}
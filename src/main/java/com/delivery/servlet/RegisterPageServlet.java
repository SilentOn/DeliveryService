package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Role;
import com.delivery.logic.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterPageServlet", value = "/registerpage")
public class RegisterPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Role> roles = null;

		try {
			roles = UserManager.getInstance(DAOFactory.getDAOFactory()).getAllRoles();
		} catch (DAOException ex) {
			// log
			System.err.println("can not obtain roles!");
		}
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
	}
}

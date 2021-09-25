package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
import com.delivery.logic.InvoiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PayReceiptServlet", value = "/payReceipt")
public class PayReceiptServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		long receiptId = Long.parseLong(request.getParameter("receiptId"));

		try {
			InvoiceManager.getInstance(DAOFactory.getDAOFactory()).payReceipt(user, receiptId);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("receiptListPage");
	}
}

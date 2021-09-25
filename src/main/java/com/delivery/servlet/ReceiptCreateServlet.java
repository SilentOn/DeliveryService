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

@WebServlet(name = "ReceiptCreateServlet", value = "/receiptCreate")
public class ReceiptCreateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		long invoiceId = Long.parseLong(request.getParameter("invoiceId"));

		try {
			InvoiceManager.getInstance(DAOFactory.getDAOFactory()).createReceipt(user, invoiceId);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("invoiceListPage");
	}
}

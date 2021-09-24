package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Receipt;
import com.delivery.entity.ReceiptStatus;
import com.delivery.entity.User;
import com.delivery.logic.InvoiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReceiptListPageServlet", value = "/receiptListPage")
public class ReceiptListPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			getServletContext().setAttribute("errorMessage", "you must be logged \"user\"");
			response.sendRedirect("jsp/error.jsp");
			return;
		}
		User user = (User) session.getAttribute("user");
		if (user == null) {
			getServletContext().setAttribute("errorMessage", "you must be logged user");
			response.sendRedirect("jsp/error.jsp");
			return;
		}

		List<Receipt> receipts = null;
		List<ReceiptStatus> receiptStatuses = null;

		try {
			if (user.getRoleId() == 1) {
				receipts = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllReceipts(user);
			} else {
				receipts = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllReceipts();
			}
			receiptStatuses = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllReceiptStatuses();
		} catch (DAOException ex) {
			// log
			System.err.println(ex.getMessage());
		}

		System.out.println("=====");
		System.out.println("receipts == >" + receipts);
		System.out.println("receiptStatuses == >" + receiptStatuses);
		System.out.println("=====");

		request.setAttribute("receipts", receipts);
		request.setAttribute("receiptStatuses", receiptStatuses);

		request.getRequestDispatcher("jsp/receiptList.jsp").forward(request, response);
	}
}

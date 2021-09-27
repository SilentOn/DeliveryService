package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.User;
import com.delivery.logic.InvoiceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PayReceiptServlet", value = "/payReceipt")
public class PayReceiptServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(PayReceiptServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("PayReceiptServlet#doPost");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String redirect = "receiptListPage";

		long receiptId = Long.parseLong(request.getParameter("receiptId"));

		try {
			InvoiceManager.getInstance(DAOFactory.getDAOFactory()).payReceipt(receiptId);
		} catch (DAOException ex) {
			log.error("Can't pay the receipt", ex);
			session.setAttribute("errorMessage", "Can't pay the receipt");
			redirect = "jsp/error.jsp";
		}

		response.sendRedirect(redirect);
	}
}

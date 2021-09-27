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

@WebServlet(name = "ReceiptCreateServlet", value = "/receiptCreate")
public class ReceiptCreateServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(ReceiptCreateServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("ReceiptCreateServlet#doPost");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String redirect = "invoiceListPage";

		long invoiceId = Long.parseLong(request.getParameter("invoiceId"));

		try {
			InvoiceManager.getInstance(DAOFactory.getDAOFactory()).createReceipt(user, invoiceId);
		} catch (DAOException ex) {
			log.error("Can't create receipt", ex);
			session.setAttribute("errorMessage", "Can't create receipt");
			redirect = "jsp/error.jsp";
		}

		response.sendRedirect(redirect);
	}
}

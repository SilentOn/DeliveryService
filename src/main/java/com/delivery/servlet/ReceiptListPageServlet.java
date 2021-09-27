package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Receipt;
import com.delivery.entity.ReceiptStatus;
import com.delivery.entity.Role;
import com.delivery.entity.User;
import com.delivery.logic.InvoiceManager;
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
import java.util.List;

@WebServlet(name = "ReceiptListPageServlet", value = "/receiptListPage")
public class ReceiptListPageServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(ReceiptListPageServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("ReceiptListPageServlet#doGet");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String forward = "/jsp/receiptList.jsp";

		List<Receipt> receipts = null;
		List<ReceiptStatus> receiptStatuses = null;

		try {
			Role role = UserManager.getInstance(DAOFactory.getDAOFactory()).getRole(user.getRoleId());

			if (role.getName() == Role.RoleName.USER) {
				receipts = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllReceipts(user);
			} else {
				receipts = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllReceipts();
			}
			receiptStatuses = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllReceiptStatuses();
		} catch (DAOException ex) {
			log.error("can not obtain receipts", ex);
			session.setAttribute("errorMessage", "can not obtain receipts");
			forward = "/jsp/error.jsp";
		}

		request.setAttribute("receipts", receipts);
		request.setAttribute("receiptStatuses", receiptStatuses);
		request.getRequestDispatcher(forward).forward(request, response);
	}
}

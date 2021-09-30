package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Receipt;
import com.delivery.entity.ReceiptStatus;
import com.delivery.entity.User;
import com.delivery.logic.InvoiceManager;
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
		boolean isManager = (boolean) session.getAttribute("isManager");

		String forward = "/jsp/receiptList.jsp";

		List<Receipt> receipts = null;
		List<ReceiptStatus> receiptStatuses = null;
		Integer pagesCount = null;
		String sortBy = request.getParameter("sort");
		String filterBy = request.getParameter("filter");
		String itemsOnPage = request.getParameter("itemsOnPage");
		String pageString = request.getParameter("p");
		if (sortBy == null || filterBy == null || itemsOnPage == null) {
			sortBy = "none";
			filterBy = "all";
			itemsOnPage = "5";
		}
		if (pageString == null) {
			pageString = "1";
		}
		int page = Integer.parseInt(pageString);

		request.getParameterMap().forEach((o, o2) -> log.debug("key: {}; value: {}", o, ((String[]) o2)[0]));

		try {
			receiptStatuses = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllReceiptStatuses();

			if (isManager) {
				user = null;
			}
			receipts = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getReceipts(user, sortBy, filterBy, itemsOnPage, page);
			pagesCount = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getPagesCountForReceipts(user, filterBy, itemsOnPage);
		} catch (DAOException ex) {
			log.error("can not obtain receipts", ex);
			session.setAttribute("errorMessage", "can not obtain receipts");
			forward = "/jsp/error.jsp";
		}

		request.setAttribute("receipts", receipts);
		request.setAttribute("receiptStatuses", receiptStatuses);
		request.setAttribute("pagesCount", pagesCount);
		request.setAttribute("page", page);
		request.getRequestDispatcher(forward).forward(request, response);
	}
}

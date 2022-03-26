package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.*;
import com.delivery.logic.InvoiceManager;
import com.delivery.logic.RegionManager;
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

@WebServlet(name = "InvoiceListPageServlet", value = "/invoiceListPage")
public class InvoiceListPageServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(InvoiceListPageServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("InvoiceListPageServlet#doGet");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		boolean isManager = (boolean) session.getAttribute("isManager");

		String forward = "/jsp/invoiceList.jsp";

		List<Invoice> invoices = null;
		List<User> users = null;
		List<City> cities = null;
		List<InvoiceStatus> invoiceStatuses = null;
		List<Address> addresses = null;
		Integer pagesCount = null;
		String sortBy = request.getParameter("sort");
		String filterBy = request.getParameter("filter");
		String itemsOnPage = request.getParameter("itemsOnPage");
		String pageString = request.getParameter("p");
		String filterByUser = request.getParameter("filterByUser");
		if (sortBy == null || filterBy == null || itemsOnPage == null) {
			sortBy = "none";
			filterBy = "all";
			itemsOnPage = "5";
			filterByUser = "all";
		}
		if (pageString == null) {
			pageString = "1";
		}
		int page = Integer.parseInt(pageString);

		request.getParameterMap().forEach((o, o2) -> log.debug("key: {}; value: {}", o, ((String[]) o2)[0]));

		try {
			cities = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllCities();
			invoiceStatuses = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllInvoiceStatuses();
			addresses = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllAddresses();

			if (isManager && "all".equals(filterByUser)) {
				user = null;
			} else if (isManager) {
				user = UserManager.getInstance(DAOFactory.getDAOFactory()).getById(Long.parseLong(filterByUser));
			}
			invoices = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getInvoices(user, sortBy, filterBy, itemsOnPage, page);
			users = UserManager.getInstance(DAOFactory.getDAOFactory()).getAllUsers();
			pagesCount = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getPagesCountForInvoices(user, filterBy, itemsOnPage);
		} catch (DAOException ex) {
			log.error("can not obtain invoices", ex);
			session.setAttribute("errorMessage", "can not obtain invoices");
			forward = "/jsp/error.jsp";
		}

		request.setAttribute("invoices", invoices);
		request.setAttribute("users", users);
		request.setAttribute("cities", cities);
		request.setAttribute("invoiceStatuses", invoiceStatuses);
		request.setAttribute("addresses", addresses);
		request.setAttribute("pagesCount", pagesCount);
		request.setAttribute("page", page);
		request.getRequestDispatcher(forward).forward(request, response);
	}
}
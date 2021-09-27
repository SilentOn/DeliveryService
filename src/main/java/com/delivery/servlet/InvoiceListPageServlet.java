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

		String forward = "/jsp/invoiceList.jsp";

		List<Invoice> invoices = null;
		List<City> cities = null;
		List<InvoiceStatus> invoiceStatuses = null;
		List<Address> addresses = null;

		try {
			Role role = UserManager.getInstance(DAOFactory.getDAOFactory()).getRole(user.getRoleId());

			if (role.getName() == Role.RoleName.USER) {
				invoices = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllInvoiceByUser(user);
			} else {
				invoices = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAll();
			}
			cities = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllCities();
			invoiceStatuses = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllInvoiceStatuses();
			addresses = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllAddresses();
		} catch (DAOException ex) {
			log.error("can not obtain invoices", ex);
			session.setAttribute("errorMessage", "can not obtain invoices");
			forward = "/jsp/error.jsp";
		}

		request.setAttribute("invoices", invoices);
		request.setAttribute("cities", cities);
		request.setAttribute("invoiceStatuses", invoiceStatuses);
		request.setAttribute("addresses", addresses);
		request.getRequestDispatcher(forward).forward(request, response);
	}
}
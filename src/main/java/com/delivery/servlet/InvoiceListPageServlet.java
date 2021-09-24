package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.*;
import com.delivery.logic.InvoiceManager;
import com.delivery.logic.RegionManager;

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

		List<Invoice> invoices = null;
		List<City> cities = null;
		List<InvoiceStatus> invoiceStatuses = null;
		List<Address> addresses = null;

		try {
			if (user.getRoleId() == 1) {
				invoices = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllInvoiceByUser(user);
			} else {
				invoices = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAll();
			}
			cities = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllCities();
			invoiceStatuses = InvoiceManager.getInstance(DAOFactory.getDAOFactory()).getAllInvoiceStatuses();
			addresses = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllAddresses();
		} catch (DAOException ex) {
			// log
			System.err.println(ex.getMessage());
		}

		System.out.println("=====");
		System.out.println("invoices == >" + invoices);
		System.out.println("cities == >" + cities);
		System.out.println("invoiceStatuses == >" + invoiceStatuses);
		System.out.println("addresses == >" + addresses);
		System.out.println("=====");

		request.setAttribute("invoices", invoices);
		request.setAttribute("cities", cities);
		request.setAttribute("invoiceStatuses", invoiceStatuses);
		request.setAttribute("addresses", addresses);

		request.getRequestDispatcher("jsp/invoiceList.jsp").forward(request, response);
	}

	/*@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(false);
		if (session == null) {
			getServletContext().setAttribute("errorMessage", "you must be logged \"user\"");
			response.sendRedirect("jsp/error.jsp");
			return;
		}


	}*/
}

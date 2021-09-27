package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.InvoiceHasCargo;
import com.delivery.entity.User;
import com.delivery.filter.InvoiceCreateFilter;
import com.delivery.logic.InvoiceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InvoiceCreateServlet", value = "/invoiceCreate")
public class InvoiceCreateServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(InvoiceCreateFilter.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("InvoiceCreateServlet#doPost");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String redirect = "invoiceListPage";

		String regionFrom = request.getParameter("invoiceRegionFrom");
		String cityFrom = request.getParameter("invoiceCityFrom");
		String addressFrom = request.getParameter("invoiceAddressFrom");
		String regionTo = request.getParameter("invoiceRegionTo");
		String cityTo = request.getParameter("invoiceCityTo");
		String addressTo = request.getParameter("invoiceAddressTo");
		Double estimate = Double.valueOf(request.getParameter("estimate"));

		@SuppressWarnings("unchecked")
		List<InvoiceHasCargo> cargos = (List<InvoiceHasCargo>) session.getAttribute("cargos");
		session.removeAttribute("cargos");

		try {
			InvoiceManager.getInstance(DAOFactory.getDAOFactory())
					.insertInvoice(user, regionFrom, cityFrom, addressFrom, regionTo, cityTo, addressTo, estimate, cargos);
		} catch (DAOException ex) {
			log.error("Can't create invoice", ex);
			session.setAttribute("errorMessage", "Can't create invoice");
			redirect = "jsp/error.jsp";
		}

		response.sendRedirect(redirect);
	}
}
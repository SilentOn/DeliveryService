package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.InvoiceHasCargo;
import com.delivery.entity.Receipt;
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

@WebServlet(name = "CalculateCostServlet", value = "/calculateCost")
public class CalculateCostServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(CalculateCostServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace("CalculateCostServlet#doPost");
		HttpSession session = request.getSession();

		Receipt receipt = null;

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
			receipt = InvoiceManager.getInstance(DAOFactory.getDAOFactory())
					.calculateReceipt(regionFrom, cityFrom, addressFrom, regionTo, cityTo, addressTo, estimate, cargos);
		} catch (DAOException ex) {
			log.error("can not calculate the cost", ex);
			request.getSession().setAttribute("cost", ex);
		}

		if (receipt != null) {
			request.getSession().setAttribute("cost", receipt.getToPay());
		}
		response.sendRedirect("jsp/calculateCost.jsp");
	}
}
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InvoicePageServlet", value = "/invoicePage")
public class InvoicePageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			getServletContext().setAttribute("errorMessage", "you must be logged \"user\"");
			response.sendRedirect("jsp/error.jsp");
			return;
		}
		User user = (User) session.getAttribute("user");
		if (user == null || user.getRoleId() == 2) {
			getServletContext().setAttribute("errorMessage", "you must be logged \"user\"");
			response.sendRedirect("jsp/error.jsp");
			return;
		}

		List<Region> regions = null;
		List<City> cities = null;
		List<Address> addresses = null;

		try {
			regions = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllRegions();
			cities = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllCities();
			addresses = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllAddresses();
		} catch (DAOException ex) {
			// log
			System.err.println(ex.getMessage());
		}

		System.out.println("=====");
		System.out.println("regions == >" + regions);
		System.out.println("cities == >" + cities);
		System.out.println("addresses == >" + addresses);
		System.out.println("=====");

		request.setAttribute("regions", regions);
		request.setAttribute("cities", cities);
		request.setAttribute("addresses", addresses);

		request.getRequestDispatcher("jsp/createInvoice.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			getServletContext().setAttribute("errorMessage", "you must be logged \"user\"");
			response.sendRedirect("jsp/error.jsp");
			return;
		}
		User user = (User) session.getAttribute("user");
		if (user == null || user.getRoleId() == 2) {
			getServletContext().setAttribute("errorMessage", "you must be logged \"user\"");
			response.sendRedirect("jsp/error.jsp");
			return;
		}

		String regionFrom = request.getParameter("invoiceRegionFrom");
		String cityFrom = request.getParameter("invoiceCityFrom");
		String addressFrom = request.getParameter("invoiceAddressFrom");
		String regionTo = request.getParameter("invoiceRegionTo");
		String cityTo = request.getParameter("invoiceCityTo");
		String addressTo = request.getParameter("invoiceAddressTo");
		Double estimate = Double.valueOf(request.getParameter("estimate"));

		List<InvoiceHasCargo> cargos = new ArrayList<>();
		List<Integer> cargoTypes = new ArrayList<>();
		List<Float> weights = new ArrayList<>();
		List<Float> lengths = new ArrayList<>();
		List<Float> widths = new ArrayList<>();
		List<Float> heights = new ArrayList<>();
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {

			// log
			//------------------------------------------------------------------------------------------
			System.out.println(entry.getKey());
			System.out.println(entry.getValue()[0]);
			//------------------------------------------------------------------------------------------

			if (entry.getKey().matches("^cargoType[0-9]+")) {
				cargoTypes.add(Integer.valueOf(entry.getValue()[0]));
			} else if (entry.getKey().matches("^weight[0-9]+")) {
				weights.add(Float.valueOf(entry.getValue()[0]));
			} else if (entry.getKey().matches("^length[0-9]+")) {
				lengths.add(Float.valueOf(entry.getValue()[0]));
			} else if (entry.getKey().matches("^width[0-9]+")) {
				widths.add(Float.valueOf(entry.getValue()[0]));
			} else if (entry.getKey().matches("^height[0-9]+")) {
				heights.add(Float.valueOf(entry.getValue()[0]));
			}
		}
		for (int i = 0; i < cargoTypes.size(); i++) {
			InvoiceHasCargo invoiceHasCargo = new InvoiceHasCargo();
			invoiceHasCargo.setCargoId(cargoTypes.get(i));
			invoiceHasCargo.setWeight(weights.get(i));
			invoiceHasCargo.setLength(lengths.get(i));
			invoiceHasCargo.setWidth(widths.get(i));
			invoiceHasCargo.setHeight(heights.get(i));
			cargos.add(invoiceHasCargo);
		}

		// log
		//------------------------------------------------------------------------------------------
		for (InvoiceHasCargo crg : cargos) {
			System.out.println(crg);
		}
		//------------------------------------------------------------------------------------------

		try {
			InvoiceManager.getInstance(DAOFactory.getDAOFactory())
					.insertInvoice(user, regionFrom, cityFrom, addressFrom, regionTo, cityTo, addressTo, estimate, cargos);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("invoiceListPage");
	}
}

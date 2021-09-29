package com.delivery.filter;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.Address;
import com.delivery.entity.City;
import com.delivery.entity.Region;
import com.delivery.logic.RegionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class InvoicePageFilter implements Filter {
	private static final Logger log = LogManager.getLogger(InvoicePageFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.trace("InvoicePageFilter#doFilter");

		List<Region> regions;
		List<City> cities;
		List<Address> addresses;

		try {
			regions = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllRegions();
			cities = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllCities();
			addresses = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllAddresses();
		} catch (DAOException ex) {
			log.error("can not obtain info for invoice page", ex);
			((HttpServletRequest) request).getSession()
					.setAttribute("errorMessage", "can not obtain info for invoice page");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}

		request.setAttribute("regions", regions);
		request.setAttribute("cities", cities);
		request.setAttribute("addresses", addresses);

		chain.doFilter(request, response);
	}
}
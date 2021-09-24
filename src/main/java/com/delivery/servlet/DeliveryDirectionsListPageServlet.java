package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.City;
import com.delivery.entity.Region;
import com.delivery.logic.RegionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DeliveryDirectionsListPageServlet", value = "/deliveryDirectionsList")
public class DeliveryDirectionsListPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Region> regions = null;
		List<City> cities = null;

		try {
			regions = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllRegions();
			cities = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllCities();
		} catch (DAOException ex) {
			// log
			System.err.println(ex.getMessage());
		}

		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
				case "sort":
					String citySort = request.getParameter("city");
					switch (citySort) {
						case "asc":
							cities.sort(Comparator.comparing(City::getCityTitle));
							break;
						case "desc":
							cities.sort((city1, city2) -> -city1.getCityTitle().compareTo(city2.getCityTitle()));
					}
					break;
				case "filter":
					String filter = request.getParameter("filter");
					if (!"all".equals(filter)) {
						long regionId = -1;
						for (Region reg : regions) {
							if (reg.getRegionTitle().equals(filter)) {
								regionId = reg.getId();
							}
						}
						long finalRegionId = regionId;
						cities = cities.stream().filter(city -> city.getRegionId() == finalRegionId).collect(Collectors.toList());
					}
			}
		}


		System.out.println("=====");
		System.out.println("regions == >" + regions);
		System.out.println("cities == >" + cities);
		System.out.println("=====");

		request.setAttribute("regions", regions);
		request.setAttribute("cities", cities);

		request.getRequestDispatcher("jsp/deliveryDirectionsList.jsp").forward(request, response);
	}
}

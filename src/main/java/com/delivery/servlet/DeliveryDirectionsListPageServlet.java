package com.delivery.servlet;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.City;
import com.delivery.entity.Region;
import com.delivery.logic.RegionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeliveryDirectionsListPageServlet", value = "/deliveryDirectionsList")
public class DeliveryDirectionsListPageServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(DeliveryDirectionsListPageServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("DeliveryDirectionsListPageServlet#doGet");
		String forward = "jsp/deliveryDirectionsList.jsp";

		List<Region> regions = null;
		List<City> cities = null;
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
			regions = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllRegions();
			cities = RegionManager.getInstance(DAOFactory.getDAOFactory()).getCities(sortBy, filterBy, itemsOnPage, page);
			pagesCount = RegionManager.getInstance(DAOFactory.getDAOFactory()).getPagesCountForCities(filterBy, itemsOnPage);
		} catch (DAOException ex) {
			log.error("Can't obtain cities and regions", ex);
			request.getSession().setAttribute("errorMessage", "Can't obtain cities and regions");
			forward = "/jsp/error.jsp";
		}

		request.setAttribute("regions", regions);
		request.setAttribute("cities", cities);
		request.setAttribute("pagesCount", pagesCount);
		request.setAttribute("page", page);
		request.getRequestDispatcher(forward).forward(request, response);
	}
}

		/*String action = request.getParameter("action");
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
		}*/
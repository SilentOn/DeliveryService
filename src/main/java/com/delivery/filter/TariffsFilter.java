package com.delivery.filter;

import com.delivery.dao.DAOException;
import com.delivery.dao.DAOFactory;
import com.delivery.entity.TariffZone;
import com.delivery.entity.TariffZoneHasWeightTariff;
import com.delivery.entity.WeightTariff;
import com.delivery.logic.RegionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TariffsFilter implements Filter {
	private static final Logger log = LogManager.getLogger(TariffsFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.trace("TariffsFilter#doFilter");
		HttpServletRequest req = ((HttpServletRequest) request);
		HttpSession session = req.getSession();

		List<TariffZone> tariffZones = null;
		List<WeightTariff> weightTariffs = null;
		List<TariffZoneHasWeightTariff> tariffZoneHasWeightTariffs = null;

		try {
			tariffZones = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllTariffZones();

			log.debug("tariffZones ==> " + tariffZones);
		} catch (DAOException ex) {
			log.error("can not obtain tariffZones!", ex);
		}

		try {
			weightTariffs = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllWeightTariffs();

			log.debug("weightTariffs ==> " + weightTariffs);
		} catch (DAOException ex) {
			log.error("can not obtain weightTariffs!", ex);
		}

		try {
			tariffZoneHasWeightTariffs = RegionManager.getInstance(DAOFactory.getDAOFactory()).getAllTariffZoneHasWeightTariffs();

			log.debug("tariffZoneHasWeightTariffs ==> " + tariffZoneHasWeightTariffs);
		} catch (DAOException ex) {
			log.error("can not obtain tariffZoneHasWeightTariffs!", ex);
		}

		request.setAttribute("tariffZones", tariffZones);
		request.setAttribute("weightTariffs", weightTariffs);
		request.setAttribute("tariffZoneHasWeightTariffs", tariffZoneHasWeightTariffs);
		chain.doFilter(request, response);
	}
}
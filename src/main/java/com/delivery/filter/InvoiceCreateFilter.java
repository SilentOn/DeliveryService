package com.delivery.filter;

import com.delivery.entity.InvoiceHasCargo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "InvoiceCreateFilter", urlPatterns = {"/invoiceCreate", "/calculateCost"})
public class InvoiceCreateFilter implements Filter {
	private static final Logger log = LogManager.getLogger(InvoiceCreateFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.trace("InvoiceCreateFilter#doFilter");
		HttpServletRequest req = ((HttpServletRequest) request);
		HttpSession session = req.getSession();

		List<InvoiceHasCargo> cargos = new ArrayList<>();
		List<Integer> cargoTypes = new ArrayList<>();
		List<Float> weights = new ArrayList<>();
		List<Float> lengths = new ArrayList<>();
		List<Float> widths = new ArrayList<>();
		List<Float> heights = new ArrayList<>();
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
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

		session.setAttribute("cargos", cargos);
		chain.doFilter(request, response);
	}
}
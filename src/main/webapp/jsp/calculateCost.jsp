<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Calculate cost</title>

	<%@include file="/jsp/connectHeader.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/addCargo.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectOptions.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectCargo.js"></script>
</head>
<body>
<%@include file="/jsp/navNotLogged.jsp" %>


<div class="container">
	<form action="${pageContext.request.contextPath}/calculateCost" method="post">
		<div class="row">
			<c:if test="${not empty sessionScope.cost}">
			<h2>Calculated cost = ${sessionScope.cost}</h2>
				${sessionScope.remove("cost")}
			</c:if>

			<div class="row card-panel">
				<div class="from col s5">
					<div class="row">
						<label for="invoiceRegionFrom">Region from</label>
						<select class="browser-default" id="invoiceRegionFrom" name="invoiceRegionFrom"
						        required>
							<option hidden selected value=""></option>
							<c:forEach items="${requestScope.regions}" var="region">
								<option data-region-id="${region.id}">
										${region.regionTitle}
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="row">
						<label for="invoiceCityFrom">City from</label>
						<input disabled list="citiesFromList" id="invoiceCityFrom" name="invoiceCityFrom"
						       autocomplete="off"
						       required>
						<datalist id="citiesFromList">
							<c:forEach items="${requestScope.cities}" var="city">
								<option data-region-id="${city.regionId}" data-city-id="${city.id}">
										${city.cityTitle}
								</option>
							</c:forEach>
						</datalist>
					</div>

					<div class="row">
						<label for="invoiceAddressFrom">Address from</label>
						<input disabled list="addressesFromList" id="invoiceAddressFrom" name="invoiceAddressFrom"
						       autocomplete="off"
						       required>
						<datalist id="addressesFromList">
							<c:forEach items="${requestScope.addresses}" var="address">
								<option data-city-id="${address.cityId}" data-address-id="${address.id}">
										${address.addressTitle}
								</option>
							</c:forEach>
						</datalist>
					</div>
				</div>

				<div class="to col s5 offset-s1">
					<div class="row">
						<label for="invoiceRegionTo">Region to</label>
						<select class="browser-default" id="invoiceRegionTo" name="invoiceRegionTo"
						        required>
							<option hidden selected value=""></option>
							<c:forEach items="${requestScope.regions}" var="region">
								<option data-region-id="${region.id}">
										${region.regionTitle}
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="row">
						<label for="invoiceCityTo">City to</label>
						<input disabled list="citiesToList" id="invoiceCityTo" name="invoiceCityTo" autocomplete="off"
						       required>
						<datalist id="citiesToList">
							<c:forEach items="${requestScope.cities}" var="city">
								<option data-region-id="${city.regionId}" data-city-id="${city.id}">
										${city.cityTitle}
								</option>
							</c:forEach>
						</datalist>
					</div>

					<div class="row">
						<label for="invoiceAddressTo">Address to</label>
						<input disabled list="addressesToList" id="invoiceAddressTo" name="invoiceAddressTo"
						       autocomplete="off"
						       required>
						<datalist id="addressesToList">
							<c:forEach items="${requestScope.addresses}" var="address">
								<option data-city-id="${address.cityId}" data-address-id="${address.id}">
										${address.addressTitle}
								</option>
							</c:forEach>
						</datalist>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="card-panel col s2">
					<label for="estimate">Estimate</label>
					<input class="right-align" id="estimate" type="number" min="1" max="100000" name="estimate"
					       value="0"
					       step="any">
				</div>
			</div>

			<div class="containerCargo card-panel row">
				<button id="add_form_field" class="waves-effect waves-light btn">Add New Cargo</button>


				<div class="card-panel row">
					<div class="col l3 m8 s12">
						<label for="cargoType1">Cargo type</label>
						<select class="browser-default" onchange="selectCargoType(this,1)" name="cargoType1"
						        id="cargoType1">
							<option value="1">parcels and cargoes</option>
							<option value="2">documents</option>
						</select>
					</div>

					<div class="col l2 m4 s6">
						<label for="weight1">Weight</label>
						<input class="right-align" type="number" min="1" max="10000" name="weight1" id="weight1"
						       value="0" step="any"/>
					</div>

					<div class="col l2 m4 s6">
						<label id="length1Label" for="length1">Length</label>
						<input class="right-align" type="number" min="1" max="1000" name="length1" id="length1"
						       value="0" step="any"/>
					</div>

					<div class="col l2 m4 s6">
						<label id="width1Label" for="width1">Width</label>
						<input class="right-align" type="number" min="1" max="1000" name="width1" id="width1"
						       value="0" step="any"/>
					</div>

					<div class="col l2 m4 s6">
						<label id="height1Label" for="height1">Height</label>
						<input class="right-align" type="number" min="1" max="1000" name="height1" id="height1"
						       value="0" step="any"/>
					</div>
				</div>
			</div>

			<input class="waves-effect waves-light btn" type="submit" value="Calculate"/>
	</form>
</div>
</body>
</html>
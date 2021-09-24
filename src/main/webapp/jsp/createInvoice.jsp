<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Create Invoice</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/addCargo.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectOptions.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectCargo.js"></script>

	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- Compiled and minified CSS -->
	<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">--%>

	<!-- Compiled and minified JavaScript -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>

</head>
<body class="container">

<nav>
	<a href="${pageContext.request.contextPath}/index.jsp">Homepage</a>
	<a href="userPage">User page</a>
	<a href="logout">Logout</a>
</nav>
<hr/>

<form action="invoicePage" method="post">

	<br/>
	<br/>
	<br/>
	<div class="from">
		<label for="invoiceRegionFrom">Region from:</label>
		<select class="browser-default" <%--class="regionFrom"--%> id="invoiceRegionFrom" name="invoiceRegionFrom"
		        required>
			<option hidden selected value=""></option>
			<c:forEach items="${requestScope.regions}" var="region">
				<option data-region-id="${region.id}">
						${region.regionTitle}
				</option>
			</c:forEach>
		</select><br/><br/>

		<label for="invoiceCityFrom">City from:</label>
		<input disabled list="citiesFromList" id="invoiceCityFrom" name="invoiceCityFrom" autocomplete="off" required>
		<datalist id="citiesFromList">
			<c:forEach items="${requestScope.cities}" var="city">
				<option data-region-id="${city.regionId}" data-city-id="${city.id}">
						${city.cityTitle}
				</option>
			</c:forEach>
		</datalist>
		<br/><br/>

		<label for="invoiceAddressFrom">Address from:</label>
		<input disabled list="addressesFromList" id="invoiceAddressFrom" name="invoiceAddressFrom" autocomplete="off"
		       required>
		<datalist id="addressesFromList">
			<c:forEach items="${requestScope.addresses}" var="address">
				<option data-city-id="${address.cityId}" data-address-id="${address.id}">
						${address.addressTitle}
				</option>
			</c:forEach>
		</datalist>
		<br/><br/>
	</div>

	<hr/>
	<div class="to">
		<label for="invoiceRegionTo">Region to:</label>
		<select class="browser-default" id="invoiceRegionTo" name="invoiceRegionTo" required>
			<option hidden selected value=""></option>
			<c:forEach items="${requestScope.regions}" var="region">
				<option data-region-id="${region.id}">
						${region.regionTitle}
				</option>
			</c:forEach>
		</select><br/><br/>

		<label for="invoiceCityTo">City to:</label>
		<input disabled list="citiesToList" id="invoiceCityTo" name="invoiceCityTo" autocomplete="off" required>
		<datalist id="citiesToList">
			<c:forEach items="${requestScope.cities}" var="city">
				<option data-region-id="${city.regionId}" data-city-id="${city.id}">
						${city.cityTitle}
				</option>
			</c:forEach>
		</datalist>
		<br/><br/>

		<label for="invoiceAddressTo">Address to:</label>
		<input disabled list="addressesToList" id="invoiceAddressTo" name="invoiceAddressTo" autocomplete="off"
		       required>
		<datalist id="addressesToList">
			<c:forEach items="${requestScope.addresses}" var="address">
				<option data-city-id="${address.cityId}" data-address-id="${address.id}">
						${address.addressTitle}
				</option>
			</c:forEach>
		</datalist>
		<br/><br/>
	</div>

	<hr/>
	<label for="estimate">Estimate:</label>
	<input id="estimate" type="number" min="1" max="100000" name="estimate" value="0" step="any">

	<hr/>
	<div class="container1">
		<button class="add_form_field">Add New Cargo</button>
		<br/>
		<br/>
		<div>
			<label for="cargoType1">Cargo type: </label>
			<select class="browser-default" onchange="selectCargoType(this,1)" name="cargoType1" id="cargoType1">
				<option value="1">parcels and cargoes</option>
				<option value="2">documents</option>
			</select>

			<br/>
			<br/>
			<label for="weight1">Weight: </label>
			<input type="number" min="1" max="10000" name="weight1" id="weight1" value="0" step="any"/>

			<label id="length1Label" for="length1">Length: </label>
			<input type="number" min="1" max="1000" name="length1" id="length1" value="0" step="any"/>

			<label id="width1Label" for="width1">Width: </label>
			<input type="number" min="1" max="1000" name="width1" id="width1" value="0" step="any"/>

			<label id="height1Label" for="height1">Height: </label>
			<input type="number" min="1" max="1000" name="height1" id="height1" value="0" step="any"/>
			<br/>
			<br/>
			<br/>
		</div>
	</div>

	<br/><br/>
	<input type="submit" value="Create invoice"/>
</form>
</body>
</html>
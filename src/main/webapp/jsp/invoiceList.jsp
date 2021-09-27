<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>List of invoices</title>

	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<!-- Compiled and minified JavaScript -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>

</head>
<body class = "container">

<nav>
	<a href="${pageContext.request.contextPath}/index.jsp">Homepage</a>
	<a href="${pageContext.request.contextPath}/jsp/userPage.jsp">User page</a>
	<a href="${pageContext.request.contextPath}/jsp/createInvoice.jsp">Create invoice</a>
	<a href="${pageContext.request.contextPath}/receiptListPage">View receipts</a>
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<hr/>

<br/>
<br/>
<br/>
<div>
	<c:forEach items="${requestScope.invoices}" var="invoice">
		<div>
			<label for="cityFrom${invoice.id}">From city: </label>
			<div id="cityFrom${invoice.id}">
				<c:forEach items="${requestScope.addresses}" var="address">
					<c:if test="${address.id eq invoice.addressFromId}">
						<c:forEach items="${requestScope.cities}" var="city">
							<c:if test="${city.id eq address.cityId}">
								${city.cityTitle}
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</div>
			<br/>
			<label for="cityTo${invoice.id}">To city: </label>
			<div id="cityTo${invoice.id}">
				<c:forEach items="${requestScope.addresses}" var="address">
					<c:if test="${address.id eq invoice.addressToId}">
						<c:forEach items="${requestScope.cities}" var="city">
							<c:if test="${city.id eq address.cityId}">
								${city.cityTitle}
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</div>
			<br/>
			<label for="status${invoice.id}">Status: </label>
			<div id="status${invoice.id}">
				<c:forEach items="${requestScope.invoiceStatuses}" var="status">
					<c:if test="${status.id eq invoice.invoiceStatusId}">
						${status.name.toString()}
					</c:if>
				</c:forEach>
			</div>
			<br/>
			<label for="numberC${invoice.id}">Number of cargos: </label>
			<div id="numberC${invoice.id}">${invoice.numberOfCargo}</div>
		</div>

		<c:if test="${sessionScope.user.roleId eq 2}">
			<c:if test="${invoice.invoiceStatusId eq 1}">
				<form action="receiptCreate" method="post">
					<input type="hidden" value="${invoice.id}" name="invoiceId"/>
					<input type="submit" value="Create a receipt"/>
				</form>
			</c:if>
		</c:if>
		<hr/>
		<br/><br/>
	</c:forEach>
</div>
</body>
</html>
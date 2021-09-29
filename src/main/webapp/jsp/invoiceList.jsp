<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>List of invoices</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navLogged.jsp" %>

<div class="container">
	<br/>
	<br/>
	<br/>
	<div>
		<c:forEach items="${requestScope.invoices}" var="invoice">
			<div class="row card-panel">
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

			<c:if test="${sessionScope.user.roleId eq 2}">
				<c:if test="${invoice.invoiceStatusId eq 1}">
					<form action="receiptCreate" method="post">
						<input type="hidden" value="${invoice.id}" name="invoiceId"/>
						<input type="submit" value="Create a receipt"/>
					</form>
				</c:if>
			</c:if>
	</div>
			<hr/>
			<br/><br/>
		</c:forEach>
	</div>
</div>
</body>
</html>
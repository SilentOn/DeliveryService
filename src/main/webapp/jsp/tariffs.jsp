<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Tariffs</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navNotLogged.jsp" %>

<div class="container">

	<div class="row card-panel">
		<table class="striped highlight">
			<thead>
			<tr>
				<th>
					Weight up to (inclusive)
				</th>
				<%--<th>
					City from
				</th>
				<th>
					City to
				</th>
				<th>
					Create time
				</th>
				<th>
					Estimate
				</th>
				<th>
					Delivery term
				</th>
				<th>
					Total weight
				</th>
				<th>
					Total volume
				</th>
				<th>
					Number of cargo
				</th>
				<th>
					Status
				</th>--%>
				<c:forEach items="${requestScope.tariffZones}" var="tariffZone">
					<th>
							${tariffZone.tariffZoneTitle}
					</th>
				</c:forEach>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.weightTariffs}" var="weight">
				<tr>
					<td>${weight.weight}</td>

					<c:forEach items="${requestScope.tariffZones}" var="tariffZone">
						<c:forEach items="${requestScope.tariffZoneHasWeightTariffs}" var="zoneWeight">
							<c:if test="${zoneWeight.tariffZoneId eq tariffZone.id && zoneWeight.weightTariffId eq weight.id}">
								<td>
										${zoneWeight.price}
								</td>
							</c:if>
						</c:forEach>
					</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<%--<td>
		<c:forEach items="${requestScope.addresses}" var="address">
			<c:if test="${address.id eq invoice.addressFromId}">
				<c:forEach items="${requestScope.cities}" var="city">
					<c:if test="${city.id eq address.cityId}">
						${city.cityTitle}
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${requestScope.addresses}" var="address">
			<c:if test="${address.id eq invoice.addressToId}">
				<c:forEach items="${requestScope.cities}" var="city">
					<c:if test="${city.id eq address.cityId}">
						${city.cityTitle}
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
	</td>
	<td>${invoice.createTime}</td>
	<td>${invoice.estimate}</td>
	<td>${invoice.deliveryTerm}</td>
	<td>${invoice.totalWeight}</td>
	<td>${invoice.totalVolume}</td>
	<td>${invoice.numberOfCargo}</td>
	<td>
		<c:forEach items="${requestScope.invoiceStatuses}" var="status">
			<c:if test="${status.id eq invoice.invoiceStatusId}">
				${status.name.toString()}
				<c:if test="${sessionScope.isManager && status.name.toString() eq 'new'}">
					<input formaction="${pageContext.request.contextPath}/receiptCreate?invoiceId=${invoice.id}"
						   formmethod="post"
						   type="submit"
						   class="waves-effect waves-light btn"
						   value="Create a receipt"/>
				</c:if>
			</c:if>
		</c:forEach>
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>--%>
	${requestScope.remove("tariffZones")}
	${requestScope.remove("weightTariffs")}
	${requestScope.remove("tariffZoneHasWeightTariffs")}
</div>
</body>
</html>
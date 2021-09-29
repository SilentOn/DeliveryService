<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Delivery directions</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navNotLogged.jsp" %>

<div class="container">
	<br/>
	<br/>
	<label>Sort by:</label>
	<%--<form action="deliveryDirectionsList">
		<input type="hidden" name="region" value="asc">
		<input type="hidden" name="action" value="sort">
		<input type="submit" value="region asc">
	</form>
	<form action="deliveryDirectionsList">
		<input type="hidden" name="region" value="desc">
		<input type="hidden" name="action" value="sort">
		<input type="submit" value="region desc">
	</form>--%>
	<form action="${pageContext.request.contextPath}/deliveryDirectionsList">
		<input type="hidden" name="city" value="asc">
		<input type="hidden" name="action" value="sort">
		<input type="submit" value="city asc">
	</form>
	<form action="${pageContext.request.contextPath}/deliveryDirectionsList">
		<input type="hidden" name="city" value="desc">
		<input type="hidden" name="action" value="sort">
		<input type="submit" value="city desc">
	</form>

	<br/>
	<form action="${pageContext.request.contextPath}/deliveryDirectionsList">
		<label for="filter">Filter by region:</label>
		<select class="browser-default" id="filter" name="filter">
			<option>all</option>

			<c:forEach items="${requestScope.regions}" var="region">
				<option>
						${region.regionTitle}
				</option>
			</c:forEach>
		</select>
		<input type="hidden" name="action" value="filter">
		<input type="submit" value="Filter">
	</form>
	<br/>
	<table border="1">
		<tr>
			<th>
				Region
			</th>
			<th>
				City
			</th>
		</tr>
		<c:forEach items="${requestScope.cities}" var="city">
			<tr>
				<td>
					<c:forEach items="${requestScope.regions}" var="region">
						<c:if test="${region.id eq city.regionId}">
							${region.regionTitle}
						</c:if>
					</c:forEach>
				</td>
				<td>
						${city.cityTitle}
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
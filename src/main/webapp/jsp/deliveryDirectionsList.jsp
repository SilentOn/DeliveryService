<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Delivery directions</title>

	<%@include file="/jsp/connectHeader.jsp" %>
	<script src="${pageContext.request.contextPath}/js/pagination.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectSortFilterOnPage.js"></script>
</head>
<body>
<%@include file="/jsp/navNotLogged.jsp" %>

<div class="container">
	<form action="${pageContext.request.contextPath}/deliveryDirectionsList" method="get">
		<div class="row card-panel sort">
			<div class="row sort params"
			     data-sort="${paramValues.sort[0]}"
			     data-filter="${paramValues.filter[0]}"
			     data-items-on-page="${paramValues.itemsOnPage[0]}">
				<div class="col l8">
					<p class="sort"><label>Sort by</label></p>
					<label class="sort">
						<input class="with-gap" type="radio" value="none" name="sort" checked>
						<span>none</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="region asc" name="sort">
						<span>region asc</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="region desc" name="sort">
						<span>region desc</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="city asc" name="sort">
						<span>city asc</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="city desc" name="sort">
						<span>city desc</span>
					</label>
				</div>
				<div class="col l2">
					<label for="filter" class="sort">Filter by region</label>
					<select class="browser-default" id="filter" name="filter">
						<option>all</option>

						<c:forEach items="${requestScope.regions}" var="region">
							<option value="${region.regionTitle}">
									${region.regionTitle}
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="col l2">
					<label for="itemsOnPage" class="sort">Items on page</label>
					<select class="browser-default" id="itemsOnPage" name="itemsOnPage">
						<option value="2">2</option>
						<option selected value="5">5</option>
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="100">100</option>
						<option value="all">all</option>
					</select>
				</div>
			</div>
			<div class="row sort"><input class="waves-effect waves-light btn" type="submit" value="Apply"></div>
		</div>

		<div class="row card-panel">
			<table class="striped highlight centered cities">
				<thead>
				<tr>
					<th>
						Region
					</th>
					<th>
						City
					</th>
				</tr>
				</thead>
				<tbody>
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
				</tbody>
			</table>
		</div>

		<div class="row">
			<div class="col l12 m12 s12 card-panel center-align">
				<ul class="pagination" data-pages-count="${requestScope.pagesCount}"
				    data-page="${requestScope.page}"></ul>
			</div>
		</div>
	</form>
	${requestScope.remove("cities")}
	${requestScope.remove("regions")}
	${requestScope.remove("pagesCount")}
	${requestScope.remove("page")}
</div>
</body>
</html>
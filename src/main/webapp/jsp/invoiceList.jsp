<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>List of invoices</title>

	<%@include file="/jsp/connectHeader.jsp" %>
	<script src="${pageContext.request.contextPath}/js/pagination.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectSortFilterOnPage.js"></script>
</head>
<body>
<%@include file="/jsp/navLogged.jsp" %>

<div class="container">
	<form action="${pageContext.request.contextPath}/invoiceListPage" method="get">
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
						<input class="with-gap" type="radio" value="ID asc" name="sort">
						<span>ID asc</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="ID desc" name="sort">
						<span>ID desc</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="estimate asc" name="sort">
						<span>estimate asc</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="estimate desc" name="sort">
						<span>estimate desc</span>
					</label>
				</div>
				<div class="col l2">
					<label for="filter" class="sort">Filter by status</label>
					<select class="browser-default" id="filter" name="filter">
						<option>all</option>

						<c:forEach items="${requestScope.invoiceStatuses}" var="status">
							<option value="${status.name.toString()}">
									${status.name.toString()}
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
			<table class="striped highlight">
				<thead>
				<tr>
					<th>
						ID
					</th>
					<th>
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
					</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${requestScope.invoices}" var="invoice">
					<tr>
						<td>${invoice.id}</td>
						<td>
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
		</div>

		<div class="row">
			<div class="col l12 m12 s12 card-panel center-align">
				<ul class="pagination" data-pages-count="${requestScope.pagesCount}"
				    data-page="${requestScope.page}"></ul>
			</div>
		</div>
	</form>
	${requestScope.remove("invoices")}
	${requestScope.remove("cities")}
	${requestScope.remove("invoiceStatuses")}
	${requestScope.remove("addresses")}
	${requestScope.remove("pagesCount")}
	${requestScope.remove("page")}
</div>
</body>
</html>
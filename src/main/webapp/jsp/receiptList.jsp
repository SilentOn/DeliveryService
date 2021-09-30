<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>List of receipts</title>

	<%@include file="/jsp/connectHeader.jsp" %>
	<script src="${pageContext.request.contextPath}/js/pagination.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectSortFilterOnPage.js"></script>
</head>
<body>
<%@include file="/jsp/navLogged.jsp" %>

<div class="container"><br/>
	<form action="${pageContext.request.contextPath}/receiptListPage" method="get">
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
						<input class="with-gap" type="radio" value="to pay asc" name="sort">
						<span>to pay asc</span>
					</label>
					<label class="sort">
						<input class="with-gap" type="radio" value="to pay desc" name="sort">
						<span>to pay desc</span>
					</label>
				</div>
				<div class="col l2">
					<label for="filter" class="sort">Filter by status</label>
					<select class="browser-default" id="filter" name="filter">
						<option>all</option>

						<c:forEach items="${requestScope.receiptStatuses}" var="status">
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
						To pay
					</th>
					<th>
						Status
					</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${requestScope.receipts}" var="receipt">
					<tr>
						<td>${receipt.id}</td>
						<td>${receipt.toPay}</td>
						<td>
							<c:forEach items="${requestScope.receiptStatuses}" var="status">
								<c:if test="${status.id eq receipt.receiptStatusId}">
									${status.name.toString()}
									<c:if test="${!sessionScope.isManager && status.name.toString() eq 'not paid'}">
										<input formaction="${pageContext.request.contextPath}/payReceipt?receiptId=${receipt.id}"
										       formmethod="post"
										       type="submit"
										       class="waves-effect waves-light btn"
										       value="Pay a receipt"/>
									</c:if>
								</c:if>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

	<%--<br/>
	<br/>
	<div>
		<c:forEach items="${requestScope.receipts}" var="receipt">
			<div class="row card-panel">
				<label for="invoiceId${receipt.id}">Invoice id: </label>
				<div id="invoiceId${receipt.id}">
						${receipt.id}
				</div>
				<br/>
				<label for="toPay${receipt.id}">To pay: </label>
				<div id="toPay${receipt.id}">
						${receipt.toPay}
				</div>
				<br/>
				<label for="status${receipt.id}">Status: </label>
				<div id="status${receipt.id}">
					<c:forEach items="${requestScope.receiptStatuses}" var="status">
						<c:if test="${status.id eq receipt.receiptStatusId}">
							${status.name.toString()}
						</c:if>
					</c:forEach>
				</div>

				<c:if test="${sessionScope.user.roleId eq 1}">
					<c:if test="${receipt.receiptStatusId eq 2}">
						<form action="${pageContext.request.contextPath}/payReceipt" method="post">
							<input type="hidden" value="${receipt.id}" name="receiptId"/>
							<input type="submit" value="Pay a receipt"/>
						</form>
					</c:if>
				</c:if>
			</div>
			<hr/>
			<br/><br/>
		</c:forEach>
	</div>--%>





	<div class="row">
		<div class="col l12 m12 s12 card-panel center-align">
			<ul class="pagination" data-pages-count="${requestScope.pagesCount}"
			    data-page="${requestScope.page}"></ul>
		</div>
	</div>
	</form>
	${requestScope.remove("receipts")}
	${requestScope.remove("receiptStatuses")}
	${requestScope.remove("pagesCount")}
	${requestScope.remove("page")}
</div>
</body>
</html>
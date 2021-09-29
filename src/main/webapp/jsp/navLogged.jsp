<nav>
	<div class="nav-wrapper">
		<div class="container">
			<a href="${pageContext.request.contextPath}/index.jsp" class="brand-logo">Delivery service</a>
			<ul class="right hide-on-med-and-down">
				<li><a href="${pageContext.request.contextPath}/jsp/userPage.jsp">User page</a></li>
				<li><a href="${pageContext.request.contextPath}/invoiceListPage">View invoices</a></li>
				<c:choose>
					<c:when test="${sessionScope.isManager}">
						<li><a href="${pageContext.request.contextPath}/receiptListPage">View receipts</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/jsp/createInvoice.jsp">Create invoice</a></li>
						<li><a href="${pageContext.request.contextPath}/receiptListPage">View my receipts</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>
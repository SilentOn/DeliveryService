<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>List of receipts</title>

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
	<a href="logout">Logout</a>
	<%--<hr/>--%>
	<a href="userPage">User page</a>
	<a href="invoiceListPage">View invoices</a>
	<c:if test="${sessionScope.user.roleId eq 1}">
		<a href="invoicePage">Create invoice</a>
	</c:if>
</nav>
<hr/>

<br/>
<br/>
<br/>
<div>
	<c:forEach items="${requestScope.receipts}" var="receipt">
		<div>
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
		</div>

		<c:if test="${sessionScope.user.roleId eq 1}">
			<c:if test="${receipt.receiptStatusId eq 2}">
				<form action="payReceipt" method="post">
					<input type="hidden" value="${receipt.id}" name="receiptId"/>
					<input type="submit" value="Pay a receipt"/>
				</form>
			</c:if>
		</c:if>
		<hr/>
		<br/><br/>
	</c:forEach>
</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<title>User page</title>

	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<!-- Compiled and minified JavaScript -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>

</head>
<body class="container">

<nav>
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="${pageContext.request.contextPath}/index.jsp">Homepage</a></li>
			<li><a href="${pageContext.request.contextPath}/invoiceListPage">View invoices</a></li>
			<c:choose>
				<c:when test="${requestScope.role.name.toString() eq 'user'}">
					<li><a href="${pageContext.request.contextPath}/jsp/createInvoice.jsp">Create invoice</a></li>
					<li><a href="${pageContext.request.contextPath}/receiptListPage">View my receipts</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/receiptListPage">View receipts</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</ul>
	</div>
</nav>

<div>
	<hr/>
	<br/>
	<br/>
	<br/>
	Number: ${sessionScope.user.phoneNumber}<br/><br/>
	Role: ${requestScope.role.name.toString()}<br/><br/>
	Email: ${requestScope.userDetails.email}<br/><br/>
	First Name: ${requestScope.userDetails.firstName}<br/><br/>
	Last Name: ${requestScope.userDetails.lastName}<br/><br/>
	<nav>
		<a href="${pageContext.request.contextPath}/jsp/editUser.jsp">Edit account details</a>
	</nav>
	<form action="${pageContext.request.contextPath}/deleteUser" method="post">
		<input type="submit" value="Delete account"/>
	</form>
</div>
</body>
</html>
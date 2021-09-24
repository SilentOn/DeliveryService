<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>User page</title>

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
	<a href="invoiceListPage">View invoices</a>
	<c:choose>
		<c:when test="${role.name eq 'user'}">
			<a href="invoicePage">Create invoice</a>
			<a href="receiptListPage">View my receipts</a>
		</c:when>
		<c:otherwise>
			<a href="receiptListPage">View receipts</a>
		</c:otherwise>
	</c:choose>
</nav>
<div>
	<hr/>
	<br/>
	<br/>
	<br/>
	Number: ${sessionScope.user.phoneNumber}<br/><br/>
	Role: ${role.name}<br/><br/>
	Email: ${userDetails.email}<br/><br/>
	First Name: ${userDetails.firstName}<br/><br/>
	Last Name: ${userDetails.lastName}<br/><br/>

	<nav>
		<a href="userPage?edit=true">Edit account details</a>
		<a href="deleteUser">Delete account</a>
	</nav>
</div>
</body>
</html>
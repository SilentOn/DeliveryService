<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.delivery.entity.Role.RoleName"%>

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
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
	<%--<hr/>--%>
	<a href="${pageContext.request.contextPath}/invoiceListPage">View invoices</a>
	<c:choose>
		<c:when test="${role.name.toString() eq 'user'}">
			<a href="${pageContext.request.contextPath}/invoicePage">Create invoice</a>
			<a href="${pageContext.request.contextPath}/receiptListPage">View my receipts</a>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/receiptListPage">View receipts</a>
		</c:otherwise>
	</c:choose>
</nav>
<div>
	<hr/>
	<br/>
	<br/>
	<br/>
	Number: ${sessionScope.user.phoneNumber}<br/><br/>
	Role: ${role.name.toString()}<br/><br/>
	Email: ${userDetails.email}<br/><br/>
	First Name: ${userDetails.firstName}<br/><br/>
	Last Name: ${userDetails.lastName}<br/><br/>
	<c:out value="${RoleName.USER.toString()}">${RoleName.USER.toString()}</c:out>
	<nav>
		<a href="editUser.jsp">Edit account details</a>
		<a href="deleteUser">Delete account</a>
	</nav>
</div>
</body>
</html>
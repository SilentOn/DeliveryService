<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>Delivery service</title>


	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<!-- Compiled and minified JavaScript -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>

</head>
<body class = "container">
<nav>
	<c:choose>
		<c:when test="${not empty sessionScope.user}">
			<a href="userPage">User page</a>
			<a href="logout">Logout</a>
		</c:when>
		<c:otherwise>
			<a href="registerpage">Registration</a>
			<a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a>
			<%--<hr/>--%>
			<a href="deliveryDirectionsList">Information about delivery directions</a>
			<a href="">Tariffs</a>
			<a href="calculateCost">Calculate shipping cost</a>
		</c:otherwise>
	</c:choose>
</nav>
<hr/>
<h1>
	<c:choose>
		<c:when test="${not empty sessionScope.user}">
			<%= "Hello, " %>
			${sessionScope.user.phoneNumber}
		</c:when>
		<c:otherwise>
			<%= "Delivery service" %>
		</c:otherwise>
	</c:choose>

</h1>
</body>
</html>
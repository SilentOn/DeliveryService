<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
<body class="container">
<nav>
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<li><a href="${pageContext.request.contextPath}/jsp/userPage.jsp">User page</a></li>
					<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/jsp/register.jsp">Registration</a></li>
					<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a></li>
					<li><a href="${pageContext.request.contextPath}/deliveryDirectionsList">
						Information about delivery directions</a></li>
					<li><a href="">Tariffs</a></li>
					<li><a href="${pageContext.request.contextPath}/jsp/calculateCost.jsp">Calculate shipping cost</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>
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
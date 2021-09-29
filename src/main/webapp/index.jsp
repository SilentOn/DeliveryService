<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Delivery service</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navNotLogged.jsp" %>

<div class="container">
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
</div>
</body>
</html>
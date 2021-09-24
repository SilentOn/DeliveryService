<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login</title>

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
</nav>
<hr/>

<form action="${pageContext.request.contextPath}/login" method="post">

	<%--<c:if test="${not empty messageLogin}">
		<h5>${messageLogin}</h5>
		<c:set var="messageLogin" value="" scope="application"/>
	</c:if>--%>
	<%--<c:choose>
		<c:when test="${not empty messageLogin}">
			<h5>${messageLogin}</h5>
			${messageLogin=""}
		</c:when>
		<c:otherwise>
			<h5/>
		</c:otherwise>
	</c:choose>--%>
	${messageLogin}
	${messageLogin=""}

	<br/>
	<br/>
	<br/>
	Number:<input type="text" name="userNumber" <%--value="+380 (95) 079-54-02"--%>/><br/><br/>
	Password:<input type="password" name="userPass" <%--value="userPass"--%>/><br/><br/>

	<br/><br/>
	<input type="submit" value="Login"/>
</form>
</body>
</html>
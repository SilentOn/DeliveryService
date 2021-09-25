<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>

	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<!-- Compiled and minified JavaScript -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>

</head>
<body class="container">

<nav>
	<a href="${pageContext.request.contextPath}/index.jsp">Homepage</a>
</nav>
<hr/>

<form action="${pageContext.request.contextPath}/login" method="post">

	<h6>${messageLogin}</h6>
	${messageLogin=null}

	<br/>
	<br/>
	<br/>
	<label for="userNumber">Number: </label>
	<input type="text" id="userNumber" name="userNumber"/><br/><br/>
	<label for="userPass">Password: </label>
	<input type="password" id="userPass" name="userPass"/><br/><br/>

	<br/><br/>
	<input type="submit" value="Login"/>
</form>
</body>
</html>
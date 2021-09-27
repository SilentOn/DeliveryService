<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Register</title>

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
			<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a></li>
		</ul>
	</div>
</nav>

<form action="${pageContext.request.contextPath}/register" method="post">

	<h6>${sessionScope.messageRegister}</h6>
	<br/>
	<br/>
	<br/>
	<label for="userNumber">Number: </label>
	<input type="text" id="userNumber" name="userNumber" value="${sessionScope.userNumber}"/>
	<br/><br/>

	<label for="userPass">Password: </label>
	<input type="password" id="userPass" name="userPass" value="${sessionScope.userPass}"/>
	<br/><br/>

	<label for="userPassConf">Confirm password: </label>
	<input type="password" id="userPassConf" name="userPassConf"/>
	<br/><br/>
	<br/><br/>

	<label for="userEmail">Email: </label>
	<input type="text" id="userEmail" name="userEmail" value="${sessionScope.userEmail}"/>
	<br/><br/>

	<label for="userFirstName">First Name: </label>
	<input type="text" id="userFirstName" name="userFirstName" value="${sessionScope.userFirstName}"/>
	<br/><br/>

	<label for="userLastName">Last Name: </label>
	<input type="text" id="userLastName" name="userLastName" value="${sessionScope.userLastName}"/>
	<br/><br/>

	${sessionScope.remove("messageRegister")}
	${sessionScope.remove("userNumber")}
	${sessionScope.remove("userPass")}
	${sessionScope.remove("userEmail")}
	${sessionScope.remove("userFirstName")}
	${sessionScope.remove("userLastName")}

	<br/><br/>
	<input type="submit" value="register"/>
	<br/><br/>
</form>
</body>
</html>
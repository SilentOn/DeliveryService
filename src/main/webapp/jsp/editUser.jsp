<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Edit user</title>

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
	<a href="${pageContext.request.contextPath}/jsp/userPage.jsp">User page</a>
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<hr/>

<form action="editUser" method="post">

	${messageEditUser}
	${messageEditUser=""}
	<br/>
	<br/>
	<br/>
	Number: ${sessionScope.user.phoneNumber}<br/><br/>
	Role: ${role.name.toString()}<br/><br/>
	Password:<input type="password" name="userPass" value="${sessionScope.user.password}"/><br/><br/>
	Confirm password:<input type="password" name="userPassConf"/><br/><br/>
	<br/><br/>

	Email:<input type="text" name="userEmail" value="${userDetails.email}"/><br/><br/>
	First Name:<input type="text" name="userFirstName" value="${userDetails.firstName}"/><br/><br/>
	Last Name:<input type="text" name="userLastName" value="${userDetails.lastName}"/><br/><br/>

	<br/><br/>
	<input type="submit" value="Edit"/>
</form>
</body>
</html>
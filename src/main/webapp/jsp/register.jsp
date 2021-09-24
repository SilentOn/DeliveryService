<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>

	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<!-- Compiled and minified JavaScript -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>


	<script>
        $(document).ready(function () {
            $('select').material_select();
        });
	</script>

</head>
<body class="container">

<nav>
	<a href="${pageContext.request.contextPath}/index.jsp">Homepage</a>
	<a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a>
</nav>
<hr/>

<form action="register" method="post">

	${messageRegister}
	${messageRegister=""}
	<br/>
	<br/>
	<br/>
	Number:<input type="text" name="userNumber" value="${userNumber}"<%--"+380 (95) 079-54-02"--%>/><br/><br/>
	Password:<input type="password" name="userPass" value="${userPass}"<%--"userPass"--%>/><br/><br/>
	Confirm password:<input type="password" name="userPassConf" <%--value="userPass"--%>/><br/><br/>
	<%--Role:
		<select class = "browser-default" name="userRole">
			<c:forEach items="${requestScope.roles}" var="role">
				<option ${role.name == userRole ? 'selected' : ''}>
						${role.name}
				</option>
			</c:forEach>
		</select>--%>
	<input type="hidden" name="userRole" value="user">
	<br/><br/>

	Email:<input type="text" name="userEmail" value="${userEmail}"/><br/><br/>
	First Name:<input type="text" name="userFirstName" value="${userFirstName}"/><br/><br/>
	Last Name:<input type="text" name="userLastName" value="${userLastName}"/><br/><br/>

	${userNumber=""}
	${userPass=""}
	${userRole=""}
	${userEmail=""}
	${userFirstName=""}
	${userLastName=""}

	<br/><br/>
	<input type="submit" value="register"/>
</form>
</body>
</html>
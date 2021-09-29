<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navNotLogged.jsp" %>

<div class="container">
	<div class="row">
		<div class="card-panel col l4 offset-l4">
			<form action="${pageContext.request.contextPath}/login" method="post">
				<h6>${sessionScope.messageLogin}</h6>
				${sessionScope.remove("messageLogin")}

				<div class="input-field">
					<i class="material-icons prefix">phone</i>
					<input id="userNumber" type="tel" name="userNumber" class="validate" required>
					<label for="userNumber">Number</label>
				</div>

				<div class="input-field">
					<i class="material-icons prefix">lock</i>
					<input type="password" id="userPass" name="userPass" class="validate" required>
					<label for="userPass">Password</label>
				</div>

				<input class="waves-effect waves-light btn" type="submit" value="Login"/>
			</form>
		</div>
	</div>
</div>
</body>
</html>
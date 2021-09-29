<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Registration</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navNotLogged.jsp" %>

<div class="container">
	<form action="${pageContext.request.contextPath}/register" method="post">
		<div class="row card-panel">
			<h6>${sessionScope.messageRegister}</h6>

			<div class="input-field">
				<i class="material-icons prefix">phone</i>
				<input type="tel" id="userNumber" name="userNumber" required class="validate" value="${sessionScope.userNumber}">
				<label for="userNumber">Number</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">lock</i>
				<input type="password" id="userPass" name="userPass" required class="validate" value="${sessionScope.userPass}">
				<label for="userPass">Password</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">lock</i>
				<input type="password" id="userPassConf" name="userPassConf" required class="validate">
				<label for="userPassConf">Confirm password</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">email</i>
				<input type="text" id="userEmail" name="userEmail" required class="validate" value="${sessionScope.userEmail}">
				<label for="userEmail">Email</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">account_circle</i>
				<input type="text" id="userFirstName" name="userFirstName" required class="validate" value="${sessionScope.userFirstName}">
				<label for="userFirstName">First name</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">account_box</i>
				<input type="text" id="userLastName" name="userLastName" required class="validate" value="${sessionScope.userLastName}">
				<label for="userLastName">Last name</label>
			</div>

			${sessionScope.remove("messageRegister")}
			${sessionScope.remove("userNumber")}
			${sessionScope.remove("userPass")}
			${sessionScope.remove("userEmail")}
			${sessionScope.remove("userFirstName")}
			${sessionScope.remove("userLastName")}

			<input class="waves-effect waves-light btn" type="submit" value="Register"/>
	</form>
</div>
</body>
</html>
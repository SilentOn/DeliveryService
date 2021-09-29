<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Edit user</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navLogged.jsp" %>

<div class="container">
	<form action="${pageContext.request.contextPath}/editUser" method="post">
		<div class="row card-panel">
			<h6>${sessionScope.messageEditUser}</h6>
			${sessionScope.remove("messageEditUser")}
			<div class="input-field">
				<i class="material-icons prefix">phone</i>
				<input id="userPhoneNumber" type="tel" readonly disabled value="${sessionScope.user.phoneNumber}">
				<label for="userPhoneNumber">Number</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">assignment_ind</i>
				<input id="userRole" type="text" readonly disabled value="${requestScope.role.name.toString()}">
				<label for="userRole">Role</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">lock</i>
				<input type="password" id="userPass" name="userPass" class="validate" value="${sessionScope.user.password}">
				<label for="userPass">Password</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">lock</i>
				<input type="password" id="userPassConf" name="userPassConf" class="validate">
				<label for="userPassConf">Confirm password</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">email</i>
				<input type="text" id="userEmail" name="userEmail" class="validate" value="${requestScope.userDetails.email}">
				<label for="userEmail">Email</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">account_circle</i>
				<input type="text" id="userFirstName" name="userFirstName" class="validate" value="${requestScope.userDetails.firstName}">
				<label for="userFirstName">First name</label>
			</div>

			<div class="input-field">
				<i class="material-icons prefix">account_box</i>
				<input type="text" id="userLastName" name="userLastName" class="validate" value="${requestScope.userDetails.lastName}">
				<label for="userLastName">Last name</label>
			</div>
			<input class="waves-effect waves-light btn" type="submit" value="Edit"/>
		</div>
	</form>
</div>
</body>
</html>
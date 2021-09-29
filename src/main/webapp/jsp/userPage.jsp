<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>User page</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navLogged.jsp" %>

<div class="container">
	<div class="row card-panel">
		<ul>
			<li>
				<div class="row">
					<i class="material-icons tiny">phone</i>
					<label>Number</label>
					<p>${sessionScope.user.phoneNumber}</p>
				</div>
			</li>

			<li>
				<div class="row">
					<i class="material-icons tiny">assignment_ind</i>
					<label>Role</label>
					<p>${requestScope.role.name.toString()}</p>
				</div>
			</li>

			<li>
				<div class="row">
					<i class="material-icons tiny">email</i>
					<label>Email</label>
					<p>${requestScope.userDetails.email}</p>
				</div>
			</li>

			<li>
				<div class="row">
					<i class="material-icons tiny">account_circle</i>
					<label>First name</label>
					<p>${requestScope.userDetails.firstName}</p>
				</div>
			</li>

			<li>
				<div class="row">
					<i class="material-icons tiny">account_box</i>
					<label>Last name</label>
					<p>${requestScope.userDetails.lastName}</p>
				</div>
			</li>
		</ul>
		<%--<hr>
		<div class="input-field">
			<i class="material-icons prefix">phone</i>
			<input id="userPhoneNumber" type="tel" readonly value="${sessionScope.user.phoneNumber}">
			<label for="userPhoneNumber">Number</label>
		</div>

		<div class="input-field">
			<i class="material-icons prefix">assignment_ind</i>
			<input id="userRole" type="text" readonly value="${requestScope.role.name.toString()}">
			<label for="userRole">Role</label>
		</div>

		<div class="input-field">
			<i class="material-icons prefix">email</i>
			<input id="userEmail" type="text" readonly value="${requestScope.userDetails.email}">
			<label for="userEmail">Email</label>
		</div>

		<div class="input-field">
			<i class="material-icons prefix">account_circle</i>
			<input id="userFirstName" type="text" readonly value="${requestScope.userDetails.firstName}">
			<label for="userFirstName">First name</label>
		</div>

		<div class="input-field">
			<i class="material-icons prefix">account_box</i>
			<input id="userLastName" type="text" readonly value="${requestScope.userDetails.lastName}">
			<label for="userLastName">Last name</label>
		</div>--%>

		<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}/jsp/editUser.jsp">
			Edit account details
		</a>
		<br>
		<br>
		<form action="${pageContext.request.contextPath}/deleteUser" method="post">
			<input onclick="return confirm('Are you sure you want to delete yourself?')"
			       class="waves-effect waves-light btn"
			       type="submit"
			       value="Delete account"/>
		</form>
	</div>
</div>
</body>
</html>
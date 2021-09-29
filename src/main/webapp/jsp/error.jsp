<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Error page</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<div class="container">
	<h1>Error!</h1>
	<p>${sessionScope.errorMessage}</p>
	${sessionScope.remove("errorMessage")}
</div>
</body>
</html>

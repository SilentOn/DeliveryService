<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Error page</title>

	<!--Import Google Icon Font-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<!-- Compiled and minified JavaScript -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>

</head>
<body class = "container">
	<h1>Error!</h1>
	${errorMessage}
	${errorMessage=""}
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>List of receipts</title>

	<%@include file="/jsp/connectHeader.jsp" %>
</head>
<body>
<%@include file="/jsp/navLogged.jsp" %>

<div class="container"><br/>
	<br/>
	<br/>
	<div>
		<c:forEach items="${requestScope.receipts}" var="receipt">
			<div class="row card-panel">
				<label for="invoiceId${receipt.id}">Invoice id: </label>
				<div id="invoiceId${receipt.id}">
						${receipt.id}
				</div>
				<br/>
				<label for="toPay${receipt.id}">To pay: </label>
				<div id="toPay${receipt.id}">
						${receipt.toPay}
				</div>
				<br/>
				<label for="status${receipt.id}">Status: </label>
				<div id="status${receipt.id}">
					<c:forEach items="${requestScope.receiptStatuses}" var="status">
						<c:if test="${status.id eq receipt.receiptStatusId}">
							${status.name.toString()}
						</c:if>
					</c:forEach>
				</div>

				<c:if test="${sessionScope.user.roleId eq 1}">
					<c:if test="${receipt.receiptStatusId eq 2}">
						<form action="${pageContext.request.contextPath}/payReceipt" method="post">
							<input type="hidden" value="${receipt.id}" name="receiptId"/>
							<input type="submit" value="Pay a receipt"/>
						</form>
					</c:if>
				</c:if>
			</div>
			<hr/>
			<br/><br/>
		</c:forEach>
	</div>
</div>
</body>
</html>
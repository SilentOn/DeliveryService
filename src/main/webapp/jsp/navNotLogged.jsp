<nav>
	<div class="nav-wrapper">
		<div class="container">
			<a href="${pageContext.request.contextPath}/index.jsp" class="brand-logo">Delivery service</a>
			<ul class="right hide-on-med-and-down">
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<li><a href="${pageContext.request.contextPath}/jsp/userPage.jsp">User page</a></li>
						<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/deliveryDirectionsList">Delivery directions</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/jsp/tariffs.jsp">Tariffs</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/calculateCost.jsp">Calculate shipping
							cost</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/jsp/register.jsp">Registration</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>
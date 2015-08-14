<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header_dv">
	<img alt="Optum Logo"
		src="<c:url value="/resources/images/optumlogo.png" />">
	<div id="header_user">
		Welcome,
		<c:set var="employee" scope="session" value="${employee}" />
		<span id="fullname">${employee.firstName}&nbsp;${employee.lastName}</span>
		| <span id="empID">${employee.empID}</span>
	</div>
	<div class="info">
		<h1 class="title">MOM Library</h1>
	</div>
</div>
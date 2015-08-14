<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header_dv">
	<img alt="Optum Logo"
		src="<c:url value="/resources/images/optumlogo.png" />">
	<div id="header_user">
		<c:set var="employee" scope="session" value="${employee}" />
		<c:if test="${not empty employee}">
		
		<label>Employee Name:</label><span id="fullname"><c:out
				value="${employee.firstName}" />&nbsp; <c:out
				value="${employee.lastName}" /> </span><br /> <label>Employee ID:</label><span
			id="empID"><c:out value="${employee.empID}" /></span><br /> <label>Network
			ID:</label><span id="ntid"><c:out value="${employee.networkID}" /></span><br />
		<label>Manager:</label><span id="manager"><c:out
				value="${employee.manager}" /></span><br /> <label>Project:</label><span
			id="project"><c:out value="${employee.project}" /></span><br />
		</c:if>
	</div>
	<div class="info">
		<h1 class="title">eVariable Input</h1>
	</div>
</div>
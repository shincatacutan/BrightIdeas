<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>OTND Web</title>
	<meta name="GENERATOR" content="Microsoft Visual Studio .NET 7.1">
	<meta name="vs_targetSchema"
		content="http://schemas.microsoft.com/intellisense/ie5">
	<link rel="shortcut icon" href="<c:url value="/resources/favicon_oh.ico" />">
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery.dataTables.css" />"
		rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.css" />"
		rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.theme.css" />"
		rel="stylesheet">
	<%-- 
	    <link href="<c:url value="/resources/css/jquery-ui.structre.css" />" rel="stylesheet"> --%>
	
	<script src="<c:url value="/resources/js/jquery-1.11.2.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui-min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-validate-min.js" />"></script>
	<script src="<c:url value="/resources/js/additional-methods.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
</head>

<body>
	<div id="body_container">
		<div id="header_dv">
			<img alt="Optum Logo"
				src="<c:url value="/resources/images/optumlogo.png" />">
			<div id="header_user">
				<label>Employee Name:</label><span id="fullname"></span><br/>
				<label>Employee ID:</label><span id="empID"></span><br/>
				<label>Network ID:</label><span id="ntid"></span><br/>
				<label>Manager:</label><span id="manager"></span><br/>
				<label>Project:</label><span id="project"></span><br/>
			</div>
			<div class="info">
				<h1 class="title">Overtime and Night Differential</h1>
			</div>
		</div>

		<div id="body_dv">
			<c:set var="isAdmin" scope="session" value="${isAdmin}" />
			<c:choose>
				<c:when test="${isAdmin==true}">
			        <%@ include file="includes/admin.jsp"%>
				</c:when>
				<c:otherwise>
			        <%@ include file="includes/main.jsp"%>
				</c:otherwise>
			</c:choose>
			
		</div>

		<div class="footer">&copy; Copyright 2015 Optum</div>
	</div>
</body>
</html>

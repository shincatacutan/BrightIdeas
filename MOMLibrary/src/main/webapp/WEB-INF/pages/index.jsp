<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>OptumRx MOM Library</title>
<link rel="shortcut icon"
	href="<c:url value="/resources/favicon_oh.ico" />">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.dataTables.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/jquery-ui.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/jquery-ui.theme.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-tagsinput.css" />"
	rel="stylesheet">
<%-- 
    <link href="<c:url value="/resources/css/jquery-ui.structre.css" />" rel="stylesheet"> --%>

	<script src="<c:url value="/resources/js/jquery-1.11.2.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui-min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-validate-min.js" />"></script>
	<script src="<c:url value="/resources/js/additional-methods.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-tagsinput.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
</head>

<body>
	<div id="body_container">
		<jsp:include page="includes/header.jsp" />
		
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

		<jsp:include page="includes/footer.jsp" />
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMS</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${rootPath}/resources/css/main.css?ver=20190808">
<link rel="stylesheet" type="text/css"
	href="${rootPath}/resources/css/list.css?ver=20190808">
<link rel="stylesheet" type="text/css"
	href="${rootPath}/resources/css/button.css?ver=20190808">
<link rel="stylesheet" type="text/css"
	href="${rootPath}/resources/css/input.css?ver=20190808">
<link rel="stylesheet" type="text/css"
	href="${rootPath}/resources/css/view.css?ver=20190808">
<link rel="stylesheet" type="text/css"
	href="${rootPath}/resources/css/login.css?ver=20190808">
<link rel="stylesheet" type="text/css"
	href="${rootPath}/resources/css/album.css?ver=20190808">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf"%>
	<section class="menu-box"></section>
	<p></p>
	<section>
		<c:choose>
			<c:when test="${BODY == 'WRITE' }">
				<%@ include file="/WEB-INF/views/body/ems/write.jspf"%>
			</c:when>
			<c:when test="${BODY == 'VIEW' }">
				<%@ include file="/WEB-INF/views/body/ems/view.jspf"%>
			</c:when>
			<c:when test="${BODY == 'SEARCH' }">
				<%@ include file="/WEB-INF/views/body/ems/search.jspf"%>
			</c:when>
			<c:when test="${BODY == 'LIST' }">
				<%@ include file="/WEB-INF/views/body/ems/list.jspf"%>
			</c:when>
				<c:otherwise>
					<%@ include file="/WEB-INF/views/body/ems/list.jspf"%>
				</c:otherwise>
		</c:choose>

	</section>

</body>
</html>
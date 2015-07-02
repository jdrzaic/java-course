<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<style type="text/css">
.greska {
	font-family: fantasy;
	font-weight: bold;
	font-size: 0.9em;
	color: red;
}
</style>
</head>
<body>
<header>
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
 </header>
	<h5>Dostupni blogovi:</h5>
	<ol>
		<c:forEach var="user" items="${authors}">
			<li><a href='/aplikacija5/servleti/author/<c:out value="${user.nick}"/>'><c:out
						value="${user.nick}" /></a></li>
		</c:forEach>
	</ol>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Glasanje za bendove -->
<html>
	<head>
		<title>Glasanje</title>
	</head>
	<body bgcolor="${sessionScope.pickedBgColor}">
		<h1>Glasanje za omiljeni bend:</h1>
		<p>Od sljedećih bendova, koji Vam je bend najdraži?</p>
		<p>Kliknite na link kako biste glasali!</p>
		<ol>
			<c:forEach var="x" items="${bands}" varStatus="status">
			 	<li><a href="glasanje-glasaj?id=${x.getKey()}">${x.getValue().getName()}${haha}</a></li>
			</c:forEach>
		</ol>
	</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Vrijednosti sinusa i kosinusa brojeva -->
<html>
	<head>
		<title>Sine and cosine</title>
	</head>
	<body  bgcolor="${sessionScope.pickedBgColor}">
		<h1>List of sine and cosine values</h1>
		<p>List has ${results.size()} elements</p>
		<table>
			<head>
				<tr><th>Position</th><th>sin(x)</th><th>cos(x)</th></tr>
			</head>
			<tbody>
				<c:forEach var="z" items="${results}" varStatus="status">
					<tr><td>${status.index}.</td><td>${z.sin}</td><td>${z.cos}</td></tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
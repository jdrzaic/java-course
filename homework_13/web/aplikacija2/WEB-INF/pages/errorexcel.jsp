<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Ispis greske korisniku, koristi se kod izracunavanja potencija broja -->
<html>
	<head>
		<title>Error generating .xls file</title>
	</head>
	<body bgcolor="${sessionScope.pickedBgColor}">
		<p>Parameters for /powers should be 3 integers, so you request should look 
		like this: http://ipaddress:port/aplikacija2/powers?a=2&b=4&n=1 </p>
	</body>
</html>
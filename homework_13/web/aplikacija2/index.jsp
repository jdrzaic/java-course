<%@ page import="java.util.Date,java.util.Calendar" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Main page -->
<html>
	<head>
		<title>Main page</title>
	</head>
   <body  bgcolor="${sessionScope.pickedBgColor}">
   		<ul>
   			<li><a href="colors.jsp">Background color chooser</a></li>
   			<li><a href="/aplikacija2/trigonometric?a=0&b=90">Sine and cosine values for integers between 0 and 90, in degrees</a></li>
   			<li><a href="stories/funny.jsp">Funny story</a></li>
   			<li><a href="appinfo.jsp">Time of running</a></li>
   			<li><a href="/aplikacija2/powers?a=1&b=100&n=3">Powers of 1.,2. and 3. power of integers between 0 and 100</a>
   		</ul>
   </body>
</html>
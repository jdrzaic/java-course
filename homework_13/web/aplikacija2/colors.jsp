<%@ page import="java.util.Date,java.util.Calendar" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Primjer bloka u kojem možemo deklarirati funkcije -->
<%! 
%>
<!-- Promjena boje pozadine -->
<html>
 	<head>
		<title>Background color chooser</title>
	</head>
	<body bgcolor="${sessionScope.pickedBgColor}">
		<ul>
			<li><a href="/aplikacija2/setcolor?color=cyan">CYAN</a></li>
			<li><a href="/aplikacija2/setcolor?color=red">RED</a></li>
			<li><a href="/aplikacija2/setcolor?color=white">WHITE</a></li>
			<li><a href="/aplikacija2/setcolor?color=green">GREEN</a></li>
			<br/><br/>
			<li><a href="index.jsp">Main page</a>
		</ul>
	</body>
</html>
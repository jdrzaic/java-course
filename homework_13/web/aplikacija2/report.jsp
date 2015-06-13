<%@ page import="java.util.Date,java.util.Calendar" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- tortni dijagram -->
<html>
	<head>
	</head>
   <body  bgcolor="${sessionScope.pickedBgColor}">
   		<h1>OS usage</h1>
   		<p><b>Here are the results of OS usage in survey that we completed.</b></p>
   		<br/><br/>
   		<c:url value="/reportImage" var="imgUrl"/>
		<img alt="my image" src="${imgUrl}">
   </body>
</html>
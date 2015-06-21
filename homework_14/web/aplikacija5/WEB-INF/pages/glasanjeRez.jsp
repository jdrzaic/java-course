<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Rezultati glasanja -->
<html>
	<head>
		<style type="text/css">
			table.rez td {text-align: center;}
		 </style>
	</head>
	<body bgcolor="${sessionScope.pickedBgColor}">

 		<h1>Rezultati glasanja</h1>
 		<p>Ovo su rezultati glasanja.</p>
 		<table border="1" cellspacing="0" class="rez">
 			<thead><tr><th>Ime</th><th>Broj glasova</th></tr></thead>
 			<tbody>
			<c:forEach var="x" items="${voteResults}" varStatus="status">
				<tr><td>${x.getOptionTitle()} </td><td>${x.getVotesCount()} </td></tr>
			</c:forEach>
			</tbody>
		</table>
 		<h2>Grafički prikaz rezultata</h2>
 		<c:url value="/glasanje-grafika" var="imgUrl"/>
		<img alt="my image" src="${imgUrl}" width="400" height="300">
		<h2>Rezultati u XLS formatu</h2>
 		<p>Rezultati u XLS formatu dostupni su <a href="/aplikacija5/glasanje-xls">ovdje</a></p>
		
		 <h2>Razno</h2>
 		<p>Nesto o trenutnim pobjednicima ankete</p>
 		<ul>
 			<c:forEach var="x" items="${bestResults}" varStatus="status">
				<li><a href="${x.getOptionLink()}">${x.getOptionTitle() }</a></li>
			</c:forEach>
		</ul>
	</body>
</html>
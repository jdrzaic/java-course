<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<html>
  <body>
  <h1>Trenutno dostupne ankete:</h1>
  <c:choose>
   		<c:when test="${polls.isEmpty()}">
   			<p>Trenutno nema niti jedne ankete!</p>
   		</c:when>
   		<c:otherwise>
   			<ol>
   				<c:forEach var="entry" items="${polls}">
   					<li><p><a href="glasanje?pollID=<c:out value="${entry.id}"></c:out>">${entry.title}</a></p></li>
   				</c:forEach>
   			</ol>
   		</c:otherwise>
   	</c:choose>
  
  </body>
</html>
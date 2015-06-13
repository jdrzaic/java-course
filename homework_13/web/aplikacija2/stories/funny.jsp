<%@page import="java.awt.Color"%>
<%@page import="java.util.Random"%>
<%@ page import="java.util.Date,java.util.Calendar" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Ispis price, razlicitim bojama -->
<html>
   <body  bgcolor="${sessionScope.pickedBgColor}">
     <h1>Funny story</h1>
     <font color="color:rgb(<% 
        Random rand = new Random();
     float r = rand.nextFloat();
     float g = rand.nextFloat();
     float b = rand.nextFloat();
     Color c = new Color(r,g,b);
     out.print(c.getRed() + "," + c.getGreen() + "," + c.getBlue());
      %>)">
      	An infinite crowd of mathematicians enters a bar. The first one orders a pint, the second one a half pint, the third one a quarter pint…
		“I understand”, says the bartender – and pours two pints.
      </font>
   </body>
</html>
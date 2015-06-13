<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date,java.util.Calendar" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Metoda sluzi za ispis vremena od pokretanja aplikacije -->
<%! 
private void writeTime(javax.servlet.jsp.JspWriter out) throws java.io.IOException {
	long timestamp = new Date().getTime();
	long difference = timestamp - (long)getServletContext().getAttribute("startTime");
	Calendar cal = Calendar.getInstance();
	cal.setTimeInMillis(difference);
	final String timeString =
	    new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());
	cal.setTime(new Date(difference));
	out.print(-1 + cal.get(Calendar.DATE) + " days, ");
	out.print(-1 + cal.get(Calendar.HOUR) + " hours, ");
	out.print(cal.get(Calendar.MINUTE) + " minutes and ");
	out.print(cal.get(Calendar.SECOND) + " seconds.");
}
%>
<html>
	<head>
		<title>Time info</title>
	</head>
   <body  bgcolor="${sessionScope.pickedBgColor}">
   		<p style="font-size:20px">Application is running for:</p>
   		<p style="font-size:20px"><%writeTime(out); %></p>
   </body>
</html>
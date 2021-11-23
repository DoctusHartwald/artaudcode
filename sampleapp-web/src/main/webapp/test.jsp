<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="com.sample.service.delegate.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>CounterService EJB Test Page</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
<%!
	CounterServiceBusinessDelegate service = new CounterServiceBusinessDelegate();
%>
</head>
<body>
    <form name="inputForm" action="<%= request.getRequestURI()%>" method="get">
      Param:&nbsp;<input type="text" name="param" value="inc"/>&nbsp;<input type='submit' value='Send'/><br/>
    </form>
<%
	int result = 0;
	String param = request.getParameter("param");
	if (param != null) {
		session.getServletContext().log("Call Stateful Session EJB...");
		if ("inc".equals(param)) {		
			result = service.increment();
		} else {
			result = service.decrement();
		}
		session.getServletContext().log("EJB call done."); 
		out.println("<p>Result: " + result + "</p>");
	} else {
		out.println("<p>no result!</p>");
	}
%>
</body>
</html>

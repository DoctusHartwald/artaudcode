<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="com.sample.service.delegate.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>EchoService EJB Test Page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
  </head>
  
  <body>
  <form method='GET' action='<%= request.getContextPath() %>/index.jsp'>
	   Message:&nbsp;<input type='text' name='message'/>&nbsp;<input type='submit' value='Send'/><br/>
  </form>
<%
  String param = request.getParameter("message");
  if ((param != null) && (param.length() > 0)) {
    EchoServiceBusinessDelegate service = new EchoServiceBusinessDelegate();		
  	String result = service.echo(param);
  	out.println("<p>Result: " + result + "</p>");
  }
%>
  </body>
</html>

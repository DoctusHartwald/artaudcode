<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contacts</title>
</head>
<body>


Tous Mes Textes <br/>
<s:iterator value="myalltext">
			
			<s:property value="name"/>
			<s:property value="text"/>
			
			<s:url id="update" action="updateTextForm">
			<s:param name="textid" value="id" />
			</s:url>
			<s:a href="%{update}">Actualiser </s:a><br>
</s:iterator>
<br><br><br>


Tous Mes Images <br/>

Tous Mes Vidéos <br/>

Tous Mes Documents <br/>
</body>
</html>
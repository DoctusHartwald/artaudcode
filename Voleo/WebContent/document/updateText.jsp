<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Text</title>
</head>
<body>



<s:form id="updateText" action="updateText">
	<s:textfield id="documentName" label="Nom du document" name="textForm.name"/><br />
	<s:textfield id="documenttext" label="Texte du document" name="textForm.text"/><br />	
	
<s:submit label="Update"/>
</s:form>




</body>
</html>
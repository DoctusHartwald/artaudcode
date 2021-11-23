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



<s:form id="tryUpdateTextByOthers" action="tryUpdateTextByOthers">
	<s:textfield id="documentName" label="Nom du document" name="textFormByOthers.name"/><br />
	<s:textfield id="documenttext" label="Texte du document" name="textFormByOthers.text"/><br />	
	
<s:submit label="Mettre à jour" value="Mettre à jour"/>
</s:form>




</body>
</html>
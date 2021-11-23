<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<s:url value="/images/styles.css"/>" rel="stylesheet"
	type="text/css" />
<title>Docs</title>
</head>
<body>

<div id="header">header</div>
	<div class="content">
	<div id="titre"><a name="ObjectifduSite"></a>
	<h2>Textes</h2>
		<fieldset><legend align=top> Textes </legend>
<s:iterator value="allText"><br>

	<s:property value="name" escape="false"/> 
	<s:property value="text" escape="false"/>	
	<s:property value="status" escape="false" />
	
	<s:url id="update" action="updateDocumentForm">
		<s:param name="docId" value="id" />
		<s:param name="docType" value="'TEXT'" />
	</s:url>
	<s:a href="%{update}"> Modifier </s:a> 
	<s:a href="getCommentaire.action">Commentaires</s:a>
	<br>
</s:iterator><br>
</fieldset>
</div>
</div>
</body>
</html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>@Controller Example Plat</title>
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">	
</head>	
<body>
<div class="container">
	<h1>
		View plat
	</h1>
	<div class="span-12 last">	
		<form:form modelAttribute="plat" action="${plat.id}" method="post">
		  	<fieldset>		
				<legend>mes Plats</legend>
				<p>
					<form:label	for="NomPlat" path="NomPlat" cssErrorClass="error">Nom du plat</form:label><br/>
					<form:input path="NomPlat" /> <form:errors path="NomPlat" />			
				</p>
				<p>	
					<form:label for="Description" path="Description" cssErrorClass="error">Description </form:label><br/>
					<form:input path="Description" /> <form:errors path="Description" />
				</p>
				<p>
					<form:label for="Type" path="Type" cssErrorClass="error">Type </form:label><br/>
					<form:input path="Type" /> <form:errors path="Type" />
				</p>
			</fieldset>
		</form:form>
	</div>
	<hr>	
	
	<ul>
		<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
	</ul>	
</div>
</body>
</html>
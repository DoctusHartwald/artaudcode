<%@ page contentType="text/html" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Enregistrement </title>

</head>
<body>

<h2>Formulaire d'enregistrement</h2>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<form:form modelAttribute="nouveauplat" method="POST">
		<div>nom  plat<form:input path="nomPlat"/></div>
		<div>description<form:input path="description" /> </div>
		<div>type<form:input path="type" /></div>
		
		<div><input type="submit" value="Submit" /></div>
	</form:form>
</body>
</html>
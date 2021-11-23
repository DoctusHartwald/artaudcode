<%@ page contentType="text/html" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Enregistrement </title>

</head>
<body>
<h2>Formulaire d'enregistrement</h2>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<form:form modelAttribute="employe">
		<div>Prénom <form:input path="prenom"/></div>
		<div>Nom <form:input path="nom" /> </div>
		<div>Login<form:input path="login" /></div>
		<div>Mot de passe <form:input path="password"/></div>
		<div><input type="submit" value="Submit" /></div>
	</form:form>
</body>
</html>
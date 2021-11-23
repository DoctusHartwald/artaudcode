<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Accueil </title>
</head>
<body>
<h2>Bienvenue <c:out value="${employe.nom}" /> <c:out value="${employe.prenom}" />!
</h2>
</body>
</html>
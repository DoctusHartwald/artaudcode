<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Formulaire d'identification</title>
<style type="text/css">
.error{
	color: #ff0000;
	font-weight: bold ; 
}
</style>
</head>
<body>
<form:form  method="POST" action="login.htm" commandName="employe">
     <fieldset>
          <legend>Informations sur vous</legend>
          <label>Login</label> :
          <form:input path="login"/>
          <form:errors path="login" cssClass="error" />
          <hr />
          <label>Mot de passe</label> :
          <form:input path="password"/>
          <hr />
          <label>Valider</label> :
          <input type="submit"/>
     </fieldset>
</form:form>
</body>
</html>
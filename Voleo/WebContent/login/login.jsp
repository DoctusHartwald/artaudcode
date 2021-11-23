<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Voleo</title>
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
		
		
		**Import jQuery and SimpleModal source files**
<script src='js/lightboxEasy/basic/js/jquery.simplemodal.js' type='text/javascript'></script>

**Contact Form JS and CSS files**
<script src='js/lightboxEasy/basic/js/basic.js' type='text/javascript'></script>
		<link rel="stylesheet" type='text/css' href='js/lightboxEasy/basic/css/basic.css' media='screen'/>
		
		
	</head>
<body>



<s:head theme="ajax" debug="true" />
 -->
<s:if test="!#session.userId">
	<br />
	<s:form id="loginForm" action="login" >
		<s:textfield id="login" name="login" label="Identifiant" />
		<s:password id="password" name="password" label="Mot de passe" />
		<s:submit label="Login" value="se connecter" />
	</s:form>
	<div id='basicModal'>
<input type='button' name='basic' value='inscrire' class='demo'/>
</div>
<div id="basicModalContent" style='display:none'>
<s:form id="addUser" action="addUser">
<P STYLE="background-color:blue;;color: red; font-family: 'New Century Schoolbook', serif">
Inscription
</P>
	<s:textfield id="pseudo" name="user.pseudo" label="pseudo"/>
	<s:textfield id="login" name="user.login" label="login"/>
	<s:textfield id="password" name="user.password" label="password"/>
	<s:textfield id="notation" name="user.notation" label="notation"/>

	<s:textfield id="firstname" name="contact.firstname" label="firstname"/>
	<s:textfield id="lastname" name="contact.lastname" label="lastname"/>
	<s:textfield id="email" name="contact.email" label="email"/>
	<s:textfield id="fixPhone" name="contact.fixPhone" label="fixPhone"/>
	<s:textfield id="mobilePhone" name="contact.mobilePhone" label="mobilePhone"/>
	
	<s:submit label="Soumettre" />

</s:form>

</div>
<!-- &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <s:url id="addUser" value="/addUserForm.action" /><s:a theme="ajax" href="%{addUser}" id="addUserLink" targets="contenu" loadingText="Chargement..." >S'inscrire</s:a><br />
 -->
	<s:if test="%{invalidLogin}">
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Invalid login or password.
		<s:action name="resetInvalidLogin" executeResult="false" />
	</s:if>
</s:if>
<s:else>
	Mon compte:
	Vous êtes connecté!!!
	
	<s:url id="disconnect" action="disconnect">
	</s:url>
	<br /><s:a href="%{disconnect}">Se déconnecter</s:a>
	<a class="redimensionneur" onclick="redimmensionner();"></a>
</s:else>
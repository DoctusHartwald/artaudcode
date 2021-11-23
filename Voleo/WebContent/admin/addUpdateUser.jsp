<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voleo</title>
<link rel="stylesheet" type="text/css" href="css/voleo.css" />
<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />
<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>

</head>
<body>
		<div id="logo" onclick="window.location.href='index.action'"></div>
		<div id="perso">
			<!-- Bordures du cadre "perso" -->
			<div class="bord-bas-droite"></div>
			<div class="bord-bas-gauche"></div>
			<div class="bord-haut-droite"></div>
			<div class="cote-bas"></div>
			<div class="cote-droite"></div>
			<div id="perso_in">
				<s:action name="loginForm" executeResult="true" />
			</div>
		</div>
		<p class="titre">Voleo, le site de partage de connaissances</p>
		<s:div id="barreNavigationTags" cssClass="pres_titre">
			<s:action name="listSelectedTag" executeResult="true" />
		</s:div>
		<div id="gauche">
			<div class="pres_barre_verticale_gauche"></div>
			<div class="pres_barre_verticale_droite"></div>
			<div class="pres_titre">Rechercher</div>
			<div class="rechercher">
				<form id="searchForm" action="search.action" >
					<s:textfield id="keywords" size="13" name="request.keywords"/>
					<input type="submit" value="Rechercher"/>
				</form>
				<a href="searchForm.action">Recherche Avancée</a>
			</div>
			<div class="pres_titre">Liste des tags</div>
			<div class="liste_tags">
				<s:action name="listAvailableTag" executeResult="true" />
				<!--<s:iterator value="tags">
				<s:property value="name"/>(<s:property value="documentsCount"/>)
				<br />
				</s:iterator>-->
			</div>
		</div>
		<div id="droite">
			<div class="pres_barre_verticale_gauche"></div>
			<div class="pres_barre_verticale_droite"></div>
			<div class="pres_titre">Mon espace Perso</div>
			<div class="liste_derniers_articles">
				<s:action name="getEspacePerso" executeResult="true" />
			</div>
			<div class="pres_titre">Derniers Articles</div>
			<div class="liste_derniers_articles">                
				<s:action name="lastArticles" executeResult="true" />
			</div>
		</div>
<div id="contenu">
<div class="pres_titre">Avancement du site</div>
<h1>Admin</h1>
<ul>

	 <s:form id="searchUser" action="searchUser">
		

		<s:combobox label="Utilisateurs Pseudo" name="userSelected.id"
			list="allUser.{pseudo}" headerKey="-1"
			headerValue="--- Please Select ---" emptyOption="false" />

		<s:submit label="Rechercher" value="Rechercher"/>
</s:form> 
	<!--
<s:form id="searchUser" action="searchUser">
		<s:label name="userId" value="Select State Name:" />
    	<s:autocompleter theme="simple" list="allUser.{pseudo}" name="userId"/><br>
    	<s:submit label="Rechercher" />
    	
</s:form>
<s:form id="searchUser" action="searchUser">

<s:updownselect
list="allUser.{pseudo}"
name="userId"
value="id"
headerKey="-1"
headerValue="--- Please select ---"
emptyOption="false" />

		
<s:submit label="Rechercher" />
  

</s:form>-->
<!--  
Type User:
<s:iterator value="allUser">
		<s:property value="pseudo"/>
		<br>
		
		<s:url id="update" action="updateTypeUserToAdmin.action">
			<s:param name="userId" value="id" />
		</s:url>
		<img src="<s:url value="images/desktop.png" />" width="32"
					height="32" alt="update user" />
		<s:a href="%{update}"> Modifier Status à<b> ADMIN</b></s:a>
		
		
		<s:url id="delete" action="deleteUser.action">
			<s:param name="userId" value="id" />
		</s:url>
		<img src="<s:url value="images/delete.png" />" width="32" height="32"
			alt="delete user" />
		<s:a href="%{delete}"> Supprimer </s:a>

		<HR width="100%" size="0.3" color="blue">
		<br>
</s:iterator>



	<s:iterator value="userSearched">
		<s:property value="login" />
		<br>
		<s:property value="pseudo" />
		<br>

	</s:iterator>
	-->
</body>
</html>
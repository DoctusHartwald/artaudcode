<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Voleo</title>
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
		<script src="http://www.scribd.com/javascripts/view.js"></script>
		
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
			<div class="pres_titre">Mon espace Perso</div>
			<h2>
			<img src="<s:url value="images/search.png" />" width="26" height="26" alt="videos" />
			Recherche Avancée</h2>
			<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><a href="index.action">Retour</a>
						
 	<s:form id="searchForm" action="search" >
	<s:textfield id="keywords" label="Mots clés" name="request.keywords"/><br />
	<s:combobox
    label="Type de doc"
    name="request.documentType"
    list="{'TEXT','IMAGE','RAWFILE','VIDEO'}"
    headerKey="-1"
    headerValue="--- Please Select ---"
    emptyOption="true"/>	
      
    <s:combobox
    label="Recherche Date Mode"
    name="request.searchDateMode"
    list="{'BEFORE','RANGE','AFTER'}"
    headerKey="-1"
    headerValue="--- Please Select ---"
    emptyOption="true"/>	
    	<s:textfield id="firstDate" label="firstDate" name="request.firstDate"/><br />
    	<s:textfield id="secondDate" label="secondDate" name="request.secondDate"/><br />
 
  <s:combobox
    label="Utilisateurs Pseudo"
    name="request.userPseudo"
    list="allUser.{pseudo}"
    headerKey="-1"
    headerValue="--- Please Select ---"
    emptyOption="true"/>
<!--
 		<s:label name="userId" value="Utilisateurs Pseudo" />
    	<s:autocompleter list="allUser.{pseudo}" name="userId"  autoComplete="true"/><br>
    	 -->
	<s:submit label="Rechercher" value="Rechercher"/>	
</s:form>
					
	
		</div>
	</body>
</html>



















































<!--  
    
<html>
	<head>
		<title>Voleo</title>
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
	</head>
<body>

<s:form id="searchForm" action="search" >
	<s:textfield id="keywords" label="Mots clés" name="request.keywords"/><br />
	<s:combobox
    label="Type de doc"
    name="request.documentType"
    list="{'TEXT','IMAGE','RAWFILE','VIDEO'}"
    headerKey="-1"
    headerValue="--- Please Select ---"
    emptyOption="true"/>	
      
    <s:combobox
    label="Recherche Date Mode"
    name="request.searchDateMode"
    list="{'BEFORE','RANGE','AFTER'}"
    headerKey="-1"
    headerValue="--- Please Select ---"
    emptyOption="true"/>	
    	<s:textfield id="firstDate" label="firstDate" name="request.firstDate"/><br />
    	<s:textfield id="secondDate" label="secondDate" name="request.secondDate"/><br />
 

 		<s:label name="userId" value="Utilisateurs Pseudo" />
    	<s:autocompleter list="allUser.{pseudo}" name="userId"  autoComplete="true"/><br>
    	
	<s:submit label="Rechercher" />
	</s:form>
	
	
	
	
	
	
	
</body>
</html>-->
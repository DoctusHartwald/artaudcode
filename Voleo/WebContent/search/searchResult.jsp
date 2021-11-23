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
			<h1>Le document Sélectionné</h1>
  			<s:iterator status="stat" value="results">
	<s:property value="#stat.index" />)<br>
	<img src="<s:url value="images/stats.png" />" width="16" height="16" alt="next" />
	Score:<s:property value="score" /> % <br>
	
	Id Doc: <s:property value="id"/><br>
	<img src="<s:url value="images/id.png" />" width="16" height="16" alt="user id" />
	User :<s:property value="userPseudo" /><br>
	<img src="<s:url value="images/box.png" />" width="16" height="16" alt="user id" />
	Nom Doc: <s:a href=""><s:property value="name" /></s:a><br>
	<img src="<s:url value="images/create-date.png" />" width="16" height="16" alt="create date" />
	Date creation:<s:property value="createDate" /><br>
	<img src="<s:url value="images/update-date.png" />" width="16" height="16" alt="update date" />
	Date modification:<s:property value="updateDate" /><br>
	<img src="<s:url value="images/tags.png" />" width="16" height="16" alt="tags" />
	Tags:<s:property value="tags" /> <br>
	<img src="<s:url value="images/documents.png" />" width="16" height="16" alt="images" />
	Doc type:<s:property value="documentType" /> <br>

	<s:url id="view" action="getSearchedDocument">
			<s:param name="docId" value="id" />
			<s:param name="docType" value="documentType" />
	</s:url>
		<s:url id="modifier" action="updateDocumentForm.action">
			<s:param name="docId" value="id" />
			<s:param name="docType" value="documentType" />
	</s:url>
	<br>
	<img src="<s:url value="images/next.png" />" width="16" height="16" alt="next" />
	<s:a href="%{view}"> Regarder </s:a><br>
	<img src="<s:url value="images/desktop.png" />" width="16" height="16" alt="change" />
	<s:a href="%{modifier}">Modifier</s:a>
	<HR width="100%" size="0.3" color="blue">
</s:iterator>
<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><s:a href="searchForm.action">Autre recherche</s:a>	
		

					
	
		</div>
	</body>
</html>


































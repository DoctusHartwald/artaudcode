
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
<h2>Gestion des News</h2>

	<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><a href="index.action">Retour</a>
	<br/>
	<img src="<s:url value="images/add-news.png" />" width="16"
					height="16" alt="update news" />
	<s:a href="addNewsForm.action">Ajout d'une News</s:a>
	<br><br>
	<li>Gérer vos news:</li>
	<br>
	<s:iterator value="allNews">
		<s:property value="name" />
		<s:property value="text" escape="false" />
		<s:property value="createDate" />
		<s:property value="updateDate" />
		<br>
		<s:url id="update" action="updateNewsForm">
			<s:param name="newsId" value="id" />
		</s:url>
		<img src="<s:url value="images/add-news.png" />" width="16"
					height="16" alt="update news" />
		<s:a href="%{update}"> Modifier </s:a>


		<s:url id="delete" action="removeNews">
			<s:param name="newsId" value="id" />
		</s:url>
		<img src="<s:url value="images/delete-news.png" />" width="16" height="16"
			alt="delete news" />
		<s:a href="%{delete}"> Supprimer </s:a>

		<HR width="100%" size="0.3" color="blue">


	</s:iterator>





</div>
</body>
</html>
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
<script language="javaScript" type="text/javascript"
	src="js/francais.js"></script>
<script language="javaScript" type="text/javascript"
	src="js/whizzywig.js"></script>
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
<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="ajouter une discussion"/><s:a href="index.action">Retour</s:a><br>
<img src="<s:url value="images/add.png" />" width="16" height="16" alt="ajouter une discussion" />
<a href="addUpdateDiscussionForm.action">Ajout Discussion</a><br/>
	
	Discussions sans réponses:<br>
	<s:iterator value="listDiscussionSansReponse">
		<s:property value="titre"/>
		<s:property value="message"/><br>
	</s:iterator>
	<br>	
	(à finir)
	
	<HR width="100%" size="0.3" color="blue">	

			<s:iterator value="mesDiscussions">
				
				<h2><s:property value="titre"/></h2>
				<s:property value="message"/><br>
				Créer le <i><s:property value="createDate"/></i>
				par <b><s:property value="user.pseudo"/></b>
				<br/>
				
				<s:url id="view" action="listResponseDiscussion">
					<s:param name="forumId" value="id"/>
				</s:url>	
				<img src="<s:url value="images/user_comment.png" />" width="16" height="16" alt="comments" />				
				(<s:property value="countReponse"/>)
				<a href="<s:url value="listResponseDiscussion.action"><s:param name="forumId" value="id"/></s:url>">Réponses</a>
				
				
				
				<s:url id="repondre" action="addReponseForm">
					<s:param name="forumId" value="id" />
					<s:param name="userId" value="user.id"/>
				</s:url>
				<img src="<s:url value="images/comments-global.png" />" width="16" height="16" alt="comments" />				
				<s:a href="%{repondre}">Répondre</s:a>
				
				<HR width="100%" size="0.3" color="blue">
					
			</s:iterator>


</div>
</body>
</html>
	
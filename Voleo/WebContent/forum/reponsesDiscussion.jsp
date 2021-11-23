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
	
	<style>
	.titreStyle{
font-size: 14px; 
color: #FF9900;
font-size: 18px;
}
	</style>
	
	<style type="text/css">
table.sample {
	border-width: 1px 1px 1px 1px;
	border-spacing: 2px;
	border-style: dotted dotted dotted dotted;
	border-color: gray gray gray gray;
	border-collapse: collapse;
	background-color: rgb(255, 245, 238);
}
table.sample th {
	border-width: 1px 1px 1px 1px;
	padding: 2px 2px 2px 2px;
	border-style: inset inset inset inset;
	border-color: blue blue blue blue;
	background-color: rgb(250, 240, 230);
	-moz-border-radius: 0px 0px 0px 0px;
}
table.sample td {
	border-width: 1px 1px 1px 1px;
	padding: 2px 2px 2px 2px;
	border-style: inset inset inset inset;
	border-color: blue blue blue blue;
	background-color: rgb(250, 240, 230);
	-moz-border-radius: 0px 0px 0px 0px;
}
</style>

	
	
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
			<a href="addUpdateDiscussionForm.action">Ajout Discussion</a>
			<br>
			
		<table class="sample">
				<tr>	
					<th width="85px" style="background: #336699;color: white;">Auteur</th>
					<th width="525px"  style="background: #336699;color: white;">Sujet</th>
				</tr>
				
			<s:iterator status="stat" value="responseByDiscussion">
			
			<tr>
				<td style="background: #0099CC;color: white;">
					
					Post de <b><s:property value="user.pseudo"/></b><br>
					<img src="<s:url value="images/avatar.jpg" />" width="55" height="55" alt="supprimer" />
					
				</td>

				<td style="background: #CCFFCC">
					<fieldset style="background: #CCCCFF; border:2px; dotted #000;">
						<div class="titreStyle"><s:property value="#stat.index"/>.
							<s:property value="titre" escape="false"/>
						</div>
					 	<fieldset style="border-left: 7px #21177D; background: #CCFFFF; border:2px; dotted #000;"> 
					 	<img src="<s:url value="images/quotes1.jpg" />" width="25" height="25" alt="supprimer" />
					 	
						<s:property value="answer" escape="false"/><br>
						<img src="<s:url value="images/quotes2.jpg" />" width="25" height="25" alt="supprimer" align="right"/>
					
						</fieldset>
					
						<img src="<s:url value="images/comment_add.png" />" width="16" height="16" alt="supprimer" />
							<s:url id="repondre" action="sendWallToWallForm">
							<s:param name="userDestination" value="userOrigineForum.id"/>
							<s:param name="forumId" value="forum.id"/>
						</s:url>
						<s:a href="%{repondre}">Répondre à <s:property value="userOrigineForum.pseudo"/></s:a>
						
						<img src="<s:url value="images/user_comment.png" />" width="16" height="16" alt="supprimer" />
						
						<s:url id="repondre" action="sendWallToWallForm">
							<s:param name="userDestination" value="user.id"/>
							<s:param name="forumId" value="forum.id"/>
						</s:url>
						<s:a href="%{repondre}">Répondre à <s:property value="user.pseudo"/></s:a>
						
						
						<img src="<s:url value="images/comments-global.png" />" width="16" height="16" alt="supprimer" />
							
						<s:url id="repondre" action="addReponseForm">
							<s:param name="forumId" value="forum.id" />
							<s:param name="userId" value="userOrigineForum.id"/>
						</s:url>
						<s:a href="%{repondre}">Répondre à tous</s:a>	
						<br>
						
						Posté le <i><s:property value="createDate"/></i><br>
						Réf:Discussion de: <i><s:property value="userOrigineForum.pseudo"/> </i><br>
					
				</fieldset>
				</td>
			</tr>
				
			
			
				
			
				
					
			</s:iterator>
	</table>
</div>
</body>
</html>




















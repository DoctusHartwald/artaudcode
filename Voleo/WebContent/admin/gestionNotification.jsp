<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Voleo</title>
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
		
		
		<style type="text/css">@import "js/tablesorter/tests/assets/css/default.css";</style>
	
		<script type="text/javascript" src="js/tablesorter//jquery-latest.js"></script>
		<script type="text/javascript" src="js/tablesorter//jquery.metadata.js"></script>
		<script type="text/javascript" src="js/tablesorter//jquery.tablesorter.js"></script>

		<script type="text/javascript">
	
	$(function() {
		$("table").tablesorter();
	});
	
		</script>	
		
		<style>
.small_show
{
font-size: 9px;
font: "Arial", "Lucida Grande",Verdana,  Sans-Serif;	
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
			<div class="pres_titre">Avancement du site</div>
			<h2>Liste de tous les Utilisateurs</h2>
		<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><a href="index.action">Retour</a>
<br>
			<li>
				<img src="<s:url value="images/add.png" />" width="16"
					height="16" alt="add contact" />
				<s:a href="addContactForm.action">Ajouter Contact
				</s:a>
			</li>
			<li>
			<img src="<s:url value="images/desktop.png" />" width="16"
					height="16" alt="add contact" />
				<s:a href="updateUserForm.action">Modifier Mon Compte</s:a>
			</li>


<img src="<s:url value="css/images/eye.png" />"  alt="Control Panel" />
<s:a href="control.action">Recherche Spécifique</s:a>		
<br>
<br>


<table id="large" cellspacing="0">
	<thead>
		<tr>
			<th>Document</th>
			<th>User</th>
			<th>UserType</th>
			<th>Voir</th>
			<th>Supprimer</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th>Document</th>
			<th>User</th>
			<th>UserType</th>
			<th>Voir</th>
			<th>Supprimer</th>
		</tr>
	</tfoot>
	
			
	<s:property value="#stat.index" />.
	<tbody>
<s:iterator value="allNotificationCommentaire">
<tr>
			<td>
					<s:property value="document.name" /> 
			</td>
			<td>
					<s:property value="user.pseudo" /> 
			</td>
			
			<td>
					<s:property value="user.userType"/>
			</td>
		
			<td> 
					<s:url id="viewCommentaireNotification" action="getSelectedCommentaireByDocumentId">
						<s:param name="commentaireId" value="commentaire.id" />
					</s:url>
					<img src="<s:url value="/images/eye.png" />" width="16" height="16" alt="update User" />
					<s:a href="%{viewCommentaireNotification}">Voir</s:a>	
			</td>		
			
			<td>
			<s:url id="deleteCommentaireNotification" action="deleteUser">
					<s:param name="notificationCommentaireId" value="id" />
			</s:url>
				<img src="<s:url value="images/delete.png" />" width="16" height="16" alt="delete contact" />
			<s:a href="%{deleteCommentaireNotification}">Supprimer</s:a>
			</td>
			
		</tr>
	</s:iterator>
	</tbody>
	
			
</table>
	<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-2189649-1";
urchinTracker();
</script>

		</div>
	</body>
</html>


 
 
 
 
 

 
 
 
 
 
 
 
 
 
 
 
 
 
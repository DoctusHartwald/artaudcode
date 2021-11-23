<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Voleo</title>
		<style type="text/css">@import "js/tablesorter/tests/assets/css/default.css";</style>
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
		<script type="text/javascript" src="js/tablesorter//jquery-latest.js"></script>
		<script type="text/javascript" src="js/tablesorter//jquery.metadata.js"></script>
		<script type="text/javascript" src="js/tablesorter//jquery.tablesorter.js"></script>

		<script type="text/javascript">
	
	$(function() {
		$("table").tablesorter();
	});
	
		</script>	
		
		
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
			<h2>			
			<img src="<s:url value="images/backup.png"/>" width="26" height="26"/>
			Historique des modifications
			</h2>
			<br>
<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><a href="index.action">Retour</a>
<br>
<table id="large" cellspacing="0" Width="40%">
	<thead>
		<tr>
			<th width="10%">Document Modifié</th>
			<th width="10%">Utilisateur</th>
			<th width="10%">Date Approuvé</th>
			<th width="10%">Utilisateur Type</th>
			
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th width="10%"> Document Modifié</th>
			<th width="10%"> Utilisateur</th>
			<th width="10%">Date Approuvé</th>
			<th width="10%">Utilisateur Type</th>
		</tr>
	</tfoot>
	
			
	<s:property value="#stat.index" />.
	<tbody>
		<s:iterator status="stat" value="allhistoriqueByUser">	
	
		<tr>
			<td>
			<s:url id="view" action="getDocumentBackUpModification">
						<s:param name="docType" value="'TEXT'" />
						<s:param name="historiqueId" value="id"/>
			</s:url>
				
				<b>
				<s:iterator value="document">
					<s:property value="name"/>
					<s:a href="%{view}">Voir</s:a>		
				</s:iterator>
			</td>
			<td>
			<s:iterator value="user">
					<s:property value="pseudo"/>
				</s:iterator>
			</td>
			
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
			<s:property value="createDate" escape="false"/>
			</td>
		
			<td>
			<s:iterator value="user">
					<s:property value="userType"/>
				</s:iterator>
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


		<!-- 	<td id="c1" class="{sortValue: 0}">80</td>
			<td><select><option>brian</option><option>christian</option></select></td> -->

<!--  
			
				<li><s:property value="#stat.index" />)
				Modification du doc
				
					<s:url id="view" action="getDocumentBackUpModification">
						<s:param name="docType" value="'TEXT'" />
						<s:param name="historiqueId" value="id"/>
					</s:url>
				
				<b>
				<s:iterator value="document">
					<s:property value="name"/>
					<s:a href="%{view}">Voir</s:a>		
				</s:iterator>
				</b>
				par l'utisateur
				<b>
				<s:iterator value="user">
					<s:property value="pseudo"/>
				</s:iterator>
				</b>
				approuvé le <i><s:property value="createDate" escape="false"/></i>
				 <br>
				id : <s:property value="id"/>
			<s:url id="supprimer" action="supprimerHistorique">
				<s:param name="historiqueId" value="id" />
			</s:url>
				</li>
				-->


<!-- 
			<img src="<s:url value="images/reject.png" />" width="16" height="16" alt="rejeter" />
				<s:a href="%{supprimer}">Supprimer</s:a>
			 -->
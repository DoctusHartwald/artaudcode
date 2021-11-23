

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voleo-Control Panel</title>
<link rel="stylesheet" type="text/css" href="css/voleo.css" />
<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />

<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>


<style type="text/css">@import "js/tablesorter/tests/assets/css/default.css";</style>
	
		<script type="text/javascript" src="admin/js/jquery-latest.js"></script>
		<script type="text/javascript" src="admin/js/jquery.metadata.js"></script>
		<script type="text/javascript" src="admin/js/jquery.tablesorter.js"></script>

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


<!-- Autocompleter PART -->


<!-- Autocompleter List Pseudo -->
 <script  type="text/javascript" src="admin/js/prototype.js" ></script>
  <script  type="text/javascript" src="admin/js/scriptaculous.js"></script>

  <style type="text/css" media="screen">
    .selected { background-color: #888; }
  </style>
<style type="text/css">
	body {
		font-family: Helvetica;
		font-size: 11px;
		color: #000;
	}
	
	.suggestionList {
		margin: 0px;
		padding: 0px;
		background: #212427 url(admin/images/tick.png) no-repeat  left  ;
		color: #FFFFFF;
		
	}
	
	.suggestionList li {
		margin: 0px 0px 3px 0px;
		padding: 3px;
		cursor: pointer;
	}
	
	.suggestionList li:hover {
				background-color: #811453;
				padding: 2px 6px 2px 6px;
		/*background-color: #659CD8;*/
	}
</style>

<!-- End of Autocompleter PART -->

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
<!-- -Contenu du site a modifier  -->
<div id="contenu">
<div class="pres_titre">Admin   **Control Panel**</div>


<!--Partie a Copier  -->
<s:if test="flag.equals('search')">
	<h1>Liste de Pseudo</h1>
	
	<s:set name="listeDePseudo" value="pseudoList" /> <br />
	<!-- Search Nombre DOC par rapport au Pseudo -->
	 <s:form id="pseudoList" method="post" action="userSearch">
		<img src="/Voleo/admin/images/database_refresh.png"
			alt="Looking for..." />
		<s:textfield id="bands_from_the_70s" name="userSearch.pseudo"
			onkeyup="javascript:pseudo(%{listeDePseudo})"
			onchange="javascript:pseudo(%{listeDePseudo})"
			onclick="javascript:pseudo(%{listeDePseudo})" label="Pseudo :"
			cssStyle="position: relative;left: 30px;margin: 10px 0px 0px 0px;width: 200px;background-color: #212427;-moz-border-radius: 7px;-webkit-border-radius: 7px;border: 2px solid #000;	color: #fff;">
		</s:textfield>
		<img src="/Voleo/admin/images/upArrow.png"
			style="position: relative; top: -12px; left: 30px;" alt="upArrow" />
		<span id="indicator1" style="display: none"><img
			src="/Voleo/admin/images/loading.gif" alt="Working..." /></span>
		<div class="suggestionList" id="band_list" style="display: none"></div>
		<s:submit label="Search" value="Rechercher" /> Search <br />
	</s:form>


  		<script type="text/javascript">
  		function pseudo(argument){
		/*alert(argument);*/
		new Autocompleter.Local('bands_from_the_70s', 'band_list', argument, {minChars: 1, indicator: 'indicator1'});
		}
  </script>
</s:if>



<s:if test="flag.equals('display')">
<s:a href="flag.action">Retour</s:a>
<table id="large" cellspacing="0">
	<thead>
		<tr>
			<th>Pseudo</th>
			<th>Login</th>
			<th>UserType</th>
			<th width="5px">Note générale</th>
			<th width="5px"><div class=small_show id=small_show>Notation générale des Docs (/5)</div></th>
			<th>Nombre Docs</th>
			<th width="5px">Nbre Docs Notés</th>
			<th width="5px"><div class=small_show id=small_show>Notation générale des Commentaires (/5)</div></th>
			<th width="5px">Nbres Commentaires</th>
			<th width="5px">Nbre Commentaires Notés</th>
			
			<th>Modifier</th>
			<th>Supprimer</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th>Pseudo</th>
			<th>Login</th>
			<th>UserType</th>
			<th width="5px">Note générale</th>
			<th width="5px">Notation générale des Docs (/5)</th>
			<th>Nombre de Docs</th>
			<th width="5px">Nombre de ses Docs Notés</th>
			<th width="5px">Notation générale des Commentaires (/5)</th>
			<th width="5px">Nombres de Commentaires</th>
			<th width="5px">Nbre Commentaires Notés</th>
			<th>Modifier Type User</th>
			<th>Supprimer</th>
		</tr>
	</tfoot>
	
			
	<s:property value="#stat.index" />.
	<tbody>
<s:iterator value="searchedUser">
<tr>
			<td>
					<s:property value="pseudo" /> 
			</td>
			<td>
					<s:property value="login" /> 
			</td>
			
			<td>
					<s:property value="userType"/>
			</td>
			
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
					<s:property value="noteGenerale"/>
			</td>
			
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
					<s:property value="meanArticle"/>
			</td>
			
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
					<s:property value="docCount"/>
			</td>
			
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
					<s:property value="hitArticle"/>
			</td>
		
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
					<s:property value="meanComment"/>
			</td>
			
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
					<s:property value="commentaireCount"/>
			</td>
			
			<td class="{sortValue: <s:property value="#stat.index" />}" > 
					<s:property value="hitComment"/>
			</td>
			
			<td> 
					<s:url id="update" action="changeUserType">
						<s:param name="userId" value="id" />
					</s:url>
					<img src="<s:url value="/images/update.png" />" width="16" height="16" alt="update User" />
					<s:a href="%{update}">Modifier</s:a>	
			</td>		
			
			<td>
			<s:url id="delete" action="deleteUser">
					<s:param name="userId" value="id" />
			</s:url>
				<img src="<s:url value="images/delete.png" />" width="16" height="16" alt="delete contact" />
			<s:a href="%{delete}">Supprimer</s:a>
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
</s:if>

<br/>
</div>
</body>
</html>






















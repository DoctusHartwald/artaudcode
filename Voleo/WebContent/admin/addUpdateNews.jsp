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
<h1>News</h1>
<ul>

	<s:if test="news.id != null">
	Modifier News:
	<!-- 
<s:form id="textForm" action="updateDocument" enctype="multipart/form-data">
	<s:textfield id="documentName" label="Nom du document" name="doc.name"/><br />
	<s:textfield id="documenttext" label="Texte du document" name="doc.text"/><br />	
	<s:textfield id="tagName" label="Nom du tag" name="newTagName"/><br />
			
	<s:submit label="Sauvegarder" />
</s:form>
-->

		<form id="updateNewsForm" method="post" action="updateNews.action">
		<input type="hidden" name="newsId" /> Titre: <input type="text"
			value="<s:property value="news.name" />" name="news.name" /> <textarea
			id="editedText" name="news.text" style="width: 100%; height: 300px">
				<s:property value="news.text" escape="false" />
		</textarea> 
		<!-- <s:textfield id="tagName" label="Nom du tag" name="tags.name" /> --> 
		<script
			type="text/javascript">
		buttonPath = "js/btn/"; //directory holding button images
		cssFile = "css/editor.css";
		makeWhizzyWig("editedText", "formatblock newline bold italic underline | left center right | number bullet indent outdent | undo redo | rule | link image table");
		</script> <input type="submit" value="Soumettre"></form>

	</s:if>
	<s:else>
	Ajouter News:
	<!--  
<s:form id="textForm" action="addDocument" enctype="multipart/form-data">
	<s:textfield id="documentName" label="Nom du document" name="doc.name"/><br />
	<s:textfield id="documenttext" label="Texte du document" name="doc.text"/><br />	
	<s:textfield id="tagName" label="Nom du tag" name="newTagName"/><br />
				
	<s:submit label="Sauvegarder" />
</s:form>
-->

		<form id="addNewsForm" method="post" action="addNews.action">
		<input type="hidden" name="newsId" /> Titre: <input type="text"
			value="<s:property value="title" />" name="news.name" /> <textarea
			id="editedText" name="news.text" style="width: 100%; height: 300px">
				<s:property value="editedText" escape="false" />
		</textarea> 
		 <script
			type="text/javascript">
		buttonPath = "js/btn/"; //directory holding button images
		cssFile = "css/editor.css";
		makeWhizzyWig("editedText", "formatblock newline bold italic underline | left center right | number bullet indent outdent | undo redo | rule | link image table");
		</script> <input type="submit" value="Soumettre"></form>


	</s:else>









</ul>

</div>
</body>
</html>







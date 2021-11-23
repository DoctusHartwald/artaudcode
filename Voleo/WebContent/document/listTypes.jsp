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
			<h1>Mes Documents</h1>
			
			
			<s:url id="image" action="listDocuments">
	<s:param name="docType" value="'IMAGE'" />
</s:url>
<s:url id="video" action="listDocuments">
	<s:param name="docType" value="'VIDEO'" />
</s:url>
<s:url id="rawfile" action="listDocuments">
	<s:param name="docType" value="'RAWFILE'" />
</s:url>

<s:url id="text" action="listDocuments">
	<s:param name="docType" value="'TEXT'" />
</s:url>
<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><a href="index.action">Retour</a>
<br>
			<ul>
			<img src="<s:url value="images/forum.png" />" width="32" height="32" alt="forum" />		
			<li><a href="listDiscussion.action">Forum</a><br/></li>
			</ul>

			<ul>
			<img src="<s:url value="images/documents.png" />" width="32" height="32" alt="documents" />		
			<s:a href="%{rawfile}">
			<li>
			Documents (<s:property value="rawfileCount" />)</li>
			</s:a><br/>
			<img src="<s:url value="images/text.png" />" width="32" height="32" alt="text" />
			<s:a href="%{text}">
			<li>
			Texts (<s:property value="textCount" />)</li>
			</s:a><br/>
			<img src="<s:url value="images/image.png" />" width="32" height="32" alt="images" />
			<s:a href="%{image}">
			<li>
			Image (<s:property value="imageCount" />)</li>
			</s:a><br/>
			<img src="<s:url value="images/video.png" />" width="32" height="32" alt="videos" />
			<s:a href="%{video}">
			<li>
			Videos (<s:property value="videoCount" />)</li>
			</s:a><br/>
			
			</ul>		
					
			
		</div>
	</body>
</html>






<!--  


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<s:url value="/images/styles.css"/>" rel="stylesheet"
	type="text/css" />
<title>List Types</title>
</head>
<body>


<s:url id="image" action="listDocuments">
	<s:param name="docType" value="'IMAGE'" />
</s:url>
<s:url id="video" action="listDocuments">
	<s:param name="docType" value="'VIDEO'" />
</s:url>
<s:url id="rawfile" action="listDocuments">
	<s:param name="docType" value="'RAWFILE'" />
</s:url>

<s:url id="text" action="listDocuments">
	<s:param name="docType" value="'TEXT'" />
</s:url>


<div id="header">header</div>
	<div class="content">
	<div id="titre"><a name="ObjectifduSite"></a>
	<h2>Mes contacts</h2>
		<fieldset><legend align=top> Mes Documents </legend>
			<s:a href="%{rawfile}">
			Documents (<s:property value="rawfileCount" />)
			</s:a><br/>
			<s:a href="%{text}">
			Text (<s:property value="textCount" />)
			</s:a><br/>
			<s:a href="%{image}">
			Image (<s:property value="imageCount" />)
			</s:a><br/>
			<s:a href="%{video}">
			Video (<s:property value="videoCount" />)
			</s:a><br/>
	   </fieldset>
	   <br>
	
</div>
</div>







<br/><br/><br/>


</body>
</html>

-->
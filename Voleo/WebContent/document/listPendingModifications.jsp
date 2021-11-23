
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

<script>
function pop() {
window.open('getSearchedDocument.action','height="150px",width="150px",top=z,left=t,resible=no');
}
</script> 


<script language="JavaScript">
function MDM_openWindow(theURL,winName,features)
{
var _W=window.open(theURL,winName,features);
_W.focus();
_W.moveTo(600,550);
}
</SCRIPT> 




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


<ul>
	<h2>Mes Modifications en attente :</h2>

<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><a href="index.action">Retour</a>
<br>
	
	
	
	<s:iterator value="allPending">
		<li><i> Nom du document d'origine:</i></li><s:property value="targetDocument.name" />
		<br><br>
		<s:url id="view" action="getSearchedDocument">
			<s:param name="docId" value="targetDocument.id" />
			<s:param name="docType" value="'TEXT'" />
		</s:url>
		
	<!-- <a href="getSearchedDocument.action" onClick="pop()">Regarder</a>  -->	
		<img src="<s:url value="images/next.png" />" width="16" height="16" alt="next" />
		<s:a href="javascript:MDM_openWindow('%{view}','Interface1','width=600,height=600, status=yes, directories=no, toolbar=no, location=yes, menubar=no, scrollbars=yes, resizable=yes')">Regarder votre document d'origine</s:a>
		<br><br>
		<b>Les Modifications:</b><br><br>
		<i>User Pseudo:</i> <s:property value="user.pseudo" /><br>
		<i>Nom du document:</i> <s:property value="name" escape="false"/><br>
		<i> Texte du document: </i><br>
		<s:property value="text" escape="false"/>
		<s:url id="approve" action="approve.action">
			<s:param name="modId" value="id" />
		</s:url>
		
		<s:url id="reject" action="reject.action">
			<s:param name="modId" value="id" />
		</s:url>
		
		<img src="<s:url value="images/approuve.png" />" width="16"
					height="16" alt="approuver" />
		<s:a href="%{approve}">Approuver</s:a>
		
		<img src="<s:url value="images/reject.png" />" width="16"
					height="16" alt="rejeter" />
		<s:a href="%{reject}">Rejeter</s:a>
		<br>	

	</s:iterator>
	

	
	
	
	
	
</ul>


</div>
</body>
</html>

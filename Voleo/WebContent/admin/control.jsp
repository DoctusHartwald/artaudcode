

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

</head>
<body>

<div id="logo"></div>
<div id="perso"><!-- Bordures du cadre "perso" -->
<div class="bord-bas-droite"></div>
<div class="bord-bas-gauche"></div>
<div class="bord-haut-droite"></div>
<div class="cote-bas"></div>
<div class="cote-droite"></div>

</div>

<p class="titre">Voleo, le site de partage de connaissances</p>

<div id="gauche">
<div class="pres_barre_verticale_gauche"></div>
<div class="pres_barre_verticale_droite"></div>
<div class="pres_titre">Rechercher</div>
<div class="rechercher"><input type="text" size="16"> <input
	type="button" value="trouver"></div>
<div class="pres_titre">Liste des tags</div>
<div class="liste_tags">voiture<br>
bateau<br>
physique<br>
chimie<br>
electronique<br>
informatique<br>
couture<br>
peinture sur glace<br>
helicoptère</div>
</div>

<div id="droite">
<div class="pres_barre_verticale_gauche"></div>
<div class="pres_barre_verticale_droite"></div>
<div class="pres_titre">Mon espace Perso</div>
<div class="liste_derniers_articles">
<p class="text_liste_derniers_articles"><s:a
	href="listContacts.action">Mes contacts</s:a><br>
Mes documents<br>
Mon Compte<br>
</p>
</div>
<div class="pres_titre">Derniers Articles</div>

<div class="liste_derniers_articles"><img src="css/logo.png"
	width="100px"></img> <s:iterator value="nlastText">
	<p class="text_liste_derniers_articles"><s:property value="name" /></p>
</s:iterator></div>

</div>

<!-- -Contenu du site a modifier  -->
<div id="contenu">
<div class="pres_titre">Admin   **Control Panel**</div>
<h1>Control Panel</h1>
<img src="<s:url value="css/images/arrow.png" />"  alt="Number Comment" />
Nombre total mes Commentaire : <s:property value="commentaireCountAdmin" />
<br/>
<table id="echange">
	<th width='60'>id</th>
	<th width='90'>Login</th>
	<th width='90'>Pseudo</th>
	<th width='90'>User Type</th>
	<th width='90'>Nombre de Commentaire</th>

	<s:iterator value="allUser">
		<tr>
			<td><s:property value="id" /></td>
			<td><s:property value="login" /></td>
			<td><s:property value="pseudo" /></td>
			<td><s:property value="userType" /></td>
			<td><s:property value="commentaireCountUser" /></td>
			<!-- 
			<td><s:property value="textCountUser" /></td>
			<td><s:property value="imageCountUser" /></td>
			<td><s:property value="	videoCountUser " /></td>
			 -->
		</tr>
	</s:iterator>
	
</table>
<br />
</div>
</body>
</html>






















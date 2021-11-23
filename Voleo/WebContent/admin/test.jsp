<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voleo</title>
<h1>formulaire</h1>
<body>




<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voleo</title>
<link rel="stylesheet" type="text/css" href="css/voleo.css" />
<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />
<link rel="stylesheet" href="UItab/ui.tabs.css" type="text/css"
	media="print, projection, screen">

<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>


<!-- Panel Tab UI  -->
<script src="UItab/jquery-1.2.3.pack.js" type="text/javascript"></script>
<script src="UItab/ui.tabs.pack.js" type="text/javascript"></script>
<script type="text/javascript">
            $(function() {
                $('#container-5 > ul').tabs({ fx: { height: 'toggle', opacity: 'toggle' } });
            });
        </script>

</head>
<body>

<div id="logo"></div>
<div id="perso"><!-- Bordures du cadre "perso" -->
<div class="bord-bas-droite"></div>
<div class="bord-bas-gauche"></div>
<div class="bord-haut-droite"></div>
<div class="cote-bas"></div>
<div class="cote-droite"></div>

<div id="perso_in"><s:action name="loginForm" executeResult="true" />
</div>
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
<s:a href="listTypes.action">Mes documents</s:a><br>
Mon Compte<br>
</p>
</div>
<div class="pres_titre">Derniers Articles</div>

<div class="liste_derniers_articles"><img src="css/logo.png"
	width="100px"></img> <s:iterator value="nlastText">
	<p class="text_liste_derniers_articles"><s:property value="name" /></p>
</s:iterator></div>
</div>
<div id="contenu">
<div class="pres_titre">Mon panneau de Contrôle</div>
<h1>Ajout d'utilisateur</h1>


<s:form id="contactForm" action="addContact">
	<s:textfield id="firstname" name="contact.firstname" label="Firstname" />
	<br />
	<s:textfield id="lastname" name="contact.lastname" label="Lastname" />
	<br />
	<s:textfield id="email" name="contact.email" label="email" />
	<br />
	<s:textfield id="fixPhone" name="contact.fixPhone" label="Fix Phone" />
	<br />
	<s:textfield id="mobilePhone" name="contact.mobilePhone"
		label="Mobile Phone" />
	<br />
	<s:submit label="Add">Add</s:submit>
</s:form></div>
</body>
</html>







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
<script language="javaScript" type="text/javascript" src="js/francais.js"></script>
<script language="javaScript" type="text/javascript" src="js/whizzywig.js"></script>
<s:head debug="false" theme="ajax"/>
		
		<script>
$(document).ready(function(){
var newsoption1 = {
firstname: "mynews",
secondname: "showhere",
thirdname:"news_display",
fourthname:"news_button",
playingtitle:"Now Playing:",
nexttitle:"Next News:",
prevtitle:"Prev News:",
newsspeed:'8000',
effectis:'1',
mouseover:true
}
$.init_news(newsoption1);
});
</script>
		
<style>
.news_style{
display:none;
}
.news_show
{
background-color: #FFCC99;
color:black;
width:550px;
height:150px;
font: normal 100% "Arial", "Lucida Grande",Verdana,  Sans-Serif;
overflow: auto;	

}
.news_border
{
background-color: #FFCC99;
width:550px;
height:150px;
font: normal 100% "Arial", "Lucida Grande",Verdana,  Sans-Serif;
border: 1px solid gray;
padding: 5px 5px 5px 5px;
overflow: auto;	

}
.news_mark{
background-color:#FFCC99 ;
font: normal 70% "Arial", "Lucida Grande",Verdana,  Sans-Serif;
border: 0px solid gray;
width:561px;
height:35px;
color:black;
text-align:center;
}
.news_title{
font: bold 120% "Arial", "Lucida Grande",Verdana,  Sans-Serif;
border: 0px solid gray;
padding: 5px 0px 9px 5px;
color:black;
}
.news_show img{

margin-left: 5px;
margin-right: 5px;

}
.buttondiv
{
position: absolute;
/*float: left;*/
/*top: 169px;*/
padding: 5px 5px 5px 5px;
background-color:white ;
border: 1px solid gray;
/*border-top-color: white;*/
border-top:none;
height:20px;
}

</style>
		
<!-- Import jQuery and SimpleModal source files -->
<script src='js/lightboxEasy/basic/js/jquery.simplemodal.js' type='text/javascript'></script>

<!-- Contact Form JS and CSS files -->
<script src='js/lightboxEasy/basic/js/basic.js' type='text/javascript'></script>
		<link rel="stylesheet" type='text/css' href='js/lightboxEasy/basic/css/basic.css' media='screen'/>

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
			<h2>Discussions</h2>
			
			
			Ajouter Discussion:

		<form id="editForm" method="post" action="addDiscussionForum.action">	
		<input type="hidden" name="newsId" /> Titre: <input type="text"
			value="<s:property value="title" />" name="forum.titre" /> 
			<textarea id="editedText" name="forum.message" style="width: 100%; height: 300px">
				<s:property value="editedText" escape="false" />
		</textarea> 
		<script
			type="text/javascript">
		buttonPath = "js/btn/"; //directory holding button images
		cssFile = "css/editor.css";
		makeWhizzyWig("editedText", "formatblock newline bold italic underline | left center right | number bullet indent outdent | undo redo | rule | link image table");
		</script> 
<input type="submit" value="Soumettre">


</form>
			
	
			
			
		</div>
	</body>
</html>
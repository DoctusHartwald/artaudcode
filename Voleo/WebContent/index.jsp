<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Voleo</title>
		
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />	
		<link rel="stylesheet" type="text/css" href="css/menu_vertical.css" />	
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<link rel="stylesheet" type='text/css' href='js/lightboxEasy/basic/css/basic.css' media='screen'/>	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
		
		<script type="text/javascript" src="js/easynews/jquery-1.2.3.pack.js"></script>
<script type="text/javascript" src="js/easynews/jquery.easynews.js"></script>
	
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
	
	
<!-- Import jQuery and SimpleModal source files -->
<script src='js/lightboxEasy/basic/js/jquery.simplemodal.js' type='text/javascript'></script>

<!-- Contact Form JS and CSS files -->
<script src='js/lightboxEasy/basic/js/basic.js' type='text/javascript'></script>
<script src='js/jquery.hoveraccordion.js' type='text/javascript'></script>
<script>
 $(document).ready(function(){
   $('#example2').hoverAccordion({
      activateitem: '1',
      speed: 'fast'
   });
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
			<div class="pres_titre">Bienvenue</div>
			<s:if test="userType!=NULL">	
				<s:if test="userType.equals('ADMIN')">
					Bienvenue Admin  !<br>
					<s:a href="adminAddUpdateUserForm.action">Test</s:a>
					<br>
					<img src="<s:url value="css/images/eye.png" />"  alt="Control Panel" />
					<s:a href="listUser.action">Administration des Utilisateurs</s:a>
					
					<br>
					<img src="<s:url value="images/news.png" />" width="16" height="16" alt="images" />
					<s:a href="listAllNews.action">Administration des News</s:a>
					<br>
					<s:a href="getAllNotification.action">Administration des Notifications</s:a>			
					<br>
					<br>
					<fieldset>
					<legend><b><img src="/Voleo/admin/images/admin.png" alt="Administration des Utilisateurs"  width="40" height="40"  />
					Administration des Utilisateurs</b></legend>
					<table>
					<tr><td>
					<img src="<s:url value="css/images/eye.png" />"  alt="Control Panel" />
					<s:a href="listUser.action">Gestion Utilisateurs</s:a>
					</td></tr>
					<tr><td>
					<img src="<s:url value="admin/images/find.png" />" width="16" height="16"  alt="Control Panel" />
					<s:a href="control.action">Recherche d'un Utilisateur</s:a>
					</td></tr>
					</table>
					
					</fieldset>
					<br/>
					<fieldset>
					<legend><b><img src="/Voleo/admin/images/graphique.gif" alt="Evolution du Site" />
					Evolution du Site</b></legend>
					<table>
					<tr><td>
					<img src="<s:url value="admin/images/graph.png" />" width="16" height="16" alt="images" />
					<s:a href="dateCountCommentaire.action">Graphique des Documents </s:a>
					</td></tr>
					<tr><td>
					<img src="<s:url value="admin/images/forum.png" />" width="16" height="16" alt="images" />
					<s:a href="forumCount.action">Graphique Forum </s:a>
					</td></tr>	
					</table>	
						</fieldset>	
						<br/>
					<fieldset>	
					<legend><b><img src="/Voleo/admin/images/data.jpg" alt="Administration des News" />
					Administration de mes News</b></legend>
					<table>
					<tr><td>
					<img src="<s:url value="images/news.png" />" width="16" height="16" alt="images" />
					<s:a href="listAllNews.action">Administration des News</s:a>
					</td></tr>
					</table>
					</fieldset>	
					
					<fieldset>	
					<legend><b><img src="/Voleo/admin/images/exportCSV.gif" alt="Export to Excel" />
					Export to Excel</b></legend>
					<table>
					<tr><td>
					<img src="/Voleo/admin/images/CSV.gif" width="30" height="30" alt="images" />
					<s:a href="exportFormatComma.action">Format CSV <i>(with Comma)</i> </s:a>
					</td></tr>
					</table>
					</fieldset>	
	
	
					<fieldset>	
					<legend><b><img src="/Voleo/admin/images/find.png" alt="Surveillance" />
				    Surveillance</b></legend>
					<table>
					<tr><td>
					<img src="/Voleo/admin/images/search.png" width="30" height="30" alt="images" />
					<s:a href="surveillance.action">Search by Pseudo </s:a>
					</td></tr>
					</table>
					</fieldset>	
					
					
					
					
					
					
				</s:if>
				<s:if test="userType.equals('USER')">
					Bienvenue User !
				</s:if>
			</s:if>
			<s:else>
				<div style="text-align:center;font-size:large">Bienvenue sur Voleo. Vous pouvez vous inscrire ou vous connecter en haut à droite.</div>
			</s:else>
			<br />
			
			
			<table>
				<tr>
					<td>
						<h2>News</h2>
					</td>
					
					<td>
						<h2>Notifications Forum</h2>
					</td>
				</tr>
				<tr>				
					<td>

						<div align=left id=mynewsdis>
						 		<div class=news_border>
									<div id=showhere class=news_show ></div>
								</div>
								<div class=buttondiv id=news_button>
									<img src=js/easynews/prev.gif align="absmiddle" id=news_prev><img src=js/easynews/pause.gif align="absmiddle" id=news_pause><img src=js/easynews/next.gif align="absmiddle" id=news_next >
								</div>
								<div class=news_mark>
									<div id=news_display class=news_title></div>
								</div>
								<s:iterator status="stat" value="allNews">
									<br>
								    <div id=mynews>
										<div class=news_style rel="News 1">
							
										<table><tr><td><img src="js/easynews/bignews.png" align=left width="35" height="35">
							 			<font color="#0099FF"><s:property value="name"/></font></td></tr><tr><td>
										<img src="js/easynews/adminIcon.png" align=left width=30 height=30>
										<font color="#FF9900">
										<b>	le <s:property value="createDate"/> <s:property value="updateDate"/></b>
										</font> 
										<s:property value="text" escape="false"/></td></tr></table>
										</div>
									</div>
								</s:iterator><br>
							<HR width="100%" size="0.3" color="blue">		
						</div>				
			
					</td>
					<td>
					<s:iterator value="nLastNotificationForum">
						<table>
							<tr>
								<td>
									<img src="images/new.png" align=left width="20" height="20">
									<font color="#0099FF">Nouvelle Réponse : De <s:property value="userOrigine.pseudo" escape="false"/></font>		
									
					 			</td>
					 		</tr>
					 		<tr>
					 			<td>
									<img src="images/personal.png" align=left width="25" height="25">
									<font color="#FF9900">
									<b>	le <s:property value="createDate"/> </b>
									</font> 
									Détails de la réponse:<s:property value="reponse.answer"/><br>
									Forum :
									 <s:property value="forum.titre"/><br>
									 <img src="images/next.png" align=left width="15" height="15">
									<a href="<s:url value="getForumDiscussionByForumId.action"><s:param name="forumId" value="forum.id"/></s:url>">
									 Voir
									 </a>
									 <br><br>
									<i>Discussion de <s:property value="forum.user.pseudo" escape="false"/></i>
								</td>
							</tr>
						</table>
				</s:iterator>
				
				</td>	
				
			</tr>
			<tr>
					<td>
						<h2>Résumé des notifications</h2>
					</td>
					<td>
						<h2>Notifications du WallToWall</h2>
					</td>
			</tr>
			<tr>
				<td>
							
			
<div style="width:300px;height:auto;background-color:#FFFFFF;padding:5px;">
<!--width:300px;height:300px;  -->
  <a name="ex2" id="ex2"></a>
  <ul id="example2">
  <li><a href="#">Discussions Sans Réponse</a>
      <ul>

        <li>
        
        
        <s:iterator value="listDiscussionSansReponse">
						<table>
							<tr>
								<td>
									<b><s:property value="titre"/></b>
									<s:property value="message"/><br>
									Créer le <i><s:property value="createDate"/></i>
									par <b><s:property value="user.pseudo"/></b>
									<br/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<td>
									<img src="images/next.png" align=left width="15" height="15">
									<a href="<s:url value="getForumDiscussionByForumId.action"><s:param name="forumId" value="id"/></s:url>">									 
									 Voir
									 </a>
								</td>
							</tr>
						</table>
						<HR width="100%" size="0.3" color="blue">	
				</s:iterator>
        
        
        
        
		

		</li>
      </ul>
    </li>
  
  
    <li><a href="#">Notifications Forum</a>
      <ul>

        <li>
        
        
        <s:iterator value="nLastNotificationForum">
						<table>
							<tr>
								<td>
									<img src="images/new.png" align=left width="20" height="20">
									<font color="#0099FF">Nouvelle Réponse : De <s:property value="userOrigine.pseudo" escape="false"/></font>		
								
					 			</td>
					 		</tr>
					 		<tr>
					 			<td>
									<img src="images/personal.png" align=left width="25" height="25">
									<font color="#FF9900">
									<b>	le <s:property value="createDate"/> </b>
									</font> 
									Détails de la réponse:<s:property value="reponse.answer"/><br>
									Forum : <s:property value="forum.titre"/><br>
									 <img src="images/next.png" align=left width="15" height="15">
									<a href="<s:url value="getForumDiscussionByForumId.action"><s:param name="forumId" value="forum.id"/></s:url>">									 
									 Voir
									 </a>
									 <br><br>
									<i>Discussion de <s:property value="forum.user.pseudo" escape="false"/></i>
								</td>
							</tr>
						</table>
						<HR width="100%" size="0.3" color="blue">	
				</s:iterator>
        
        
        
        
		

		</li>
      </ul>
    </li>
    <li><a href="#">Notifications WallToWall</a>
      <ul>
        <li>
        
        	<s:iterator value="nLastNotificationWallToWall">
						<img src="images/personal.png" align=left width="25" height="25">
						<font color="#0099FF">
						<s:property value="userOrigine.pseudo"/> 
						</font> 
						a envoyé un message à<br>
						<img src="images/yellowUser.png" align=left width="25" height="25">						
						<font color="#FF9900">
						<s:property value="userDestination.pseudo"/>
						</font> 
						<br><br>
						<img src="images/forum.png" align=left width="25" height="25">
						Réf Forum: <s:property value="forum.message"/>
						<img src="images/next.png" align=left width="15" height="15">
						<a href="<s:url value="getForumDiscussionByForumId.action"><s:param name="forumId" value="forum.id"/></s:url>">
						 Voir
						 </a>
						,le <i><s:property value="createDate"/></i>.
						<HR width="100%" size="0.3" color="blue">	
					</s:iterator>
        
        
        </li>
      </ul>

    </li>
    
    
     <li><a href="#">Notifications Commentaires</a>
      <ul>
        <li>
        
        	<s:iterator value="nLastNotificationCommentaire">
						<img src="images/star.png" align=left width="25" height="25">
						<font color="#0099FF">
						<s:property value="user.pseudo"/> 
						</font> 
						a posté un commentaire
						<img src="images/user_comment.png" align=left width="16" height="16">						
						<br>
						<font color="#FF9900">
						sur le document: <s:property value="document.name"/>
						</font> 
						
						<s:url id="viewCommentaire" action="getSelectedCommentaireByDocumentId">
							<s:param name="commentaireId" value="commentaire.id" />
						</s:url>	
						<img src="images/next.png" align=left width="16" height="16">							
						<s:a href="%{viewCommentaire}">Voir</s:a>
						
						
						<br><br>
						Le <i><s:property value="createDate"/></i>.
						<HR width="100%" size="0.3" color="blue">	
					</s:iterator>
        
        
        </li>
      </ul>

    </li>
    
    
    
    
    <li><a href="#">Derniers Articles</a>
      <ul>
        <li>
        
        	<img src="<s:url value="images/text.png" />" width="16" height="16" alt="text" />
				Textes(<s:property value="textCount"/>)
				<s:iterator value="nlastText">
					<s:url id="viewtext" action="getSearchedDocument">
						<s:param name="docId" value="id" />
						<s:param name="docType" value="'TEXT'" />
					</s:url>
					
					<s:a href="%{viewtext}"> <p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
				</s:iterator>
				<br>
				<img src="<s:url value="images/documents.png" />" width="16" height="16" alt="documents" />						
				Documents(<s:property value="rawfileCount"/>)
				<s:iterator value="nLastDocument">
					<s:url id="viewdoc" action="getSearchedDocument">
						<s:param name="docId" value="id" />
						<s:param name="docType" value="'RAWFILE'" />
					</s:url>
					<s:a href="%{viewdoc}"> <p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
				</s:iterator>
				<br>
				<img src="<s:url value="images/image.png" />" width="16" height="16" alt="images" />	
				Images(<s:property value="imageCount"/>)
				<s:iterator value="nLastImage">
					<s:url id="viewimage" action="getSearchedDocument">
						<s:param name="docId" value="id" />
						<s:param name="docType" value="'IMAGE'" />
					</s:url>				
					<s:a href="%{viewimage}"> <p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
				</s:iterator>
				<br>
				<img src="<s:url value="images/video.png" />" width="16" height="16" alt="videos" />
				Vidéos(<s:property value="videoCount"/>)
				<s:iterator value="nLastVideo">
					<s:url id="viewvideo" action="getSearchedDocument">
						<s:param name="docId" value="id" />
						<s:param name="docType" value="'VIDEO'" />
					</s:url>	
					
					<s:a href="%{viewvideo}"><p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
				</s:iterator>
        </li>
      </ul>
    </li>
    <li><a href="#">Mes derniers Articles</a>

      <ul>
        <li>
        	<s:iterator value="myNlastText">
        		<s:url id="viewtext" action="getSearchedDocument">
						<s:param name="docId" value="id" />
						<s:param name="docType" value="'TEXT'" />
				</s:url>		
				<s:a href="%{viewtext}"> <p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
        	</s:iterator>
        </li>
      </ul>
    </li>
  </ul>
  </div>
					
			
				</td>
				<td>
					
					<s:iterator value="nLastNotificationWallToWall">
						<img src="images/personal.png" align=left width="25" height="25">
						<font color="#0099FF">
						<s:property value="userOrigine.pseudo"/> 
						</font> 
						a envoyé un message à<br>
						<img src="images/yellowUser.png" align=left width="25" height="25">						
						<font color="#FF9900">
						<s:property value="userDestination.pseudo"/>
						</font>
						<a href="<s:url value="getWallToWallByWallToWallId.action"><s:param name="wallToWallId" value="id"/></s:url>">
						 Voir
						</a><img src="images/eye.png" align=left width="16" height="16">						
						
						<br><br>
						<img src="images/forum.png" align=left width="25" height="25">
						Réf Forum: <s:property value="forum.message"/>,le <i><s:property value="createDate"/></i>.<br>
						<img src="images/next.png" align=left width="16" height="16">						
						<a href="<s:url value="getForumDiscussionByForumId.action"><s:param name="forumId" value="forum.id"/></s:url>">
						Voir
						</a>
						<HR width="100%" size="0.3" color="blue">
					</s:iterator>
				</td>
			</tr>
		</table>
			
		</div>
	</body>
</html>

<!--  
				<table>
				<tr>
					<td>
						<h2>News</h2>
					</td>
					
					<td>
						<h2>Notifications Forum</h2>
					</td>
				</tr>
				<tr>				
				<td>
				


		</td>
		
		
		
		<td>
					<s:iterator value="nLastNotificationForum">
					<table>
						<tr>
							<td>
								<img src="js/easynews/bignews.png" align=left width="35" height="35">
								<font color="#0099FF">Nouvelle Réponse : De <s:property value="userOrigine.pseudo" escape="false"/></font>		
								
					 		</td>
					 	</tr>
					 	<tr>
					 		<td>
								<img src="js/easynews/adminIcon.png" align=left width=30 height=30>
								<font color="#FF9900">
								<b>	le <s:property value="createDate"/> </b>
								</font> 
								Détails de la réponse:<s:property value="reponse.answer"/><br>
								Forum : <s:property value="forum.titre"/><br>
								<i>Discussion de <s:property value="forum.user.pseudo" escape="false"/></i>
							</td>
						</tr>
					</table>
				</s:iterator>	
				</td>
		
		</tr>
		</table>
		<HR width="100%" size="0.3" color="blue">				
			
	-->		
		
	
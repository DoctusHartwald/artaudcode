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
         <script type="text/javascript" src="js/easynews/jquery-1.2.3.pack.js"></script>
		<script type="text/javascript" src="js/easynews/jquery.easynews.js"></script>
		<script type="text/javascript" src="js/notation.js"></script>
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
        	<div class="pres_titre">Liste de documents filtrés</div>
        	
        	<s:set name="nbDocumentNonVote" value="0"/>
			
			<s:iterator value="allDocToShowAfterFilter" id="d">
				<s:url id="view" action="getSearchedDocument">
					<s:param name="docId" value="id" />
					<s:param name="docType" value="type" />
				</s:url>
			
				<s:url id="note1" action="noteArticle">
					<s:param name="docId" value="id" />
					<s:param name="nouvelleNote" value="1" />
					<s:param name="docType" value="type" />
				</s:url>
				<s:url id="note2" action="noteArticle">
					<s:param name="docId" value="id" />
					<s:param name="nouvelleNote" value="2" />
					<s:param name="docType" value="type" />
				</s:url>
				<s:url id="note3" action="noteArticle">
					<s:param name="docId" value="id" />
					<s:param name="nouvelleNote" value="3" />
					<s:param name="docType" value="type" />
				</s:url>
				<s:url id="note4" action="noteArticle">
					<s:param name="docId" value="id" />
					<s:param name="nouvelleNote" value="4" />
					<s:param name="docType" value="type" />
				</s:url>
				<s:url id="note5" action="noteArticle">
					<s:param name="docId" value="id" />
					<s:param name="nouvelleNote" value="5" />
					<s:param name="docType" value="type" />
				</s:url>
				
				<s:set name="etoileN" value="1" />
				<s:if test="#session.userId">
					<s:set name="userAVote" value="%{false}"/>
					<s:iterator value="userWhoHasVoted">
						<s:if test="getId() == #session.userId">
							<s:set name="userAVote" value="%{true}"/>
						</s:if>
					</s:iterator>
				</s:if>
				<s:else>
					<s:set name="userAVote" value="%{true}"/>
				</s:else>
				
				<s:if test="!#userAVote">
					<s:set name="nbDocumentNonVote" value="#nbDocumentNonVote+1"/>
				</s:if>
				
				Note de l'article:
				<s:set name="meanDuDoc" value="mean" />
				<%
					Double meanDuDocCourant = Double.parseDouble(pageContext.getAttribute("meanDuDoc").toString());
					for(double i=0.5;i<5;i+=1)
						if(i < meanDuDocCourant){
				%>
					<s:if test="!#userAVote">
						<s:if test="#etoileN == 1">
					    	<s:a href="%{note1}"><img id="Document${nbDocumentNonVote}Star1" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:if>
					    <s:elseif test="#etoileN == 2">
					    	<s:a href="%{note2}"><img id="Document${nbDocumentNonVote}Star2" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 3">
					    	<s:a href="%{note3}"><img id="Document${nbDocumentNonVote}Star3" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 4">
					    	<s:a href="%{note4}"><img id="Document${nbDocumentNonVote}Star4" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 5">
					    	<s:a href="%{note5}"><img id="Document${nbDocumentNonVote}Star5" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:set name="etoileN" value="#etoileN +1" />
					</s:if>
					<s:else>
						<img src="/Voleo/css/images/SunOver.gif" />
					</s:else>
				<%
						}else{
				%>
					<s:if test="!#userAVote">
						<s:if test="#etoileN == 1">
					    	<s:a href="%{note1}"><img id="Document${nbDocumentNonVote}Star1" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:if>
					    <s:elseif test="#etoileN == 2">
					    	<s:a href="%{note2}"><img id="Document${nbDocumentNonVote}Star2" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 3">
					    	<s:a href="%{note3}"><img id="Document${nbDocumentNonVote}Star3" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 4">
					    	<s:a href="%{note4}"><img id="Document${nbDocumentNonVote}Star4" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 5">
					    	<s:a href="%{note5}"><img id="Document${nbDocumentNonVote}Star5" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:set name="etoileN" value="#etoileN +1" />
					</s:if>
					<s:else>
						<img src="/Voleo/css/images/SunOut.gif" />
					</s:else>
				<%
						}
				%>
		
				[<s:property value="nbVote"/> Vote(s)]<br>
				
				Titre : <s:property value="name" /><br />
				Propriétaire : <s:property value="user.pseudo" /><br />
				Date de création : <s:property value="createDate" /><br />
				Note : <s:property value="mean" /> (<s:property value="nbVote" /> votes)<br />
				Tags: 	
				<s:iterator value="tags">
					<s:property value="name" />, 
			
				</s:iterator>
				<br>
				<img src="<s:url value="images/next.png" />" width="16" height="16" alt="next" />
				<s:a href="%{view}"> Regarder </s:a><br>
				<hr />
			</s:iterator>
			<script type="text/javascript">
				NbDoc = <s:property value="#nbDocumentNonVote" />;
				NotationSystem()
			</script>	
        </div>
    </body>
</html>
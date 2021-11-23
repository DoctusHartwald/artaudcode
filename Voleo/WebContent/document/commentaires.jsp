
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
		<script language="javaScript" type="text/javascript" src="js/notation.js"></script>
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
			<img src="<s:url value="images/user_comment.png" />" width="16"
					height="16" alt="add contact" />Commentaires(<s:property value="commentaireCount"/>)

			<img src="<s:url value="images/comment_add.png" />" width="16" height="16" alt="add contact" />
			<s:url id="addCommentaire" action="addCommentaireForm" />
			<s:a href="%{addCommentaire}">Ajouter un commentaire</s:a>
		
<br><br>
			<s:set name="nbCommentaireNonVote" value="0"/>
			<s:iterator status="stat" value="commentaire">
			
				<s:set name="id" value="getId()"/>
				<s:url id="note1" action="noteCommentaire">
					<s:param name="commentaireId" value="#id" />
					<s:param name="nouvelleNote" value="1" />
				</s:url>
				<s:url id="note2" action="noteCommentaire">
					<s:param name="commentaireId" value="#id" />
					<s:param name="nouvelleNote" value="2" />
				</s:url>
				<s:url id="note3" action="noteCommentaire">
					<s:param name="commentaireId" value="#id" />
					<s:param name="nouvelleNote" value="3" />
				</s:url>
				<s:url id="note4" action="noteCommentaire">
					<s:param name="commentaireId" value="#id" />
					<s:param name="nouvelleNote" value="4" />
				</s:url>
				<s:url id="note5" action="noteCommentaire">
					<s:param name="commentaireId" value="#id" />
					<s:param name="nouvelleNote" value="5" />
				</s:url>
			
				<s:property value="#stat.index" />)
				
				Posté par <i><s:property value="user.pseudo" /> </i>à 
				<s:property value="createDate" escape="false"/><br>
				
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
					<s:set name="nbCommentaireNonVote" value="#nbCommentaireNonVote+1"/>
				</s:if>
				<s:set name="meanDuCom" value="mean" />
				Note :
				<%
					Double meanDuComCourant = Double.parseDouble(pageContext.getAttribute("meanDuCom").toString());
					for(double i=0.5;i<5;i+=1)
						if(i < meanDuComCourant){
				%>
					<s:if test="!#userAVote">
						<s:if test="#etoileN == 1">
					    	<s:a href="%{note1}"><img id="Document${nbCommentaireNonVote}Star1" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:if>
					    <s:elseif test="#etoileN == 2">
					    	<s:a href="%{note2}"><img id="Document${nbCommentaireNonVote}Star2" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 3">
					    	<s:a href="%{note3}"><img id="Document${nbCommentaireNonVote}Star3" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 4">
					    	<s:a href="%{note4}"><img id="Document${nbCommentaireNonVote}Star4" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 5">
					    	<s:a href="%{note5}"><img id="Document${nbCommentaireNonVote}Star5" src="/Voleo/css/images/SunOver.gif" /></s:a>
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
					    	<s:a href="%{note1}"><img id="Document${nbCommentaireNonVote}Star1" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:if>
					    <s:elseif test="#etoileN == 2">
					    	<s:a href="%{note2}"><img id="Document${nbCommentaireNonVote}Star2" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 3">
					    	<s:a href="%{note3}"><img id="Document${nbCommentaireNonVote}Star3" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 4">
					    	<s:a href="%{note4}"><img id="Document${nbCommentaireNonVote}Star4" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 5">
					    	<s:a href="%{note5}"><img id="Document${nbCommentaireNonVote}Star5" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:set name="etoileN" value="#etoileN +1" />
					</s:if>
					<s:else>
						<img src="/Voleo/css/images/SunOut.gif" />
					</s:else>
				<%
						}
				%>
					( <s:property value="nbVote"/> Vote(s) )
				<br />
				<s:property value="textcommentaire" escape="false"/><br>	
				<HR width="100%" size="0.3" color="blue">
			</s:iterator>	
  			<script type="text/javascript">
				NbDoc = <s:property value="#nbCommentaireNonVote" />;
				NotationSystem();
			</script>
					
	
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
<title>Liste Documents</title>
</head>
<body>
<div id="header">header</div>
	<div class="content">
	<div id="titre"><a name="ObjectifduSite"></a>
	<h2>
		<s:if test="docType.equals('TEXT')">
			Mes Textes
		</s:if>
		<s:if test="docType.equals('VIDEO')">
			Mes Vidéos
		</s:if>
		<s:if test="docType.equals('IMAGE')">
			Mes Images
		</s:if>
		<s:if test="docType.equals('RAWFILE')">
			Mes Documents
		</s:if>
	</h2>
		<fieldset><legend align=top> 	
			<s:if test="docType.equals('TEXT')">
				Mes Textes
			</s:if>
			<s:if test="docType.equals('VIDEO')">
				Mes Vidéos
			</s:if>
			<s:if test="docType.equals('IMAGE')">
				Mes Images
			</s:if>
			<s:if test="docType.equals('RAWFILE')">
				Mes Documents
			</s:if> 
		</legend>

		<s:iterator value="documentList"><br>
			<s:property value="name" /> 
			<s:property value="text" />			
			
			<s:url id="update" action="updateDocumentForm">
				<s:param name="docId" value="id" />
			</s:url>
			<s:a href="%{update}"> Modifier </s:a>
	
			<s:url id="delete" action="removeDocument">
				<s:param name="docId" value="id" />		
			</s:url>
			<s:a href="%{delete}"> Supprimer </s:a> 
			<br />
		</s:iterator>	
<br />
		<s:url id="add" action="addDocumentForm" />
		<s:if test="docType.equals('TEXT')">
			<s:a href="%{add}">Ajouter un texte</s:a>
		</s:if>
		<s:if test="docType.equals('VIDEO')">
			<s:a href="%{add}">Ajouter une video</s:a>
		</s:if>
		<s:if test="docType.equals('IMAGE')">
			<s:a href="%{add}">Ajouter une image</s:a>
		</s:if>	
		<s:if test="docType.equals('RAWFILE')">
			<s:a href="%{add}">Ajouter un document</s:a>
		</s:if>
	</fieldset>
<br><br><br>

   	<fieldset><legend align=top> Mes Documents récents: </legend>
   	<br>
	   
	   <s:iterator value="lastNMyDocuments"><br>
			<s:property value="id" /> <br>
			<s:property value="text" /> <br>
	   </s:iterator>
	</fieldset>
<br><br>
Mes documents validés:<br>
<s:iterator value="allValidated">
		<s:property value="name" /> 
		<s:property value="status" /> 
		<br>
</s:iterator>
<br>

Mes documents Pending:<br>
<s:iterator value="allPending">
		<s:property value="name" /> 
		<s:property value="status" /> 
	
		<br>
</s:iterator>
<br>
</div>
</div>
</body>
</html>

-->
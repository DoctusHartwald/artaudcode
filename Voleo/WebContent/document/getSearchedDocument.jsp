<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Voleo</title>
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />
		<s:head theme="ajax" debug="false" />	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
		<script language="javaScript" type="text/javascript" src="js/whizzywig.js"></script>
		<script language="javaScript" type="text/javascript" src="js/notation.js"></script>
		<script src="http://www.scribd.com/javascripts/view.js"></script>
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
			<h1>Le document Sélectionné</h1>
			<br>
			
			<s:set name="nbDocumentNonVote" value="0"/>
			<s:url id="note1" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="1" />
			</s:url>
			<s:url id="note2" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="2" />
			</s:url>
			<s:url id="note3" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="3" />
			</s:url>
			<s:url id="note4" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="4" />
			</s:url>
			<s:url id="note5" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="5" />
			</s:url>

			<s:iterator value="searchDocument">
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
				<s:set name="meanDuDoc" value="mean" />
				Note :
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
					( <s:property value="nbVote"/> Vote(s) )
				<br />
				<i> Nom du document :</i><s:property value="name" /> 
				 <br>
				<s:if test="docType.equals('TEXT')">
					<s:property value="text" escape="false"/> 				
				</s:if>
				<s:if test="docType.equals('VIDEO')">
				</s:if>
				<s:if test="docType.equals('IMAGE')">
				</s:if>
				<s:if test="docType.equals('RAWFILE')">
					<i>Description du document:</i> <s:property value="description" escape="false"/><br><br>
					<br>
					<div id='embedded_doc'></div>
						<script type="text/javascript">
			 		   		var scribd_doc = scribd.Document.getDocFromUrl('<s:property value="url" />', 'pub-19984416605044210160');
			    			scribd_doc.addParam("height", 500);
			    			scribd_doc.addParam("width", 500);
							scribd_doc.addParam("public", false);
			    			scribd_doc.write('embedded_doc');
						</script>	
					<br>
				</s:if>
				<br>

				<s:url id="comments" action="getCommentaireByDocumentId">
					<s:param name="docId" value="id" />
					<s:param name="docType" value="docType"/>
				</s:url>
		
				<img src="<s:url value="images/user_comment.png" />" width="16"
					height="16" alt="add contact" />
	
				<s:a href="%{comments}">Commentaires(<s:property value="countCommentaire"/>)</s:a>
		
		
				<s:url id="addcomments" action="addCommentaireForm">
					<s:param name="docId" value="id" />
					<s:param name="docType" value="docType"/>
				</s:url>
				<img src="<s:url value="images/comment_add.png" />" width="16"
					height="16" alt="add contact" />
				<s:a href="%{addcomments}">Ajout Commentaires</s:a>

				<s:url id="modifier" action="updateDocumentForm.action">
					<s:param name="docId" value="id" />
					<s:param name="docType" value="documentType" />
				</s:url>
				<img src="<s:url value="images/desktop.png" />" width="16" height="16" alt="change" />
				<s:a href="%{modifier}">Modifier</s:a>

				<HR width="100%" size="0.3" color="blue">
				
				
				
				
				
			</s:iterator>
			<script type="text/javascript">
				NbDoc = <s:property value="#nbDocumentNonVote" />;
				NotationSystem()
			</script>	
			<img src="<s:url value="images/retour.gif" />" width="16" height="16" alt="retour" /><s:a href="searchForm.action">Autre recherche</s:a>	
			
		</div>
	</body>
</html>






<!--  
<html>
	<head>
		<title>Voleo</title>
		<link rel="stylesheet" type="text/css" href="css/voleo.css" />	
		<link rel="stylesheet" type="text/css" href="css/cadre_perso.css" />	
		<script language="javaScript" type="text/javascript" src="js/voleo.js"></script>
		<script src="http://www.scribd.com/javascripts/view.js"></script>
		<s:head theme="ajax" debug="false" />	
		<script language="javaScript" type="text/javascript" src="js/notation.js"></script>
		
	</head>
	<body>
	
		<div id="logo"></div>
        <div id="perso">
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
			<div class="rechercher">
				<input type="text" size="16">
				<input type="button" value="trouver">
			</div>
			<div class="pres_titre">Liste des tags</div>
			<div class="liste_tags">voiture<br>bateau<br>physique<br>chimie<br>electronique<br>informatique<br>couture<br>peinture sur glace<br>helicoptère</div>
		</div>
		
		<div id="droite">
			<div class="pres_barre_verticale_gauche"></div>
			<div class="pres_barre_verticale_droite"></div>
			<div class="pres_titre">Mon espace Perso</div>
				<div class="liste_derniers_articles">
					<p class="text_liste_derniers_articles">
						<s:a href="listContacts.action">Mes contacts</s:a><br>
						<s:a href="listTypes.action">Mes documents</s:a><br>
						Mon Compte<br>
					</p>
			</div>
			<div class="pres_titre">Mes Derniers Articles</div>
				
				
   	<br>
	   
	   
				
			<div class="liste_derniers_articles">
				<img src="css/logo.png" width="100px"></img>
				<s:iterator value="lastNMyDocuments">
				<p class="text_liste_derniers_articles">
					<s:property value="name" /> 
				</p>
				</s:iterator><br>
			</div>
	
			
		</div>
		<div id="contenu">
			<div class="pres_titre">Mon espace Perso</div>
			<h1>Le document Sélectionné</h1>
  			<s:a href="searchForm.action" > Retour Recherche</s:a>

	<script type="text/javascript">
				NbDoc = <s:property value="#nbDocumentNonVote" />;
				NotationSystem()
	</script>
				
<s:set name="nbDocumentNonVote" value="0"/>
			<s:url id="note1" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="1" />
			</s:url>
			<s:url id="note2" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="2" />
			</s:url>
			<s:url id="note3" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="3" />
			</s:url>
			<s:url id="note4" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="4" />
			</s:url>
			<s:url id="note5" action="noteArticle">
				<s:param name="docId" value="id" />
				<s:param name="nouvelleNote" value="5" />
			</s:url>





			<s:iterator status="stat" value="searchDocument">
				<s:property value="#stat.index" />.<br/>
				
				
				<s:set name="userAVote" value="%{false}"/>
				<s:set name="etoileN" value="1" />
				<s:iterator value="userWhoHasVoted">
					<s:if test="getId() == #session.userId">
						<s:set name="userAVote" value="%{true}"/>
					</s:if>
				</s:iterator>
				
				<s:if test="!#userAVote">
					<s:set name="nbDocumentNonVote" value="#nbDocumentNonVote+1"/>
				</s:if>
				<s:set name="meanDuDoc" value="mean" />
				Note :
			
					<s:if test="!#userAVote">
						<s:if test="#etoileN == 1">
					    	<s:a href="%{note1}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star1" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:if>
					    <s:elseif test="#etoileN == 2">
					    	<s:a href="%{note2}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star2" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 3">
					    	<s:a href="%{note3}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star3" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 4">
					    	<s:a href="%{note4}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star4" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 5">
					    	<s:a href="%{note5}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star5" src="/Voleo/css/images/SunOver.gif" /></s:a>
					    </s:elseif>
					    <s:set name="etoileN" value="#etoileN +1" />
					</s:if>
					<s:else>
						<img src="/Voleo/css/images/SunOver.gif" />
					</s:else>
			
			
			
			
			
					<s:if test="!#userAVote">
						<s:if test="#etoileN == 1">
					    	<s:a href="%{note1}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star1" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:if>
					    <s:elseif test="#etoileN == 2">
					    	<s:a href="%{note2}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star2" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 3">
					    	<s:a href="%{note3}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star3" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 4">
					    	<s:a href="%{note4}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star4" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:elseif test="#etoileN == 5">
					    	<s:a href="%{note5}" theme="ajax" targets="contenu" ><img id="Document${nbDocumentNonVote}Star5" src="/Voleo/css/images/SunOut.gif" /></s:a>
					    </s:elseif>
					    <s:set name="etoileN" value="#etoileN +1" />
					</s:if>
					<s:else>
						<img src="/Voleo/css/images/SunOut.gif" />
					</s:else>
			
				<br />
				
				
				<i> Nom du document :</i><s:property value="name" /> 
				 <br>
				<s:if test="docType.equals('TEXT')">
					<s:property value="text" escape="false"/> 				
				</s:if>
				<s:if test="docType.equals('VIDEO')">
				</s:if>
				<s:if test="docType.equals('IMAGE')">
				</s:if>
				<s:if test="docType.equals('RAWFILE')">
					<i>Description du document:</i> <s:property value="description" escape="false"/><br><br>
					<br>
					<div id='embedded_doc'></div>
						<script type="text/javascript">
			 		   		var scribd_doc = scribd.Document.getDocFromUrl('<s:property value="url" />', 'pub-19984416605044210160');
			    			scribd_doc.addParam("height", 500);
			    			scribd_doc.addParam("width", 500);
							scribd_doc.addParam("public", false);
			    			scribd_doc.write('embedded_doc');
						</script>	
					<br>
				</s:if>
				<br>
		<s:url id="comments" action="getCommentaireByDocumentId">
			<s:param name="docId" value="id" />
			<s:param name="docType" value="docType"/>
		</s:url>
		<img src="<s:url value="images/user_comment.png" />" width="16"
					height="16" alt="add contact" />
		<s:a href="%{comments}">Commentaires</s:a>(<s:property value="commentaireCount" />)
		
		
		<s:url id="addcomments" action="addCommentaireForm">
			<s:param name="docId" value="id" />
			<s:param name="docType" value="docType"/>
		</s:url>
		<img src="<s:url value="images/comment_add.png" />" width="16"
					height="16" alt="add contact" />
		<s:a href="%{addcomments}">Ajout Commentaires</s:a>
		<br />

		<HR width="100%" size="0.3" color="blue">
	</s:iterator>	
			
		
			
			
		</div>
	</body>
</html> -->










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
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<p class="text_liste_derniers_articles">
	<s:if test="#session.userId">
	    <s:a href="listContacts.action">Mes contacts (<s:property value="contactCount"/>)</s:a><br>
	    <s:a href="listTypes.action">Mes documents (<s:property value="docCount"/>)</s:a><br>
	    <s:a href="listPendingTypes.action">Mes modifications en attente (<s:property value="docPendingCount"/>)</s:a><br>
	    <s:a href="getHistoriqueByUser.action">Historique (<s:property value="historiqueCount"/>)</s:a><br>
		<s:a href="myDiscussions.action">Mes Discussions</s:a><br>
		<s:a href="receivedWallToWall.action">Mon Wall</s:a><br>
    <br /></s:if>
    <s:a href="listDiscussion.action">Forum(<s:property value="newForumCount"/>)</s:a><br/><br/>
    <b>Statistiques</b><br>
    <s:if test="forumCount > 1" >
    	<s:property value="forumCount"/> Discussions<br>
    </s:if>
    <s:else>
    	<s:property value="forumCount"/> Discussion<br>
    </s:else>
    <s:if test="userCount > 1">
        <s:property value="userCount"/> Utilisateurs<br>
    </s:if>
    <s:else>
        <s:property value="userCount"/> Utilisateur<br>
    </s:else>
    <s:if test="adminCount > 1">
        <s:property value="adminCount"/> Admins<br>
    </s:if>
    <s:else>
        <s:property value="adminCount"/> Admin<br>
    </s:else>
</p>
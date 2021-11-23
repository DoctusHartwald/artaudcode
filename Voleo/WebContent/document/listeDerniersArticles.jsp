<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<ul style="margin:0px;padding:0px">
	<li>
		<img src="<s:url value="images/text.png" />" width="16" height="16" alt="text" />
		Textes(<s:property value="textCountLatest"/>)
	</li>
	<s:iterator value="nlastText">
	    <s:url id="viewtext" action="getSearchedDocument">
	        <s:param name="docId" value="id" />
	        <s:param name="docType" value="'TEXT'" />
	    </s:url>   
	    <s:a href="%{viewtext}"> <p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
	</s:iterator>
	
	<li>
		<img src="<s:url value="images/documents.png" />" width="16" height="16" alt="documents" />
		Documents(<s:property value="rawfileCountLatest"/>)
	</li>
	<s:iterator value="nLastDocument">
	    <s:url id="viewdoc" action="getSearchedDocument">
	        <s:param name="docId" value="id" />
	        <s:param name="docType" value="'RAWFILE'" />
	    </s:url>
	    <s:a href="%{viewdoc}"> <p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
	</s:iterator>
	
	<li>
		<img src="<s:url value="images/image.png" />" width="16" height="16" alt="images" />   
		Images(<s:property value="imageCountLatest"/>)
	</li>
	<s:iterator value="nLastImage">
	    <s:url id="viewimage" action="getSearchedDocument">
	        <s:param name="docId" value="id" />
	        <s:param name="docType" value="'IMAGE'" />
	    </s:url>               
	    <s:a href="%{viewimage}"> <p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
	</s:iterator>
	
	<li>
		<img src="<s:url value="images/video.png" />" width="16" height="16" alt="videos" />
		Vidéos(<s:property value="videoCountLatest"/>)
	</li>
	<s:iterator value="nLastVideo">
	    <s:url id="viewvideo" action="getSearchedDocument">
	        <s:param name="docId" value="id" />
	        <s:param name="docType" value="'VIDEO'" />
	    </s:url>      
	    <s:a href="%{viewvideo}"><p class="text_liste_derniers_articles"><s:property value="name" /></p></s:a>
	</s:iterator>
</ul>
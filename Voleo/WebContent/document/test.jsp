<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>index</title>
</head>
<body>

 <fieldset><legend align=top> Documents Récents </legend>
	    <s:iterator value="nlastText"><br>
	  	   <s:property value="name"/>
	 	   <s:property value="text"/><br>
	    </s:iterator>
	    </fieldset>


<div id="div1">
	<s:if test="tags.size > 0">
		<s:iterator value="tags">
			<s:property value="name" /><br />
		</s:iterator>
	</s:if>
</div>

<div id="div2">
<s:iterator value="text">
 	 		text is: <s:property value="text" />
</s:iterator>
		
</div>

<s:form id="tagForm" action="addNewText">
	<s:textfield id="tagName" label="Nom du tag" name="newTagName"/><br />
	<s:textfield id="documentName" label="Nom du document" name="newDocumentName"/><br />
	<s:textfield id="documenttext" label="Texte du document" name="newDocumentText"/><br />	
		
<s:submit label="Soumettre" value="Soumettre"/>
</s:form>

<br><br>

<s:form id="updateTextForm" action="updateText">
	<s:textfield id="documentName" label="Nom du document" name="documentName"/><br />	
	<s:textfield id="documentText" label="Text du document" name="documentText"/><br />
<s:submit label="Ok"/>
</s:form>

<!--  <iframe src="http://imageshack.us/iframe.php?txtcolor=111111&type=blank&size=30" scrolling="no" allowtransparency="true" frameborder="0" width="280" height="70">Update your browser for ImageShack.us!</iframe> -->

</body>
</html>
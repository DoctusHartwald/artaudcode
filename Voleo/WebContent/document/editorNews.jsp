<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page pageEncoding="UTF-8"%>
<html>
<head>
	<script language="javaScript" type="text/javascript" src="js/francais.js"></script>
	<script language="javaScript" type="text/javascript" src="js/whizzywig.js"></script>
</head>
<body>
	<form id="editForm" method="post" action="addText.action">
		<input type="hidden" name="newsId" />
		Titre: <input type="text" value="<s:property value="title" />" name="title" />
		<textarea id="editedText" name="editedText" style="width:100%; height:300px">
				<s:property value="editedText" escape="false" />
		</textarea>
		<script type="text/javascript">
		buttonPath = "js/btn/"; //directory holding button images
		cssFile = "css/editor.css";
		makeWhizzyWig("editedText", "formatblock newline bold italic underline | left center right | number bullet indent outdent | undo redo | rule | link image table");
		</script>
		<input type="submit" value="Soumettre">
	</form>
</body>
</html>
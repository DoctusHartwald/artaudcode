<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>

Tags sélectionnés: 
<s:if test="allTagsSelected.size > 0">
	<s:url action="listDocumentFiltered" id="listDocumentFiltered">
	</s:url>
	<s:a href="%{listDocumentFiltered}">
		<img src="/Voleo/images/magnifier.png" alt="filtrer" />
	</s:a>
	<s:iterator value="allTagsSelected">
		<s:set name="aTag" value="toString()" />
		<s:url action="removeSelectedTag" id="removeSelectedTag">
			<s:param name="tagsSelected" value="#aTag" />
		</s:url>
		<s:a href="%{removeSelectedTag}">
			<s:property value="#aTag" />
		</s:a>&nbsp;
	</s:iterator>
</s:if>
<s:else>
	&nbsp;
</s:else>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>

<s:iterator value="tagsCount">
	<s:url id="addSelectedTag" action="addSelectedTag" >
		<s:param name="tagsSelected" value="name" />
	</s:url>
	<s:a href="%{addSelectedTag}">
		<s:property value="name" /> 
		(<s:property value="documentsCount" />)
	</s:a><br />
</s:iterator>
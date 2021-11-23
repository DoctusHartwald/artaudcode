<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<s:if test="tags.size > 0">
		<s:iterator value="tags">
			<s:property value="name" /><br />
		</s:iterator>
	</s:if>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
<s:iterator value="contacts">	
				<s:property value="firstname" /> 
					<s:property value="lastname" /> 
					<s:property value="email" /> 
					<s:property value="fixPhone" /> 
					<s:property value="mobilePhone" />
					</s:iterator>
					
</body>
</html>
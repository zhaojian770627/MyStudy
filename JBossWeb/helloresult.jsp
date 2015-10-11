<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id="helloBean" class="mypack.HelloBean"
	scope="session">
	<jsp:setProperty name="helloBean" property="*" />
</jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>helloapp</title>
</head>
<body>
	<b>id:<jsp:getProperty name="helloBean" property="id" /></b>
	<b>name:<jsp:getProperty name="helloBean" property="name" /></b>
</body>
</html>
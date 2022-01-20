<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
</head>
<body>
<h1>This is Home Page.</h1>
<h2>Register succesfully</h2>
	<%
	  response.setHeader("Cache-Control", "no-store");
	String message = (String) session.getAttribute("message");
	if (message != null) {
	%><h5 class="text-primary">
		<%out.println(message);%>
	</h5>
	<%
	}
	session.removeAttribute("message");
	%>
	<p>
		Now please continue to <a href="Login.jsp">Login
	</p>
</body>
</html>
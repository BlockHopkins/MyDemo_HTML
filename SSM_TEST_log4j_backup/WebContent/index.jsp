<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>index page</p>
	<p>userid : <%=request.getParameter("userid") %></p><!-- 和hello.jsp同一个httpRequest，可以getParameter获得hello.jsp页面传进来的userid -->
	<p>user : <%=request.getAttribute("user") %></p><!-- 通过getAttribute获取Controller放在ModelMap中的参数 -->
	<p>username : <%=request.getAttribute("username") %></p>
	<p>age:<%=request.getAttribute("age") %></p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<% 
	pageContext.setAttribute("pageId", "my pageContext value");
	request.setAttribute("requestId", "my request value");
	session.setAttribute("sessionId", " my session value");
	application.setAttribute("applicationId", "my application value");

%>

<h1>INDEX</h1>
pageId :<%=pageContext.getAttribute("pageId") %><br>
request :<%=request.getAttribute("requestId") %><br>
sessionId :<%=session.getAttribute("sessionId") %><br>
application :<%=application.getAttribute("applicationId") %> <br>

<a href="result.jsp">result</a>

<a href="scope.do?mytest=1">test</a>

<form action="scope.do" method="post">
	<input type="hidden" name="myRequest" value="my request value 2">
	<input type="submit" value="controller">
</form>
</body>
</html>
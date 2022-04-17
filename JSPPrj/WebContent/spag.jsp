<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	pageContext.setAttribute("result", "hello");
%>
<body>

	
	${requestScope.result}<br>
	${names[1]}<br>
	${map.id}<br>
	${result}<br>
	${param.no/2}<br>
	${header.accept}<br>
	
</body>
</html>
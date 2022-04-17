<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
인풋태그로 넣어서 보내기 
<form action="myinsertres.jsp" method="post">
작성자<input type="text" name ="writer">
제목<input type="text" name ="title">
내용<input type="text" name ="content">

<input type ="submit" value="글작성" >

</form>

</body>
</html>
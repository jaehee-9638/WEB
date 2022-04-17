
<%@page import="com.myboard.dao.MyBoardDao"%>
<%@page import="com.myboard.dto.MyBoardDto"%>
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
<%
int seq = Integer.parseInt(request.getParameter("seq"));

MyBoardDao dao =new MyBoardDao();
MyBoardDto dto =dao.selectOne(seq);
%>
<form action="updateres.jsp" method="post">
<input type="hidden" name ="seq" value ="<%=dto.getSeq() %>">
	글번호<%=dto.getSeq() %><br/>
	작성자<%=dto.getWriter() %><br/>
	제목<input type="text" name ="title" value ="<%=dto.getTitle() %>"><br/>
	내용<input type="text" name ="content" value ="<%=dto.getContent() %>"><br/>
	작성일<%=dto.getRegdate() %><br/>
	<input type="submit" value="수정" >
</form>
</body>
</html>
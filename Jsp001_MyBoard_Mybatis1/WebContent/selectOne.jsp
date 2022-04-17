<%@page import="com.myboard.dao.MyBoardDao"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
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
	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = dao.selectOne(seq);
	
%>

	글번호 <%=dto.getSeq() %><br/>
	작성자 <%=dto.getWriter() %><br/>
	제목 <%=dto.getTitle() %><br/>
	내용 <%=dto.getContent() %><br/>

	<input onclick="location.href='update.jsp?seq=<%=dto.getSeq() %>'" type="button" value ="글수정">
	<input type ="button" value="삭제" onclick="location.href='delete.jsp?seq=<%=dto.getSeq() %>'">
</body>
</html>
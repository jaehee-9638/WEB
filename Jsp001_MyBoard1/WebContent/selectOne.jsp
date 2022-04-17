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
	int seq =Integer.parseInt(request.getParameter("seq"));	//왜 0이 들어올까...
	
	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = new MyBoardDto();
	dto=dao.selectOne(seq);
%>
<%=dto.getSeq()%>
<%=dto.getWriter() %>
<%=dto.getTitle() %>
<%=dto.getContent() %>
<%=dto.getRegdate() %>

<input type="button" onclick="location.href='update.jsp?seq=<%=dto.getSeq()%>'" value="수정">
<input type="button" onclick="location.href='delete.jsp?seq=<%=dto.getSeq()%>'" value="삭제">
</body>
</html>
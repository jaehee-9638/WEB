<%@page import="com.myboard.dao.MyBoardDao"%>
<%@page import="com.myboard.dto.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text.html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int seq=Integer.parseInt(request.getParameter("seq"));
	MyBoardDao dao=new MyBoardDao();
	MyBoardDto dto= dao.selectOne(seq);
%>
<h1>selectOne</h1>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td>
				<input type="button" value="목록" onclick="">
				<input type="button" value="수정" onclick="location.href='myupdate.jsp?seq=<%=dto.getSeq()%>'">
				<input type="button" value="삭제" onclick="location.href='mydelete.jsp?seq=<%=dto.getSeq()%>'">
			</td>
		</tr>
	</table>


</body>
</html>
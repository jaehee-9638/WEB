<%@page import="com.myboard.dto.MyBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.myboard.dao.MyBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<%	response.setContentType("text.html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
	MyBoardDao dao=new MyBoardDao();
	List<MyBoardDto> list=dao.selectList();
%>
	<h1>게시판 전체보기</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
	for (MyBoardDto dto:list){
%>		
		
		<tr>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><a href="myselect.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRegdate() %></td>
		</tr>
		
<%
	}
%>		
		<tr>
			<td colspan="4">
				<input type="button" value="글작성" onclick="location.href='myinsert.jsp'">
			</td>
		</tr>
	</table>
</body>
</html>
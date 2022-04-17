
<%@page import="java.util.List"%>
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
	//MyBoardDto dto =new MyBoardDto();
	MyBoardDao dao =new MyBoardDao();
	List<MyBoardDto> list =dao.selectList();
	
	%>
	<table border="1">
		<thead>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>
				
			</tr>
		</thead>
	<%
	for (MyBoardDto dto :list){
	%>
		<tbody>
			<tr>
				<td><%=dto.getSeq() %></td>
				<td><a href="selectOne.jsp?seq=<%=dto.getSeq() %>"><%=dto.getWriter() %></a></td>
				<td><%=dto.getTitle() %></td>
				<td><%=dto.getContent() %></td>
				<td><%=dto.getRegdate() %></td>
				
			</tr>
		</tbody>
	
	
	<%
	}
	%>
	</table>
	<input type="button" value ="글작성" onclick="location.href='myinsert.jsp'">
	
	
</body>
</html>
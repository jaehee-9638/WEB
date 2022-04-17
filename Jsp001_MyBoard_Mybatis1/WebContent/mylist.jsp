
<%@page import="com.myboard.dto.MyBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.myboard.dao.MyBoardDao"%>
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
	MyBoardDao dao = new MyBoardDao();
	List <MyBoardDto> list = dao.selectList();
%>

	<table>
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
				<td><%=dto.getWriter() %></td>
				<td><a href="selectOne.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
				<td><%=dto.getContent() %></td>
				<td><%=dto.getRegdate() %></td>
			</tr>
		</tbody>
<%
	}
%>
	</table>
	<div>
	<button onclick="location.href='insert.jsp'" >글작성</button>
	</div>
</body>
</html>
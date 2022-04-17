<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MVCBoardDto dto = (MVCBoardDto) request.getAttribute("dto");
%>
<table>
	<tr>
		<th>글번호</th>
		<td><%=dto.getSeq() %></td>
	</tr>
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
		<td><%=dto.getContent() %></td>
	</tr>
	<tr>
		<th>작성일</th>
		<td><%=dto.getRegdate() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type ="button" value ="수정" onclick="location.href='myController.jsp?command=update&seq=<%=dto.getSeq() %>'">
			<input type ="button" value ="삭제" onclick="location.href='myController.jsp?command=delete&seq=<%=dto.getSeq() %>'">
		</td>
	</tr>
</table>

</body>
</html>
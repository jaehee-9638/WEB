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

MVCBoardDto dto = (MVCBoardDto)request.getAttribute("dto");

%>

<form action="myservlet.do" method="post">
<input type ="hidden" name ="command" value ="updateform">
<input type ="hidden" name ="seq" value="<%=dto.getSeq() %>">	
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
			<th>글제목</th>
			<td><input type ="text" name ="title" value ="<%=dto.getTitle()%>"></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><input type ="text" name ="content" value ="<%=dto.getContent()%>"></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=dto.getRegdate() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type ="submit" value ="수정">
			</td>
		</tr>
	</table>
</form>

</body>
</html>
<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>글수정</h3>
<%
	MVCBoardDto dto=(MVCBoardDto)(request.getAttribute("update"));
%>
	<form action="mycontroller.jsp" method="post">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
		<table border="1">
			<tr>
				<th>작성자</th>			
				<td><%=dto.getWriter() %></td>			
			</tr>
			<tr>
				<th>글제목</th>			
				<td><input type="text" name="title" value="<%=dto.getTitle() %>"></td>			
			</tr>
			<tr>
				<th>글내용</th>			
				<td><textarea rows="10" cols="60" name="content"  ><%=dto.getContent() %></textarea></td>			
			</tr>
			<tr>		
				<td>
					<input type="submit" value="수정완료" onclick="location.href='mycontroller.jsp?command=list'">
					<input type="button" value="취소" onclick="location.href='mycontroller.jsp?command=list'">
				</td>			
			</tr>
		</table>
	</form>






</body>
</html>
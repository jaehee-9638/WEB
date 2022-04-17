
<%@page import="java.util.List"%>
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

<%
	List<MVCBoardDto> list=(List<MVCBoardDto>) request.getAttribute("list");
	
	
%>
<h3>게시판</h3>



	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
		for(MVCBoardDto dto : list){
%>		
		
		<tr>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><a href="mycontroller.jsp?command=myselect&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRegdate() %></td>
		</tr>
		
<%
		}
%>		

		<tr>
			<td>
				<input type="button" value="글작성" onclick="location.href='mycontroller.jsp?command=insertform'">
			</td>
		</tr>
	</table>




</body>
</html>
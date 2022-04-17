<%@page import="com.login.dto.MYMemberDto"%>
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
<h1>마이페이지 입니다.</h1>

<%
	MYMemberDto dto = (MYMemberDto)(request.getAttribute("select"));
%>
		<h1>mySelect</h1>

  
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		
		
		<tr> 
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='logincontroller.jsp?command=mypageupdate&myno=<%=dto.getMyno()%>'">
				
			</td>
		</tr>
	</table>
   

</body>
</html>
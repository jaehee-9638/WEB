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
<%
	MYMemberDto dto =(MYMemberDto) request.getAttribute("dto");

%>
<form action ="loginController.jsp" method="post" >
<input type ="hidden" name ="command" value="updateform">
<input type ="hidden" name ="myno" value ="<%=dto.getMyno() %>">
	<table>
		<tr>
			<th>회원번호</th>
			<td><%=dto.getMyno() %></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>등급</th>
			<td>
				<select name ="role" ><!-- 키 -->
					<option value="USER" <%=dto.getMyrole().equals("USER")? "selected":"" %>>user</option><!-- value -->
					<option value="ADMIN" <%=dto.getMyrole().equals("ADMIN")? "selected":"" %>>admin</option>
					<!-- option태그의 value는전송되는값 ,selected속성으로기본값설정가능, -->
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type ="submit" value ="등급변경">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
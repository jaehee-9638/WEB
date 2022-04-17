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
 MYMemberDto dto = (MYMemberDto)request.getAttribute("dto");
%>
<form action ="loginController.jsp" method ="post">
<input type ="hidden" name="command" value ="mypageUpdateForm">
<input type ="hidden" name ="myno" value ="<%=dto.getMyno() %>">

	<table>
		<tr>
			<th width="100px"></th>
			<td width="100px"></td>
		</tr>
		<tr>
			<th>회원번호</th>
			<td><%=dto.getMyno() %></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="mypw" value="<%=dto.getMypw() %>"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="myaddr" value="<%=dto.getMyaddr() %>"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="myphone" value="<%=dto.getMyphone() %>"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="myemail" value="<%=dto.getMyemail() %>"></td>
		</tr>
		<tr>
			<th>가입여부</th>
			<td><%=dto.getMyenabled() %></td>
		</tr>

		<tr>
			<th>등급</th>
			<td><%=dto.getMyrole() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type ="submit" value="글수덩 ">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
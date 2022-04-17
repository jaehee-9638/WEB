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
			<td><%=dto.getMypw() %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=dto.getMyaddr() %></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><%=dto.getMyphone() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=dto.getMyemail() %></td>
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
				<input type="button" value ="정보수정" onclick="location.href='loginController.jsp?command=mypageUpdate&myid=<%=dto.getMyid() %>'">
				<input type ="button" value ="탈퇴" onclick="location.href='loginController.jsp?command=idDrop&myno=<%=dto.getMyno() %>'" >
			</td>
		</tr>
	</table>


</body>
</html>
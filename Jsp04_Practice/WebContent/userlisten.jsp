<%@page import="com.login.dto.MYMemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function updateRole(myno){
		location.href="logincontroller.jsp?command=updateroleform&myno="+myno;
	}
</script>

</head>
<body>
<h2>전체 회원 리스트 (enabled=y)</h2>

<%
	List<MYMemberDto> list= (List<MYMemberDto>)request.getAttribute("list");
%>
<table border="1">
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>등급변경</th>
	</tr>
<%
	for (MYMemberDto dto:list) {
%>
	<tr>
		<td><%=dto.getMyno() %></td>
		<td><%=dto.getMyid() %></td>
		<td><%=dto.getMyname() %></td>
		<td><%=dto.getMyphone() %></td>
		<td><%=dto.getMyemail() %></td>
		<td><input type ="button" value="변경" onclick="updateRole(<%=dto.getMyno()%>);"></td>
	</tr>
<%
	}
%>

	<tr>
		<td colspan="9" align="right">
			<input type="button" value="메인" onclick="">
		</td>
	</tr>
</table>
</body>
</html>
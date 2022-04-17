<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Expires", "0");
%>
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
<h2>어드민 메인 페이지</h2>
<%
	//세션 만료시 index.html로
	MYMemberDto dto=(MYMemberDto)(session.getAttribute("dto"));
	if (dto==null){
		pageContext.forward("index.html");
	}
%>

<div>
	<span><%=dto.getMyid() %>님 환영합니다</span>
	<a href="logincontroller.jsp?command=logout">logout</a>
</div>

<div>
	<div>
		<a href="logincontroller.jsp?command=listall">회원 전체 조회(탈퇴한 회원 포함)</a>
	</div>
	<div>
		<a href="logincontroller.jsp?command=listen">회원 전체 조회(MYENABLED=Y)</a>
	</div>
</div>
</body>
</html>
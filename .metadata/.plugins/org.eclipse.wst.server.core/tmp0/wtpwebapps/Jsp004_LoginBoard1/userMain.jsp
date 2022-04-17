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
//myid받어
	String myid = (String)request.getAttribute("myid");
%>
<%=myid %>님 환영합니다.

유저페이지 입니다
내정보 조회 버튼만들자 (들어가면 정보수정이랑 회원탈퇴 버튼 만들자 )


<input type ="button" value ="내정보조회 " onclick="location.href='loginController.jsp?command=selectMypage&myid=<%=myid %>'">
<input type ="button" value ="로그아웃 " onclick="location.href='loginController.jsp?command=logout'">
</body>
</html>
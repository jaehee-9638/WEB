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
관리자페이지입니다.
<a href="loginController.jsp?command=userSelectAll">회원 전체 정보 확인(all)</a>
<a href="loginController.jsp?command=userSelectEn">회원 전체 정보 확인(탈퇴안한 회원만)</a>
<a href="loginController.jsp?command=updateRole">회원등급 조절 </a>

<input type ="button" value ="로그아웃 " onclick="location.href='loginController.jsp?command=logout'">
</body>
</html>
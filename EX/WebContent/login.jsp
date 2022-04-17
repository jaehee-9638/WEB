<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <form action="loginAction.jsp" method="post">
	 	<h3>로그인화면 </h3>
	 	<input type="text" placeholder="아이디" name="user_id" maxlength="20">
	 	<input type="password" placeholder="비밀번호" name="user_password" maxlength="20">
	 	<input type="submit" value="로그인">
	 </form>
</body>
</html>
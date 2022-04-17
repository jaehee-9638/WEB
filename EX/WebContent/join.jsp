<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="joinAction.jsp" method="post">
<h3>회원가입 화면 </h3>
	<input type="text" placeholder="아이디" name="user_id" maxlength="20"><br>
	<input type="password" placeholder="비밀번호" name="user_password" maxlength="20"><br>
	<input type="text" placeholder="이름" name="user_name" maxlength="20"> <br>
	<input type="radio" name="user_gender"  value="남자" checked >남자	
	<input type="radio" name="user_gender"  value="여자" checked >여자	<br>
	<input type="email" placeholder="이메일" name="user_email" maxlength="50"> <br>
	<input type="submit" value="회원가입">
</form>	 	
</body>
</html>
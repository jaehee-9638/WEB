<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.nav {
	height: 50px;
	border-bottom: 1px solid black;
	display: flex;
	align-items: center;
}

.nav-right-items {
	display: flex;
	margin-left: auto;
}

.nav-item {
	margin-left: 10px;
	margin-right:30px;
}

.company-name {
	margin-left: 20px;
}
</style>
</head>
<body>
<!-- 헤더 만들기 로고 이미지 나중에 넣자 
	컨트롤러로 보내자 
 -->
<div class="nav">
		<div class="company-name">로고</div>
		<div class="nav-right-items">
			<div class="nav-item"><a href="naver_map.jsp">map </a></div>
			<div class="nav-item"><a href="board.do?command=boardlist">게시판</a></div>
			<div class="nav-item"><a href="test.jsp">test </a></div>
			<div class="nav-item"><a href="login.jsp">로그인/회원가입 </a></div>
		</div>

	</div>
</body>
</html>
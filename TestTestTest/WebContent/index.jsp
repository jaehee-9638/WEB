<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css_link/index.css" rel="stylesheet" type="text/css" />

</head>
<body>

<!-- 접속시 보이는 화면 구성  -->
	<!-- ex> <a href="login.html">로그인 화면 </a> 이런식으로 페이지 넘어가게 하자-->

	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<div class="main" style="background-image: url('images/mainimg.jpg')">
	
		<div class="title">DayTwo</div>
		<div class="subtitle">데이트 코스 고민일때!</div>
		<!--  <img src="images/mainimg.jpg" alt="메인이미지" class="main-img"> -->
		
	</div>
	
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	

</body>
</html>
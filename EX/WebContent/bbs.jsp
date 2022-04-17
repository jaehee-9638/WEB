<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String user_id=null;
	if(session.getAttribute("user_id") != null){
		user_id=(String) session.getAttribute("user_id");
	}
%>

<ul>
<li class="active"><a href="main.jsp">메인</a></li>

<%
	if(user_id==null){
		//로그인이 되어있지 않으면 
	
%>

	<!-- 로그인이 되어있지 않음 사람이 볼수있는 화면  -->
<li><a href="login.jsp">로그인</a></li>
<li><a href="join.jsp">회원가입 </a></li>
<%
	}else{
%>
	<!-- 로그인이 되어있는 사람이 볼수있는 화면  -->
<li><a href="mypage.jsp">마이페이지</a></li>
<li><a href="logoutAction.jsp">로그아웃 </a></li>
<%
	}
%>

</ul>
<div>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<tr>
			<td>1</td>
			<td>안녕하세요</td>
			<td>홍길동</td>
			<td>20201.22.22</td>
		</tr>
		
	</table>
	<button onclick="location.href='write.jsp'">글작성</button>
</div>
</body>
</html>
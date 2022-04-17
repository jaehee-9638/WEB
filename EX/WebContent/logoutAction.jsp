
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text.html; charset=UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
세션 관리 
세션 : 현재 접속한 회원에 할당해주는 고유의 아이디임 , 웹서버는 한명의 회원을 세션 아이디로 구분할수있는데 따라서 로그인에 성공했을때 세션아이디를 부여해주는걸로 부터 세션 관리가 시작된다.
따라서 로그인액션 페이지와 회원가입액션 페이지에 각각 로그인에 성공한 회원에게 세션을 부여해주는 부분(session.setAttribute("user_id",user.getUser_id());)을 넣어주고 
이렇게 부여해준 세션을 할당 해제해주는걸 이 페이지에서 진행한다. 
 -->
 
 <%
 	session.invalidate();
 	//현재 이 페이지에 접속한 회원이 세션을 뺏앗기도록 해서 로그아웃 시켜주고 
 	
 %>
 <script>
 	location.href='main.jsp';
 </script>
	
</body>
</html>
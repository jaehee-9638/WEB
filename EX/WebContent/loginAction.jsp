<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text.html; charset=UTF-8");%>

<jsp:useBean id="user" class="user.User" scope="page"/>
<jsp:setProperty name="user" property="user_id"/>
<jsp:setProperty name="user" property="user_password"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 위에 jsp : 태그는 
	첫번째 태그는 한명의 회원정보를 담는 User라는 클래스를 javaBeans로 사용하기 위한 태그임 (scope는 사용범위인듯 ,현재페이지에서만 사용가능 )
	두번째 태그는 로그인페이지에서 넘겨준 userID를 받아서 한명의 사용자에userID에 넣어준다는거고 
	세번째 태그는 두번째와 비슷한 내용 이다.
	이페이지로 넘어온 userID와 userPassword가 그대로 담기게 되는거임 
 -->
 
 <%
 //이미 로그인이된 유저는 로그인과 회원가입 페이지에 들어갈수 없도록 해줘야한다. 
 String user_id=null;
 if(session.getAttribute("user_id") !=null){	//userid란이름으로 session이존재하는 회원들은 
	 user_id=(String) session.getAttribute("user_id");	//userid에 해당세션값을 넣어줄수있도록 한다. 
 }
 if(user_id != null){	//userid가 null값이 아닌경우 -> 로그인이 되어있는경우 
	 PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('이미 로그인이 되어있습니다. ')");
		script.println("location.href='main.jsp'");
		script.println("</script>");
 }
 
 
 //userDAO라는 인스턴스만들어주고 
 	UserDAO userDAO=new UserDAO();
 //로그인을 시도할수있도록 하자 
 	int result=userDAO.login(user.getUser_id(),user.getUser_password());
 //이렇게 하면 로그인 페이지에서 userID와 userPassword가 각각 입력이 된값으로 Action페이지로 넘어와서 그값을 여기 login함수에 넣어서 실행 해주는거임 
 //-2~1까지 각각의 결과에 대한 값이 result 에 담긴다. 	
 	if(result==1){	
 		session.setAttribute("user_id",user.getUser_id());
 		PrintWriter script=response.getWriter();	//PrintWriter는 javascript문장을 작성하기위해 사용하는 거임 
 		//printWriter를 해서 하나의 스크립트 문장을 넣어줄수있도록 하고 
 		script.println("<script>");	//script문장을 유동적으로 실행할수 있게 해준다. 
 		script.println("location.href='main.jsp'");
 		script.println("</script>");
 	}
 	else if(result==0){
 		PrintWriter script=response.getWriter();
 		script.println("<script>");
 		script.println("alert('비밀번호가 틀립니다.')");
 		script.println("history.back()");
 		script.println("</script>");
 	}
 	else if(result==-1){
 		PrintWriter script=response.getWriter();
 		script.println("<script>");
 		script.println("alert('존재하지 않는 아이디 입니다..')");
 		script.println("history.back()");
 		script.println("</script>");
 	}
 	else if(result==-2){
 		PrintWriter script=response.getWriter();
 		script.println("<script>");
 		script.println("alert('데이터 베이스 오류 발생.')");
 		script.println("history.back()");
 		script.println("</script>");
 	}
 %>
	
</body>
</html>
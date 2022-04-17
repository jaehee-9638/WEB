<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text.html; charset=UTF-8");%>

<jsp:useBean id="user" class="user.User" scope="page"/>
<jsp:setProperty name="user" property="user_id"/>
<jsp:setProperty name="user" property="user_password"/>
<jsp:setProperty name="user" property="user_name"/>
<jsp:setProperty name="user" property="user_gender"/>
<jsp:setProperty name="user" property="user_email"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 위에 jsp : 태그는 다섯가지 변수 모두 받아야하기 때문에 5가지 모두 넣어줬음 
 -->
 
 <%
 //이부분은 loginaction페이지랑 동일 함  
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
 
 
 
 	//하나라도 입력안할시 회원가입 안되기 때문에  
 	if(user.getUser_id()==null || user.getUser_password()==null || user.getUser_name()==null || user.getUser_gender()==null || user.getUser_email()==null){
 		PrintWriter script=response.getWriter();
 		script.println("<script>");
 		script.println("alert('입력되지 않은 사항이 있습니다. ')");
 		script.println("history.back()");
 		script.println("</script>");
 	}else{
 	//모두 입력되었다고 하면 
 	//db에 접근할 객체 만들어주고 
 	UserDAO userDAO=new UserDAO();
 	//
 	int result=userDAO.join(user);	//->얘는 useBean 
 	if(result==-1){
 		//회원가입시 다 입력했는데 오류날경우가 아이디가 존재하는경우밖에없어서 
 		//아이디에 pk조건 추가해줬음 
 		PrintWriter script=response.getWriter();
 		script.println("<script>");
 		script.println("alert('이미 존재하는 아이디 입니다..')");
 		script.println("history.back()");
 		script.println("</script>");
 	}
 	else {	
 		session.setAttribute("user_id",user.getUser_id());
 		PrintWriter script=response.getWriter();
 		script.println("<script>");
 		script.println("location.href='main.jsp'");
 		script.println("</script>");
 	}
 	}
 	
 	
 %>
	
</body>
</html>
<%@page import="user.User"%>
<%@page import="user.Board"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="user.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text.html; charset=UTF-8");%>

<jsp:useBean id="board" class="user.Board" scope="page"/>


<jsp:setProperty name="board" property="board_title"/>

<jsp:setProperty name="board" property="board_content"/>

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

	
	
	BoardDAO boardDAO=new BoardDAO();
	
	int result=boardDAO.write(board.getBoard_title(),board.getUser_id(),board.getBoard_content());	//->얘는 useBean 
	if(result!=0){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('글작성 성공 ')");
		script.println("location.href='bbs.jsp'");
		script.println("</script>");
	

	}else{
		PrintWriter script=response.getWriter();
		script.println("<script>");
 		script.println("alert('글작성 실패')");
 		script.println("history.back()");
 		script.println("</script>");

	}
%>
 	

 
 <!-- 
 
 //이부분은 loginaction페이지랑 동일 함  
 String user_id=null;
 if(session.getAttribute("user_id") !=null){	//userid란이름으로 session이존재하는 회원들은 
	 user_id=(String) session.getAttribute("user_id");	//userid에 해당세션값을 넣어줄수있도록 한다. 
 }
 if(user_id == null){	//userid가 null인경우 -> 로그인해야함 
	 PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요 ')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
 }else{
	
	 	if(board.getBoard_title()==null || board.getBoard_content()==null ){
	 		PrintWriter script=response.getWriter();
	 		script.println("<script>");
	 		script.println("alert('입력되지 않은 사항이 있습니다. ')");
	 		script.println("history.back()");
	 		script.println("</script>");
	 	}else{
	 	
	 	BoardDAO boardDAO=new BoardDAO();
	 	//
	 	int result=boardDAO.write(board.getBoard_title(),user_id,board.getBoard_content());	//->얘는 useBean 
	 	if(result==-1){
	 		
	 		PrintWriter script=response.getWriter();
	 		script.println("<script>");
	 		script.println("alert('글쓰기에 실패했습니다.')");
	 		script.println("history.back()");
	 		script.println("</script>");
	 	}
	 	else {	
	 		
	 		PrintWriter script=response.getWriter();
	 		script.println("<script>");
	 		script.println("location.href='bbs.jsp'");
	 		script.println("</script>");
	 	}
	 	}
	 	
 }
 
 
 -->
	
</body>
</html>
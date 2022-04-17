<%@page import="java.util.List"%>
<%@page import="com.login.dto.MYMemberDto"%>
<%@page import="com.login.biz.MYMemberBiz"%>
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


<%
String command=request.getParameter("command");
System.out.println("["+command+"]");

MYMemberBiz biz=new MYMemberBiz();

if (command.equals("login")){
	String myid =request.getParameter("myid");
	String mypw =request.getParameter("mypw");
	
	MYMemberDto dto=biz.login(myid, mypw);
	
	if(dto != null){
		session.setAttribute("dto", dto);
		session.setMaxInactiveInterval(10*60);
		
		if(dto.getMyrole().equals("ADMIN")){
			
			response.sendRedirect("adminmain.jsp");
			
		}else if(dto.getMyrole().equals("USER")){
			//int myno=Integer.parseInt(request.getParameter("myno"));
			//dto=biz.selectUser(myno);
			//request.setAttribute("select", dto);
			//pageContext.forward("usermain.jsp");
			response.sendRedirect("usermain.jsp");
		}
	}else{ 
%>
	<script type="text/javascript">
	alert("로그인실패");
	location.href="index.html";
	</script>

<%		

	}
	
}else if (command.equals("logout")){
	session.invalidate();
	response.sendRedirect("index.html");
}else if (command.equals("listall")){
	
	List<MYMemberDto> list= biz.selectMemberAll();
	request.setAttribute("list", list);
	pageContext.forward("userlistall.jsp");
	
	
}else if (command.equals("listen")){
	List<MYMemberDto> list= biz.selectMemberEn();
	request.setAttribute("list", list);
	pageContext.forward("userlisten.jsp");
	
}else if (command.equals("updateroleform")){
	int myno= Integer.parseInt(request.getParameter("myno"));
	MYMemberDto dto =biz.selectUser(myno);
	request.setAttribute("dto", dto);
	pageContext.forward("updaterole.jsp");
}else if (command.equals("updaterole")){
	int myno=Integer.parseInt(request.getParameter("myno"));
	String myrole=request.getParameter("myrole");
	int res=biz.updateRole(myno, myrole);
	if (res>0){
%>
	<script type="text/javascript">
	alert("등급변경 성공");
	location.href="logincontroller.jsp?command=listen";
	</script>
<% 		
		
	}else {
%>
	<script type="text/javascript">
	alert("등급변경 실패");
	location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
	</script>
<% 
		
	}
}else if (command.equals("registform")){
	response.sendRedirect("regist.jsp");
}else if (command.equals("idchk")){
	String myid=request.getParameter("myid");
	MYMemberDto dto=biz.idcheck(myid);
	
	boolean idnotused=true;
	if (dto.getMyid()!=null){
		idnotused=false;
	}
	response.sendRedirect("idchk.jsp?idnotused="+idnotused);
}else if (command.equals("registres")){
	String myid=request.getParameter("myid");
	String mypw=request.getParameter("mypw");
	String myname=request.getParameter("myname");
	String myaddr=request.getParameter("myaddr");
	String myphone=request.getParameter("myphone");
	String myemail=request.getParameter("myemail");
	System.out.println("1");
	MYMemberDto dto =new MYMemberDto();
	dto.setMyid(myid);
	dto.setMypw(mypw);
	dto.setMyname(myname);
	dto.setMyaddr(myaddr);
	dto.setMyphone(myphone);
	dto.setMyemail(myemail);
	System.out.println("2");
	
	int res=biz.regist(dto);
	if (res>0){
		System.out.println("3");
%>

	<script type="text/javascript">
	alert("가입 완료! " + dto.getMyid + "님 환영합니다");
	location.href="logincontroller.jsp?command=userpage";
	</script>
<% 		
System.out.println("4");
		
	}else{
		System.out.println("5");
%>
	<script type="text/javascript">
	alert("가입 실패");
	location.href="regist.jsp";
	</script>
<% 		
System.out.println("6");
	}
}


%>


<h1 style="color: red;">잘못왔다...</h1>
</body>
</html>
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

//login 컨트롤러 
if (command.equals("login")){
	String myid =request.getParameter("myid");
	String mypw =request.getParameter("mypw");
	
	MYMemberDto dto=biz.login(myid, mypw);
	
	//login상태일때 세션 설정
	if(dto != null){
		session.setAttribute("dto", dto);
		session.setMaxInactiveInterval(10*60);
		
		//등급별 이동위치 지정 
		if(dto.getMyrole().equals("ADMIN")){
			response.sendRedirect("adminmain.jsp");
			
		}else if(dto.getMyrole().equals("USER")){
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
	
//로그아웃 : 세션 종료시 index.html ->로그인 화면으로 이동한다. 
}else if (command.equals("logout")){
	session.invalidate();
	response.sendRedirect("index.html");
//회원정보 모두 조회 하기 (탈퇴회원포함)
}else if (command.equals("listall")){
	
	List<MYMemberDto> list= biz.selectMemberAll();
	request.setAttribute("list", list);
	pageContext.forward("userlistall.jsp");
	
//회원정보 조회 (탈퇴 한 회원 빼고 )
}else if (command.equals("listen")){
	List<MYMemberDto> list= biz.selectMemberEn();
	request.setAttribute("list", list);
	pageContext.forward("userlisten.jsp");

//updaterole.jsp로 이동하는
}else if (command.equals("updateroleform")){
	int myno= Integer.parseInt(request.getParameter("myno"));
	MYMemberDto dto =biz.selectUser(myno);
	request.setAttribute("dto", dto);
	pageContext.forward("updaterole.jsp");
	
//updaterole.jsp에서 온 애 ->등급 변경 성공시 경로 지정 
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
//regist.jsp로 경로 지정 
}else if (command.equals("registform")){
	response.sendRedirect("regist.jsp");
//id중복확인 
}else if (command.equals("idchk")){
	String myid=request.getParameter("myid");
	MYMemberDto dto=biz.idcheck(myid);
	boolean idnotused=true;
	if (dto.getMyid()!=null){
		idnotused=false;
	}
	response.sendRedirect("idchk.jsp?idnotused="+idnotused);
//regist.jsp에서 가입하기 버튼 누르면 이쪽으로 온다. 
}else if (command.equals("registres")){
	String myid=request.getParameter("myid");
	String mypw=request.getParameter("mypw");
	String myname=request.getParameter("myname");
	String myaddr=request.getParameter("myaddr");
	String myphone=request.getParameter("myphone");
	String myemail=request.getParameter("myemail");
	MYMemberDto dto =new MYMemberDto();
	dto.setMyid(myid);
	dto.setMypw(mypw);
	dto.setMyname(myname);
	dto.setMyaddr(myaddr);
	dto.setMyphone(myphone);
	dto.setMyemail(myemail);
	
	int res=biz.regist(dto);
	if (res>0){
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
}else if (command.equals("mypage")){
	//1
			int Myno = Integer.parseInt(request.getParameter("myno"));
			//2
			MYMemberDto dto = biz.selectUser(myno);
			//3
			request.setAttribute("select", dto);
			//4
			pageContext.forward("mypage.jsp");
}


%>


<h1 style="color: red;">잘못왔다...</h1>
</body>
</html>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.login.biz.MYMemberBiz"%>
<%@page import="com.login.dto.MYMemberDto"%>
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
	//1. 보내준 값이 있으면 받는다.
	//2. db에 전달할 값이 있으면 전달하고,없으면 없는대로 호출해서 리턴받는다.
	//3. 화면에 전달할 값이 있으면, request 객체에 담아준다.
	//4. 보낸다.
	String command = request.getParameter("command");
	System.out.println("["+command+"]");
	MYMemberBiz biz = new MYMemberBiz();
	if (command.equals("login")){
		
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		
		
		MYMemberDto dto = biz.login(myid, mypw);
		if (dto != null){
			//세션에 로그인정보 담아주자 
			session.setAttribute("dto", dto);
			session.setMaxInactiveInterval(10*60);
			
			if (dto.getMyrole().equals("ADMIN")){	//등급이 관리자일땐 관리자 페이지로
				response.sendRedirect("adminMain.jsp");
			}else {	//등급이 유저일땐 유저 페이지로
				request.setAttribute("myid", myid);//잘담겼다~~
				pageContext.forward("userMain.jsp");
			}
		}else {
			System.out.println("로그인 실패");
			
		}
		
	}else if (command.equals("logout")){
		session.invalidate();
		response.sendRedirect("index.html");
	}else if (command.equals("userSelectAll")){
		//유저ㅓ 전부다 select 
		List<MYMemberDto> list = (List<MYMemberDto>)biz.selectUserListAll();
	
		
		request.setAttribute("user", list);
		
		pageContext.forward("userListAll.jsp");
	}else if(command.equals("userSelectEn")){
		List<MYMemberDto> list =biz.selectUserListEn();
		request.setAttribute("list", list);
		pageContext.forward("userListEn.jsp");
	}else if (command.equals("updateRole")){
		//유저전부 select해서 role update 가능하도록 ? 
		//받아야할건 그냥 ....
		//페이지 이동해서 화면에 일단 개인 정보 한줄로뿌려준다음
		int myno = Integer.parseInt(request.getParameter("myno"));
		MYMemberDto dto = biz.selectUser(myno);
		request.setAttribute("dto", dto);
		pageContext.forward("updateRole.jsp");
	}else if (command.equals("updateform")){
		int myno =Integer.parseInt(request.getParameter("myno"));
		String role = request.getParameter("role");
		
		MYMemberDto dto = new MYMemberDto();
		dto.setMyno(myno);
		dto.setMyrole(role);
		System.out.println(dto.getMyno()+dto.getMyrole()+"컨트롤러에서 받은거");
		int res = biz.updateUserRole(myno, role);
		System.out.println(res+"res");
		if (res>0){//성공
%>
	<script type="text/javascript">
	alert ("등급변경 성공");
	location.href = "loginController.jsp?command=userSelectEn";
	</script>
<%			
		}else{	//실패
%>
	<script type="text/javascript">
	alert ("등급변경 실패");
	location.href = "loginController.jsp?command=userSelectEn";
	</script>
<%		
			
		}
		
	}else if (command.equals("selectMypage")){
		
		String myid = request.getParameter("myid");
		MYMemberDto dto = biz.selectUser2(myid);
		request.setAttribute("dto", dto);
		pageContext.forward("mypage.jsp");
		
	}else if (command.equals("idDrop")){
		int myno =Integer.parseInt(request.getParameter("myno"));
		int res =biz.idDrop(myno);
		if (res>0){	//성공
			System.out.println("성공");
			response.sendRedirect("index.html");
		}else{//실패
			System.out.println("실패");
			response.sendRedirect("index.html");
		}
				
	}else if (command.equals("mypageUpdate")){
		//여긴그냥 정보만 받아서 화면에 selectMypage 뿌려주고 input박스에 담아주자 
		
		String myid = request.getParameter("myid");
		MYMemberDto dto = biz.selectUser2(myid);
		request.setAttribute("dto", dto);
		pageContext.forward("mypageUpdate.jsp");
		
	}else if (command.equals("mypageUpdateForm")){
	
		String mypw =request.getParameter("mypw");
		String myaddr =request.getParameter("myaddr");
		String myphone =request.getParameter("myphone");
		String myemail =request.getParameter("myemail");
		int myno =Integer.parseInt(request.getParameter("myno"));
		
		MYMemberDto dto = new MYMemberDto();
		
		dto.setMypw(mypw);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		dto.setMyno(myno);
		
		int res=biz.mypageUpdate(mypw, myaddr, myphone, myemail, myno);
		request.setAttribute("myno", myno);
		if (res>0){	//성공
			
			pageContext.forward("loginController.jsp?command=selectMypage2");
		}else{//실패
			
			pageContext.forward("loginController.jsp?command=selectMypage2");
		}
		
	}else if (command.equals("selectMypage2")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		MYMemberDto dto = biz.selectUser(myno);
		request.setAttribute("dto", dto);
		pageContext.forward("mypage.jsp");
	}else if(command.equals("regist")){
		
		response.sendRedirect("regist.jsp");
		
	}else if (command.equals("idchk")){
		String myid = request.getParameter("myid");
		//다오만들고 호출 
		MYMemberDto dto = biz.idchk();
		//
		System.out.println("컨트롤러로 리턴된 dao" +dto.getMyid());
		boolean idnotused=true;
		if (dto.getMyid() != null){	//얘 myid 말고 그걸로 바꿔야하는거아님 ??? 디티오안에 있는게 없으면 ?? 뭐지 얘이상함 흐름 다시보자 
			idnotused=false;
		}
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
	}else if(command.equals("registForm")){
		//여기서 input내용 받고 다오 호출해서 성공 실패 조회 하고 로그인 화면으로 가서 로그인 할수있도록
		String myid =request.getParameter("myid");
		String mypw =request.getParameter("mypw");
		String myname=request.getParameter("myname");
		String myaddr=request.getParameter("myaddr");
		String myphone=request.getParameter("myphone");
		String myemail=request.getParameter("myemail");
		
		MYMemberDto dto = new MYMemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res= biz.regist(dto);
		if (res>0){//성공
			response.sendRedirect("index.html");
		}else{//실패
			response.sendRedirect("index.html");
		}
	}
	
%>

</body>
</html>
<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBoardBiz"%>
<%@page import="com.mvc.biz.MVCBoardBizImpl"%>
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
<!-- form으로 보내서 res에서 확인하던 과정을 그냥 여기서 한번에 확인할수잇게 하는듯  -->
<!-- 앞에서 command보냈으니 받아야지 .... -->
<!-- 
	controller에서 해야할 일 : 요청한 명령을 확인한다.
1. 보내준 값이 있으면 받는다.
2. db에 전달할 값이 있으면 전달하고,없으면 없는대로 호출해서 리턴받는다.
3. 화면에 전달할 값이 있으면, request 객체에 담아준다.
4. 보낸다.
		pageContext.forward() : 페이지 위임 (request, response 객체가 그대로 전달)
			response.sendRedirect() : 페이지 이동 (새로운 request, response 객체)
 -->
<%
	String command= request.getParameter("command");
	System.out.println("["+command+"]");
	
	MVCBoardBiz biz= new MVCBoardBizImpl();
	
	if (command.equals("list")){
		List<MVCBoardDto> list = biz.selectList();
		request.setAttribute("list", list);
		pageContext.forward("mylist.jsp");
	}else if (command.equals("myselect")){
		int seq= Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto=biz.selectOne(seq);
		request.setAttribute("select", dto);
		pageContext.forward("myselect.jsp");
	}else if (command.equals("insertform")){
		response.sendRedirect("myinsert.jsp");
	}else if (command.equals("insertres")){
		String writer=request.getParameter("writer");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		MVCBoardDto dto= new MVCBoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		int res=biz.insert(dto);
		if(res>0){
%>		
		<script type="text/javascript">
		alert("글작성 성공");
		location.href="mycontroller.jsp?command=list";
		</script>
<% 
		
		}else{
%>

		<script type="text/javascript">
		alert("글작성 실패");
		location.href="mycontroller.jsp?command=insertform";
		</script>

<%			
		}
		
		
	}else if (command.equals("update")){
		int seq=Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto= biz.selectOne(seq);
		request.setAttribute("update", dto);
		pageContext.forward("myupdate.jsp");
	}else if (command.equals("updateres")){
		int seq=Integer.parseInt(request.getParameter("seq"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");

		MVCBoardDto dto=new MVCBoardDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		int res=biz.update(dto);
		if (res>0){
			
%>
		<script type="text/javascript">
		alert("수정성공");
		location.href="mycontroller.jsp?command=list";
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
		alert("수정실패");
		location.href="mycontroller.jsp?command=myselect";
		</script>
<% 			
		}
		
		
		
		
	}else if (command.equals("deleteres")){
		int seq=Integer.parseInt(request.getParameter("seq"));
		int res=biz.delete(seq);
		if (res>0){
%>
		<script type="text/javascript">
		alert("삭제성공");
		location.href="mycontroller.jsp?command=list";
		</script>
<% 			
			
		}else {
%>
		<script type="text/javascript">
		alert("삭제 실패");
		location.href="mycontroller.jsp?command=list";
		</script>
<%			
		}
	}
		
%>
</body>
</html>
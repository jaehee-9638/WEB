<%@page import="com.mvc.biz.MVCBoardBiz"%>
<%@page import="com.mvc.biz.MVCBoardBizImpl"%>
<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String command = request.getParameter("command");
	System.out.println("["+command+"]");
	MVCBoardBiz biz = new MVCBoardBizImpl();
	if (command.equals("list")){
		//여기서 다오 받아서 속성에 넣어서 list.jsp로 보내자 
		List<MVCBoardDto> list =biz.selectList();
		//list = new ArrayList<mvcBoardDto>();
		
		
		request.setAttribute("list", list);
		pageContext.forward("boardList.jsp");
		
	}else if (command.equals("selectList")){
		int seq =Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		pageContext.forward("boardSelect.jsp");
		
	}else if (command.equals("insert")){
		response.sendRedirect("insert.jsp");
	}else if (command.equals("insertform")){
		String writer =request.getParameter("writer");
		String title =request.getParameter("title");
		String content=request.getParameter("content");//전달 잘 받ㅇ므 
		
		//db전달 
		MVCBoardDto dto =new MVCBoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		System.out.println("dto에저장된 content:"+dto.getContent());
		//호출해서 리턴받자 
		int res=biz.insert(dto);
		
		//리쿠ㅔ스크 객체에 담자 
		request.setAttribute("res", res);
		//보내자
		if (res>0){
%>
	<script type="text/javascript">
	alert("작성성공");
	location.href="myController.jsp?command=list";
	</script>			
<%		
		}else {
%>
	<script type="text/javascript">
	alert("작성실패");
	location.href="insert.jsp";
	</script>			
<%					
		}
	}else if (command.equals("update")){
		//보낼때 seq보내야함 
		int seq = Integer.parseInt(request.getParameter("seq")); //일단 얘잘 받음 
		
		request.setAttribute("seq", seq);
		pageContext.forward("update.jsp");
	}else if (command.equals("updateform")){
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int seq =Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto = new MVCBoardDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setSeq(seq);
		int res = biz.update(dto);
		if (res>0){
%>
	<script type="text/javascript">
	alert("수정성공");
	location.href="myController.jsp?command=list";
	</script>			
<%	
		}else{
%>
	<script type="text/javascript">
	alert("수정실패");
	location.href="update.jsp?seq=<%=dto.getSeq()%>";
	</script>			
<%				
		}
	}else if (command.equals("delete")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		int res =biz.delete(seq);
		if (res>0){
%>
	<script type="text/javascript">
	alert("삭제 성공");
	location.href="myController.jsp?command=list";
	</script>			
<%				
		}else{
%>
	<script type="text/javascript">
	alert("삭제 실패");
	location.href="myController.jsp?command=selectList&seq=<%=seq%>";
	</script>			
<%				
		}
	}else if (command.equals("muldel")){
		String [] seqs=request.getParameterValues("chk");
		for (int i =0; i<seqs.length; i++){
		System.out.println("컨트롤러에서 받은 seqs :"+ seqs[i]);
		}
		int res=biz.multiDelete(seqs);
		System.out.println("res : "+res);
		if (res>0){
%>
	<script type="text/javascript">
	alert("선택삭제 성공");
	location.href="myController.jsp?command=list";
	</script>			
<%			
		}else{
%>
	<script type="text/javascript">
	alert("삭제 실패");
	location.href="myController.jsp?command=list";
	</script>			
<%			
		}
		
	}
%>

</body>
</html>
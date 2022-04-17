<%@page import="com.board.dao.MDBoardDao"%>
<%@page import="com.board.dao.MDBoardDaoImpl"%>
<%@page import="com.board.dto.MDBoardDto"%>
<%@page import="com.board.biz.MDBoardBiz"%>
<%@page import="com.board.biz.MDBoardBizImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<%	response.setContentType("text.html; charset=UTF-8");%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	String title =request.getParameter("title");
	String content=request.getParameter("content");
	System.out.println(seq);
	System.out.println(title);
	System.out.println(content);//넘어온건 맞음 근데 여기서 저장하는과정에서 
	
	
	MDBoardBiz biz = new MDBoardBizImpl();
	MDBoardDto dto =new MDBoardDto();
	//seq,null,title,content,null
	
	dto.setSeq(seq);
	dto.setTitle(title);
	dto.setContent(content); 
	int res = biz.update(dto);
	if (res>0){
%>
	<script type="text/javascript">
	alert("수정성공");
	location.href="boardlist.jsp";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
	alert("수정실패");
	location.href="boardUpdate.jsp?seq=<%=seq%>";
	</script>
<%
	}
%>

</body>
</html>
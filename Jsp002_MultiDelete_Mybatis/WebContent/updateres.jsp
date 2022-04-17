<%@page import="com.board.biz.MDBoardBiz"%>
<%@page import="com.board.biz.MDBoardBizImpl"%>
<%@page import="com.board.dto.MDBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
 <%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String title =request.getParameter("title");
	String content = request.getParameter("content");
	int seq =Integer.parseInt (request.getParameter("seq"));
	
	MDBoardDto dto = new MDBoardDto(seq,null,title,content,null);
	
	MDBoardBiz biz =new MDBoardBizImpl();
	
	int res=biz.update(dto);
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
	location.href="update.jsp?seq=<%=seq%>";
	</script>
<%
	}
%>

</body>
</html>
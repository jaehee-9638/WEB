<%@page import="com.board.dao.MDBoardDaoImpl"%>
<%@page import="com.board.dao.MDBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text.html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	MDBoardDao dao = new MDBoardDaoImpl();
	int res = dao.delete(seq);
	if(res > 0) {
%>
	<script type="text/javascript">
		alert("삭제 완료");
		location.href="boardlist.jsp";
	</script>
<%
	} else {
%>
	<script type="text/javascript">
		alert("삭제 실패");
		location.href="boardselect.jsp?seq=<%=seq%>";		
	</script>
<%
	}
%>
<body>


</body>
</html>
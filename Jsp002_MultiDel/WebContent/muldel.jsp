<%@page import="com.board.dao.MDBoardDaoImpl"%>
<%@page import="com.board.dao.MDBoardDao"%>
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
	String []seqs= request.getParameterValues("chk");
	for (int i=0; i<seqs.length; i++){

	System.out.println(seqs[i]);	
	}
	if(seqs==null || seqs.length==0){
%>
	<script type="text/javascript">
	alert("선택삭제시 하나이상 선택해주세요");
	location.href="boardlist.jsp";
	</script>
<%
	}else {
	MDBoardDao dao = new MDBoardDaoImpl();
	int res =dao.multidel(seqs);
	if (res>0){

%>
	<script type="text/javascript">
	alert("선택삭제 성공 : "+<%=res%>+"개 삭제");
	location.href="boardlist.jsp";
	</script>
<%
	}else {
%>

	<script type="text/javascript">
	alert("선택삭제 실패");
	location.href="boardlist.jsp";
	</script>
<%
	}

%>

<%
	}
%>
</body>
</html>
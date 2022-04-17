<%@page import="com.board.biz.MDBoardBizImpl"%>
<%@page import="com.board.biz.MDBoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String[] seqs =request.getParameterValues("chk");
	if(seqs==null|| seqs.length==0){

%>
<script type="text/javascript">
	alert("하나 이상 체크하세요")
	location.href="boardlist.jsp"
</script>


<%
	}else{
		MDBoardBiz biz=new MDBoardBizImpl();
		int res =biz.multidelete(seqs);
		if (res>0){
	

%>

<script type="text/javascript">
	alert("선택삭제 성공")
	location.href="boardlist.jsp"
	
</script>


<%
	}else{
%>

<script type="text/javascript">
	alert("선택삭제 실패")
	location.href="boardlist.jsp"
	
</script>

<%	
		}
	}	
%>

<body>

</body>
</html>
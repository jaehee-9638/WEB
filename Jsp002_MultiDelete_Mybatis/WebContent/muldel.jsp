<%@page import="com.board.biz.MDBoardBizImpl"%>
<%@page import="com.board.biz.MDBoardBiz"%>
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
String []seqs = request.getParameterValues("chk");
if (seqs==null || seqs.length==0){
%>
 하나이상
<%
}else {
	
	//값받고
	//성공시
	MDBoardBiz biz = new MDBoardBizImpl();
	int res = biz.muldel(seqs);
	if (res>0){
%>
	<script type="text/javascript">
	alert("선택삭제 성공 : "+<%=res%>+"개 삭제")
	location.href="boardlist.jsp";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
	alert("선택삭제 실패)
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
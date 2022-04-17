<%@page import="com.myboard.dao.MyBoardDao"%>
<%@page import="com.myboard.dto.MyBoardDto"%>
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
int seq =Integer.parseInt(request.getParameter("seq"));
String title=request.getParameter("title");
String content=request.getParameter("content");//여기까진 잘 넘어옴 
System.out.println(seq);
System.out.println(title);	
System.out.println(content);


MyBoardDto dto = new MyBoardDto();
dto.setSeq(seq);
dto.setTitle(title);
dto.setContent(content);

MyBoardDao dao = new MyBoardDao();
int res=dao.update(dto);
System.out.println(res);//지금 res가 0이다...
if (res>0){
%>
<script type="text/javascript">
alert("수정완료");
location.href="mylist.jsp";
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
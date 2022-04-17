<%@page import="com.myboard.dto.MyBoardDto"%>
<%@page import="com.myboard.dao.MyBoardDao"%>
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
String writer = request.getParameter("writer");
String title =request.getParameter("title");
String content =request.getParameter("content");
MyBoardDto dto =new MyBoardDto();
dto.setWriter(writer);
dto.setTitle(title);
dto.setContent(content);
MyBoardDao dao = new MyBoardDao();
int res =dao.insert(dto);	//res받는거
if(res>0){
%>
<script type="text/javascript">
alert("작성성공");
location.href="mylist.jsp";
</script>
<%
}else {
%>
<script type="text/javascript">
alert("작성실패");
location.href="mylist.jsp";
</script>
<%
}
%>


</body>
</html>
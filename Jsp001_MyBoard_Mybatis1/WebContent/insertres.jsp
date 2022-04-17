
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
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = new MyBoardDto();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	int res= dao.insert(dto);
	
	if (res>0){
%>
	<script type="text/javascript">
	alert("글작성 성공");
	location.href="mylist.jsp";
	</script>
<%
	}else{
%>	
	<script type="text/javascript">
	alert("글작성 실패");
	location.href="insert.jsp";
	</script>
<%
	}
%>

</body>
</html>
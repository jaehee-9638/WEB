

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
<!-- 성공 실패 결과 rs받아서 확인해야하나 ?  -->
<!--dao에서res받고 update에서  변경한 내용 가져오자 그래서 저장소에 넣고  -->
<% 
	int seq =Integer.parseInt(request.getParameter("seq"));
	String title =request.getParameter("title");
	String content = request.getParameter("content");
	System.out.println(seq);
	System.out.println(title);	//얘 지금 null이다
	System.out.println(content);	//얘도 null
	MyBoardDto dto = new MyBoardDto(seq,null,title,content,null);
	MyBoardDao dao = new MyBoardDao();
	int res =dao.update(dto);
	if (res>0){
%>
	<script type="text/javascript">
	alert("수정성공");
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
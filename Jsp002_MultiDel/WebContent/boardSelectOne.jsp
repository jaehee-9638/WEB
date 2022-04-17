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
	//biz에서 selectone불러오자 
	MDBoardBiz biz = new MDBoardBizImpl();
	MDBoardDto dto = biz.selectOne(seq);
%>
	<table>
	
			<col width="100px"/>
			<col width="200px"/>
		
		<tr>
			<th>글번호</th>
			<td><%=dto.getSeq() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=dto.getContent() %></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=dto.getRegdate() %></td>
		</tr>
	</table>
	<input type ="button" value ="목록으로" onclick="location.href='boardlist.jsp'">
	<input type ="button" value ="수정" onclick="location.href='boardUpdate.jsp?seq=<%=dto.getSeq() %>'">
	<input type ="button" value ="삭제" onclick="location.href='boardDelete.jsp?seq=<%=dto.getSeq() %>'">
</body>
</html>
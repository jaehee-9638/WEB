<%@page import="com.board.biz.MDBoardBizImpl"%>
<%@page import="com.board.biz.MDBoardBiz"%>
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
	int seq = Integer.parseInt(request.getParameter("seq"));
	
	
	MDBoardBiz biz = new MDBoardBizImpl();
	MDBoardDto dto = new MDBoardDto();
	dto=biz.selectOne(seq);
%>

	<table>
		<tr>
			<th>글번호</th>
			<td><%=dto.getSeq()%></td>
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
		
		<tr>
			<td colspan="2">
				<input type ="button" value ="글 수정" onclick="location.href='update.jsp?seq=<%=dto.getSeq()%>'"/>
				<input type="button" value ="글삭 제" onclick ="location.href='delete.jsp?seq=<%=dto.getSeq()%>'">
			</td>
		</tr>
	</table>

</body>
</html>
<%@page import="java.util.List"%>
<%@page import="com.board.biz.MDBoardBiz"%>
<%@page import="com.board.biz.MDBoardBizImpl"%>
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
<script type="text/javascript">
function allCheck(allCheck){
	var checkboxes = document.getElementsByName("chk");
	checkboxes.forEach((checkbox)=>{
		checkbox.checked=allCheck.checked;
	})
	
}
</script>
</head>
<body>
<%


MDBoardBiz biz= new MDBoardBizImpl();
List<MDBoardDto> list = biz.selectList();

%>
<form action="./muldel.jsp" method="post">

<table>
	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>내용</th>
		<th>작성일</th>
		<th><input type="checkbox" name ="all" onclick="allCheck(this)"></th>
	</tr>

<%
for (MDBoardDto dto :list ){
%>
	<tr>
		<td><%=dto.getSeq()%></td>
		<td><%=dto.getWriter() %></td>
		<td><a href="selectOne.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
		<td><%=dto.getContent() %></td>
		<td><%=dto.getRegdate() %></td>
		<td><input type="checkbox" name ="chk" value="<%=dto.getSeq()%>"></td>
	</tr>
<%
}
%>
	<tr>
		<td>
			<input type ="button" onclick="location.href='insert.jsp'" value ="글작성"/>
			<input type ="submit" value ="선택삭제">
		</td>
	</tr>
</table>
</form>
</body>
</html>
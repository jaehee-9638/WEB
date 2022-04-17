<%@page import="java.util.List"%>
<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function allCheck(allCheck){
	var checkboxes = document.getElementsByName("chk");
	checkboxes.forEach((checkbox) =>{
		checkbox.checked=allCheck.checked ;
	})
}
</script>
</head>
<body>
<%
	List<MVCBoardDto> list =(List<MVCBoardDto>) request.getAttribute("list");
%>	
<form action="myservlet.do" method ="post">
<input type="hidden" name ="command" value ="multidelete">
	<table>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성일</th>
			<th><input type ="checkbox" name="all" onclick="allCheck(this)"></th>
		</tr>
<%for (MVCBoardDto dto :list){ %>
		<tr>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><a href ="myservlet.do?command=selectOne&seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getContent() %></td>
			<td><%=dto.getRegdate() %></td>
			<td><input type ="checkbox" name="chk" value ="<%=dto.getSeq() %>"></td>
		</tr>
<%} %>
		<tr>
			<td colspan="5">
				<input  type ="button" value ="글작성" onclick="location.href='myservlet.do?command=insert'">
				<input  type ="submit" value ="선택삭제 ">
			</td>
		</tr>
	</table>
</form>	
</body>
</html>
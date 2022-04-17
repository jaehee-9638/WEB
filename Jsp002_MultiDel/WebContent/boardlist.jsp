<%@page import="com.board.biz.MDBoardBiz"%>
<%@page import="com.board.biz.MDBoardBizImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.board.dto.MDBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%	response.setContentType("text.html; charset=UTF-8");%>
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
	MDBoardBiz biz = new MDBoardBizImpl();
	List<MDBoardDto> list = biz.selectList();
	
%>
	
	
<form action="muldel.jsp" method="post">
	<table>
		
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>
				<th><input type ="checkbox" name="all" onclick="allCheck(this)"></th>
			</tr>
		
		
<%
if (list.size()==0){
%>		
		<tr >
			<td colspan="4">--작성된 글이 없습니다.--</td>
		</tr>
<%	
	}else {
		for (MDBoardDto dto :list){
%>		
		
			<tr>
				<td><%=dto.getSeq() %></td>
				<td><%=dto.getWriter() %></td>
				<td><a href ="boardSelectOne.jsp?seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
				<td><%=dto.getContent() %></td>
				<td><%=dto.getRegdate() %></td>
				<td><input type ="checkbox" name="chk" value="<%=dto.getSeq() %>"></td>
				<!-- 전체삭제도 가능하도록 한다.  -->
			</tr>
		
	
	
<%
		} 	//for문 닫히는태그
%>
	</table>
	<input type ="submit" value="선택삭제">
	<input type ="button" onclick="location.href='boardInsert.jsp'" value="글작성" >
	</form>	
	
<%
		
	}	//작성된글 있는지없는지 확인하고 닫히는 태그 
%>
	
</body>
</html>
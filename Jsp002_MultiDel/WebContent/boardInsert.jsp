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
 글작성페지이 입니다. 
 <form action="boardInsertres.jsp" method ="post">
 	<table>
	
			<col width="100px"/>
			<col width="200px"/>
		
		
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content"></td>
		</tr>
		
	</table>
	<input type="submit" value ="작성" >
 </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
int seq =Integer.parseInt(request.getParameter("seq"));//SEQ받았는데
%>
<form action="myController.jsp" method="post">
<input type="hidden" name ="command" value ="updateform">
<input type="hidden" name ="seq" value ="<%=seq%>">
	<table>
		
		<tr>
			<th>작성자</th>
			<td></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value =""></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content" value =""></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type ="submit" value ="글작성">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
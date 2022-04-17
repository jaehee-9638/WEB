<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시판 글작성 페이지입니다.</h3>

<form method="post" action="writeAction.jsp">
<table border="1">
	<tr>
		<th><input type="text" placeholder="글제목" name="board_title" maxlength="50"></th>
	</tr>
	<tr>	
		<td><textarea rows="10" cols="10"placeholder="글내용"name="board_content"maxlength="2048"></textarea></td>
	</tr>
	
</table>
<input type="submit" value="글쓰기" >
</form>
</body>
</html>
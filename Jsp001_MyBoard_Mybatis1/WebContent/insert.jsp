<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	글작성 페이지입니다
	 폼테크로 post방식으로 res로 보내자 
	 <form action="insertres.jsp" method="post">
	 	작성자<input type = "text" name="writer"><br/>
	 	제목<input type = "text" name="title"><br/>
	 	내용<input type = "text" name="content">
	 	
	 	<input type="submit" value ="글작성">
	 </form>
	 
</body>
</html>
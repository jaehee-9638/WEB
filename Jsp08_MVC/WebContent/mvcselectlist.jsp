<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<table border="1">
	<col width="100">
	<col width="100">
	<tr>
		<th>작성자</th>
		<td>${dto.writer }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.title }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly">${dto.content }</textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="button" value="수정" onclick="location.href='mvcc.do?command=updateform&seq=${dto.seq}'">
			<input type="button" value="삭제" onclick="location.href='mvcc.do?command=delete&seq=${dto.seq}'">
			<input type="button" value="목록" onclick="mvcc.do?commad=list">
		</td>
	</tr>
</table>

</body>
</html>
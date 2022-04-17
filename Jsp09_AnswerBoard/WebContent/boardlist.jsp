<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
	<col width="50px">
	<col width="500px">
	<col width="100px">
	<col width="100px">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		
	</tr>
	<c:choose>
		<c:when test="${empty list }">
			<tr>
				<td colspan="4" align="center">--------------작성된 글이 없습니다.-----------------</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.boardno }</td>
					<td>
						<c:forEach begin="1" end="${dto.titletab }">
							&nbsp;
						</c:forEach>
						<a href="answer.do?command=detail&boardno=${dto.boardno }">${dto.title }</a>
					</td>
					<td>${dto.writer }</td>
					<td>${dto.regdate }</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="4">
			<input type="button" value="글작성" onclick="location.href='answer.do?command=insertform'" >
			<input type="button" value="로그인" onclick="" >
		</td>
	</tr>
</table>

</body>
</html>
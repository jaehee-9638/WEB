
<%@page import="com.board.dto.BoardDto"%>
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
<link href="resources/css_link/board_selectone.css" rel="stylesheet" type="text/css" />

</head>
<body>


<div class="main-wrap">



		

		<div id="body">

			<div class="table">
<%-- <%
	BoardDto dto = (BoardDto)(request.getAttribute("dto"));
%> --%>
<c:set var="dto" scope="request" value="${dto }"  ></c:set>

				<table>
					<tr>
						<th>제목</th>
						<td>${dto.freecomm_title }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${dto.freecomm_id }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${dto.freecomm_regdate }</td>
					</tr>
					<tr class="content">
						<th>내용</th>
						<td>${dto.freecomm_content }</td>
					</tr>


				</table>
	<div id="bottom-div">
				<br>
				<div class="button" >
					<input type="button" onclick="location.href='board.do?command=delete&freecomm_seq=${dto.freecomm_seq }'" value="삭제">
				</div>
				<div class="button">
					<input type="button" onclick="location.href='board.do?command=updateres&freecomm_seq=${dto.freecomm_seq }'" value="수정">
				</div>
				<div class="button">
				<button onclick="location.href='board.do?command=boardlist'">목록</button>
				</div>
	</div>
			</div>


		</div>
<!-- 
<script type="text/javascript">

	function update() {
		var inputPw = prompt("비밀번호를 입력해 주세요:");
		document.getElementsByName("freecomm_pw")[1].value = inputPw;
		document.updateform.submit();
	}
	
	function deleteres() {
		var inputPw = prompt("비밀번호를 입력해 주세요:");
		document.getElementsByName("freecomm_pw")[0].value = inputPw;
		document.deleteform.submit();
	}
	
	

</script>

 -->
	</div>

	


</body>
</html>
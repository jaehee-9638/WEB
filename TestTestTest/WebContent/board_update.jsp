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


<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<link href="resources/css_link/board_update.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function() {
	//썸머노트 사이즈 지정
	  $('#summernote').summernote({
	        height : 200, // 에디터 높이
	        minHeight : 200, // 최소 높이
	        maxHeight : 200, // 최대 높이
	        focus : true, // 에디터 로딩후 포커스를 맞출지 여부
	        lang : "ko-KR", // 한글 설정
	        placeholder : '최대 500자까지 작성 가능하며 욕설이나 비방글 부적절한 내용은 삭제 될 수 있습니다.' //placeholder 설정
	    });	

</script>


</head>
<body>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%-- <%

	BoardDto dto =(BoardDto)(request.getAttribute("update"));
	
%> --%>
<c:set var="dto" scope="request" value="${update }"></c:set>



	<div class="main-wrap">

	
	
		<form action="board.do?command=updateform" method="post" >
			<input type="hidden" name="freecomm_seq" value="${dto.freecomm_seq }">
	<div id="body">
		<div id="table">
	
			<div class="list">작성자 : </div><div class="text"><input name="freecomm_id" type="text" readonly="readonly"  value="${dto.freecomm_id }" ></div>
			<!-- placeholder="name" -->
			<div class="list">작성일 : </div><div class="text"><input id="sysdate" type="text" readonly="readonly" value="${dto.freecomm_regdate }"/></div>
			<!-- placeholder="sysdate" -->
			<div class="list">제목 : </div><div class="text"><input name="freecomm_title" type="text"  value="${dto.freecomm_title }"/></div>
			<!-- placeholder="title" -->
			<div class="list">내용 : </div><div class="text"><textarea name="freecomm_content" rows="10" cols="73" id="summernote">${dto.freecomm_content }</textarea></div>
			<!--  -->
			
			
		</div>
		<div id="update-button">
				<input type="submit" value="수정완료"/>
				<input type="button" value="취소" onclick="">
		</div>
	</div>
</form>
	
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>




</body>
</html>
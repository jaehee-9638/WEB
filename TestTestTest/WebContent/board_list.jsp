<%@page import="java.util.ArrayList"%>
<%@page import="com.board.biz.BoardBiz"%>
<%@page import="com.board.dao.BoardDao"%>
<%@page import="java.util.List"%>
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
<link href="resources/css_link/board_list.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js">
	
	
	$(function allCheck(bool){
	
		var chks = document.getElementsByName("chk");
		for(var i = 0; i < chks.length; i++) {
			chks[i].checked = bool;
		}
	});
	//체크한 글 없으면 submit 이벤트 취소 
	$(function() {
		// muldelform이라는 id를 가진 태그(form 태그)에서 submit 이벤트가 발생 시
		$("#muldelform").submit(function(){
			// 유효성 검사
			if($("muldelform input:checked").length == 0) {
				alert("하나 이상 체크해 주세요");
				
				// submit 이벤트가 중지(이벤트 전파 막기)
				return false;
			}
		};
	});
	
</script>


</head>
<body>


<%@ include file="/WEB-INF/views/include/header.jsp" %>
<div class="main-wrap">

		


	<div id="body">
		<div class="search">
		
			<!-- f는 key 제목,작성자는 value로 전달 -->
			<select name="f">
				<option value="freecomm_title">제목</option>
				<option value="freecomm_id">작성자</option>
			</select>
			
			 <input id="member_id" onkeyup="searchFunction()" type="text" placeholder="검색어 입력(아이디로 검색)" name="q" value="">
			
				<button onclick="searchFunction();">검색</button>
			 
			
		</div>
		
		<form action="board.do?command=multidelete" method="post" id="muldelform">
		<div class="table">
			<table class="styled-table">
		
				
				<col width="200px" />
				<col width="500px" />
				<col width="400px" />
				<col width="200px" />
				<col width="100px" />
					<tr>
						<td colspan="5">총 게시글 갯수: </td>
					</tr>
					
					<tr>
						
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일자</th>
						<th><input type="checkbox" name="all" onclick="allCheck(this.checked);"/></th>
					</tr>
			<%-- 	<%
			BoardBiz biz=new BoardBiz();
			List<BoardDto> list = biz.selectAllList();
			for (BoardDto dto :list){
			%>	--%>
			
					
				
				<c:forEach var="dto" items="${list }" >
					
				
					<tr>
						
						<td>${dto.freecomm_seq }</td>
						<td><a href="board.do?command=selectOneByTitle&freecomm_seq=${dto.freecomm_seq }">${dto.freecomm_title }</a></td>
						<td>${dto.freecomm_id }</td>
						<td>${dto.freecomm_regdate }</td>
						<td><input type="checkbox" name="chk"  value="${dto.freecomm_seq }"></td>
						
					</tr>
					
				</c:forEach>
		<%-- <%		}	%>	 --%>	
				
			</table>
			</div>
			<div > <!-- 페이징 -->
				<a>이전</a>
				<button onclick="alter('이전페이지 없음');">이전</button>
			
				<ul>	<!-- 여기안에 숫자 들어오게 -->
					
				</ul>
			<div >
				<span>다음</span>
				<span>다음 페이지 없습</span>
			</div>
			
		</div>
		<div class="input-button">

				<input type="submit" value="선택삭제" >
			<input type="button" value="글작성" onclick="location.href='board.do?command=boardinsertres'" style="text-align:right;"/>
				
		</div>
		
		</form>
		
		
		<div class="paging">
		<%--나중에 인클루드  --%>
			</div>
		 
		
		
		<!--
		<jsp:include page="/paging/paging.jsp" flush="true" >
		    <jsp:param name="page" value="${paging.page}" />
		    <jsp:param name="beginPage" value="${paging.beginPage}" />
		    <jsp:param name="endPage" value="${paging.endPage}" />
		    <jsp:param name="displayRow" value="${paging.displayRow}" />
		    <jsp:param name="displayPage" value="${paging.displayPage}" />
		
			    
		</jsp:include>
		-->
	

			
	

	</div>
	
	

	
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>


</body>
</html>
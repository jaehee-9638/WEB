<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function idChkProc(){
	var chk = document.getElemetsByName("myid")[0].title;
	if (chk=="n"){
		alert("중복체크 해주세요")
		document.getElemetByName("myid")[0].focus();
	}
}

function idChk(){
	var myid = document.getElementsByName("myid")[0].value;
	if (myid ==null || myid.trim()==""){
		alert("id를 입력해주세요")
	}else {
		open(location.href="loginController.jsp?command=idchk&myid="+myid,"","width:200, height:200");
	}
}
</script>

</head>
<body>
	<form action="loginController.jsp" method ="post">
	<input type ="hidden" name="command" value ="registForm">
		<table>
			<tr>
				<th>ID</th>
				<td>
					<input type="text" name="myid" title="n"> 
					<input type="button" value ="중복체크" onclick="idChk()">
				</td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="text" name="mypw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="myname"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="myaddr"></td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td><input type="text" name="myphone"></td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td><input type="text" name="myemail"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value ="가입">
				</td>
			</tr>
		</table>
	</form>


	회원가입을 마치신 후엔 로그인 화면으로 이동합니다. 로그인 해주세요!
</body>
</html>
<%
int x=3;
int y=4;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input {
		width:50px;
		height:50px;
	}
	.output{
		height:50px;
		background: #e9e9e9;
		font-size:24px;
		font-weight:bold;
		text-align:right;
		padding :0px 5px;
	}
</style>
</head>
<body>
	

	<!-- 페이지에 보이는 부분 + - 선택 버튼 있어야하고 폼태그로 calc.java로 넘겨주자  -->

	<form action ="calc3" method="post">
		<table>
			<tr>
				<td class="output" colspan="4">${3+4}  </td>
			</tr>
			<tr>
				<td><input type="submit" value="CE" name="operator"></td>
				<td><input type="submit" value="C" name="operator"></td>
				<td><input type="submit" value="BS" name="operator"></td>
				<td><input type="submit" value="/" name="operator"></td>
			</tr>
			<tr>
				<td><input type="submit" value="7" name="value"></td>
				<td><input type="submit" value="8" name="value"></td>
				<td><input type="submit" value="9" name="value"></td>
				<td><input type="submit" value="*" name="operator"></td>
			</tr>
			<tr>
				<td><input type="submit" value="4" name="value"></td>
				<td><input type="submit" value="5" name="value"></td>
				<td><input type="submit" value="6" name="value"></td>
				<td><input type="submit" value="-" name="operator"></td>
			</tr>
			<tr>
				<td><input type="submit" value="1" name="value"></td>
				<td><input type="submit" value="2" name="value"></td>
				<td><input type="submit" value="3" name="value"></td>
				<td><input type="submit" value="+" name="operator"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="0" name="value"></td>
				<td><input type="submit" value="." name="dot"></td>
				<td><input type="submit" value="=" name="operator"></td>
			</tr>
		</table>
		
	</form>

</body>
</html>
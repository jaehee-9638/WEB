<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String sql = " SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM EMP "; 
	Class.forName("oracle.jdbc.driver.OracleDriver");

	Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	while (rs.next()){
	
%>
	

	<table>
		<thead>
			<tr>
				<th>사원번호</th>
				<th>이름</th>
				<th>직업</th>
				<th>매니저번호</th>
				<th>고용일</th>
				<th>월급</th>
				<th>부서번호</th>
			</tr>
		</thead>
		
		
		<tbody>
			<tr>
				<td><%=rs.getInt(1) %></td>
				<td><%=rs.getString(2) %></td>
				<td><%=rs.getString(3) %></td>
				<td><%=rs.getInt(4) %></td>
				<td><%=rs.getDate(5) %></td>
				<td><%=rs.getInt(6) %></td>
				<td><%=rs.getInt(7) %></td>
				<td><%=rs.getInt(8) %></td>
				
			</tr>
		</tbody>
		
	</table>



<%
	}
	rs.close();
	stmt.close();
	con.close();
%>

</body>
</html>
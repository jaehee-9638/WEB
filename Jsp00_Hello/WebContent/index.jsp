
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

	<table border="1">
	
<%
		//자바영역 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="kh";
		String password="kh";
		Connection con=null;
		
		con=DriverManager.getConnection(url,user,password);
		
		String sql=" SELECT EMPNO,ENAME,JOB,MGR,HIREDATE, SAL, COMM, DEPTNO "
					+" FROM EMP ";
		
		Statement stmt=null;
		ResultSet rs=null;
		stmt=con.createStatement();
		
		rs=stmt.executeQuery(sql);
		
		while(rs.next()){
			
		
		
		

%>	
<!-- html 영역 -->
		<tr>
			<td><%=rs.getInt(1) %></td>
			<td><%=rs.getString(2) %></td>
			<td><%=rs.getString(3) %></td>
			<td><%=rs.getInt(4) %></td>
			<td><%=rs.getDate(5) %></td>
			<td><%=rs.getDouble(6) %></td>
			<td><%=rs.getDouble(7) %></td>
			<td><%=rs.getInt(8) %></td>
		</tr>



<%

		}
		rs.close();
		stmt.close();
		con.close();




%>
	
	
	
	</table>

</body>
</html>
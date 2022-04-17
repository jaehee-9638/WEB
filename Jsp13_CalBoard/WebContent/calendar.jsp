<%@page import="com.cal.common.Util"%>
<%@page import="com.cal.dto.CalDto"%>
<%@page import="java.util.List"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	#calendar {
		border-collapse: collapse;
		border: 1px solid gray;
		
	}
	#calendar th {
		width: 80px;
		border: 1px solid gray;
	}
	#calendar td {
		width: 80px;
		height: 80px;
		border: 1px solid gray;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	a {
		text-decoration: none;
	}
	.list > p {
		font-size: 5px;
		margin: 1px;
		background-color: skyblue;
	}
	.preview {
		position: absolute;
		top: -30px;
		lefg: 10px;
		background-color: skyblue;
		width: 40px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		border-radius: 40px 40px 40px 1px;
	}

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>

<%
	Calendar cal=Calendar.getInstance();

	int year =cal.get(Calendar.YEAR);	//현재 날짜 뽑아내서 값 넣어둔 변수 
	int month = cal.get(Calendar.MONTH) + 1;
	
	String paramYear=request.getParameter("year");	
	String paramMonth=request.getParameter("month");
	if(paramYear != null) {
		year = Integer.parseInt(paramYear);
	} 
	if(paramMonth != null) {	
		month = Integer.parseInt(paramMonth);
	}
	if(month > 12) {
		month = 1;
		year++;
	}
	if(month < 1) {
		month = 12;
		year--;
	}
	// 년, 월, 일
		cal.set(year, month-1, 1);
		// 0 : 일요일 ~ 6 : 토요일
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		// 해당월의 마지막 날짜를 구해준다.
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		CalDao dao = new CalDao();
		String yyyyMM = year + Util.isTwo(String.valueOf(month));
		List<CalDto> list = dao.getCalViewList("kh", yyyyMM);
%>

</body>
</html>
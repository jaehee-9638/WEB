package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	
	//calcPage.java안에 service의 내용 그대로 doget메소드 안에 넣었음 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String exp="0";
		if(cookies!=null) {
			for (Cookie c:cookies) {
				if (c.getName().equals("exp")) {
					exp=c.getValue();
					break;
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style type=\"text/css\">");
		out.write("input {");
		out.write("width:50px;");
		out.write("height:50px;");
		out.write("}");
		out.write(".output{");
		out.write("height:50px;");
		out.write("background: #e9e9e9;");
		out.write("font-size:24px;");
		out.write("font-weight:bold;");
		out.write("text-align:right;");
		out.write("padding :0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");

		out.write("<form method=\"post\">");	//현재페이지의 url이 post와 같다면 action속성 필요없음 
		out.write("<table>");
		out.write("<tr>");
		out.printf("<td class=\"output\" colspan=\"4\">%s </td>",exp);
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" value=\"CE\" name=\"operator\"></td>");
		out.write("<td><input type=\"submit\" value=\"C\" name=\"operator\"></td>");
		out.write("<td><input type=\"submit\" value=\"BS\" name=\"operator\"></td>");
		out.write("<td><input type=\"submit\" value=\"/\" name=\"operator\"></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" value=\"7\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"8\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"9\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"*\" name=\"operator\"></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" value=\"4\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"5\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"6\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"-\" name=\"operator\"></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" value=\"1\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"2\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"3\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\"+\" name=\"operator\"></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td></td>");
		out.write("<td><input type=\"submit\" value=\"0\" name=\"value\"></td>");
		out.write("<td><input type=\"submit\" value=\".\" name=\"dot\"></td>");
		out.write("<td><input type=\"submit\" value=\"=\" name=\"operator\"></td>");
		out.write("</tr>");
		out.write("</table>");
				
		out.write("</form>");

		out.write("</body>");
		out.write("</html>");
		
	}
	//calc3.java안에 serviec의 내용 그대로 dopost메소드 안에 넣었음 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies(); //배열형태로 cookie받기 

		String value = request.getParameter("value");	//value받기
		String operator = request.getParameter("operator");	//op받기
		String dot =request.getParameter("dot");	//dot 받기
		
		String exp="";	//exp초기화
		if(cookies!=null) {	//배열로 받은 cookie가 null이 아니면 
			for (Cookie c:cookies) { 	//향상된 for 문으로cookie객체 c로 넣기 
				if (c.getName().equals("exp")) { 	//c객체에서 dlfmadl exp인애 있으면 
					exp=c.getValue(); //exp안에 그 값 넣는다. 
					break;
				}
			}
		}
		//계산
		//이부분은 강의 듣고 주석 적기 
		if (operator !=null && operator.equals("=")) {	//op가 null이아니고 =이면 
			ScriptEngine engine=new ScriptEngineManager().getEngineByName("nashorn");
			//ScriptEngine객체(자바에서 자바스크립트 이용할수있는 엔진) 사용해서 ,,그냥 엔진 불러내는 과정임 
			try {
				exp=String.valueOf(engine.eval(exp));	//엔ㅈ
				//eval함수는 주의해서 사용,잘 사용안한다 : 문자열을 입력받아 그 문자열을 expression으로 처리한 후 결과값을 반환하는 함수
				//expression : 값,변수,연산자,함수 모임 . 
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		//C가 들어오면 쿠키 삭제 되도록 
		else if (operator !=null && operator.equals("C")) {
			exp="";
		}
		
		//값 저장 ?
		else {
			exp +=(value==null)?"":value; //삼함연산 null이 아니면 누적하는 형태임 
			exp +=(operator==null)?"":operator; 
			exp +=(dot==null)?"":dot; 
		}
		Cookie expCookie =new Cookie("exp",exp);
		if (operator != null&&operator.equals("C")) {
			expCookie.setMaxAge(0);	// 쿠키가 소멸되도록 하는 함수 
		}
		expCookie.setPath("/calculator");	//패스요청은 1개의 경로로만 가능함 
		response.addCookie(expCookie);
		response.sendRedirect("calculator");	//자기가 자기를 호출하더라도 넣어줘야함 
	}
	
	
	
	//service함수안에 super.service(request, response);는		//부모가 가지고있ㄴㄴ 서비스 함수 호출해주는거 
	//doGet이나 doPost가 오버라이드 되어있지 않은 상태면 얘가 호출된다. 
	//선택 1 . service를 오버라이드 해서 get post여기서 처리
	//선택 2 . service를 오버라이드 하지않고 doGet이나 doPost 오버라이드 
	//405에러는 url은 잇는데 처리하려는 메소드 없음 
	//만약 do post한번에 처리하고 싶으면 service함수 만들고 super.sevice메소드는 없이 하자 
	
	
}

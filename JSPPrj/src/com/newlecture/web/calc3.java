package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationContext;

/**
 * Servlet implementation class calc
 */
@WebServlet("/calc3")
public class calc3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		expCookie.setPath("/calc3");	//패스요청은 1개의 경로로만 가능함 
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
		

	}

}

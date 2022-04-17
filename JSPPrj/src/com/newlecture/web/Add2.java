package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Add2")
public class Add2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//먼저 배열의 갯수 찾은다음에/ 그 배열만 큼 값 가져온다. /그다음 정수형으로만들고 연산 
		
		String[] num_=request.getParameterValues("num");
		int result=0;
		for (int i=0; i<num_.length; i++) {
			int num = Integer.parseInt(num_[i]);
			result +=num;
		}
		
		
		response.getWriter().println("덧셈결과는 "+result+"입니다");
			
			
			
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}

}

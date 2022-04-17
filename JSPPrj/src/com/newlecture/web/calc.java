package com.newlecture.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class calc
 */
@WebServlet("/calc")
public class calc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String x_=request.getParameter("x");
		String y_=request.getParameter("y");
		String ty=request.getParameter("button");
		System.out.println(x_);
		int x=10;
		int y=10;
		
		if (x_!=null && !x_.equals("")) {x=Integer.parseInt(x_);}
		if (y_!=null && !y_.equals("")) {y=Integer.parseInt(y_);}
		
		int result =0;
		
		if (ty.equals("뺄셈")) {
			result =x-y;
			
		}else { 
			result=x+y;
			
		}
		
		response.getWriter().println("연산결과는"+result+"입니다.");
	}

}

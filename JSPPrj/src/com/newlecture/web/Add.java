package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Add")
public class Add extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String x_=request.getParameter("x");
		String y_=request.getParameter("y");

		/*
		 * if (first !=null && !first.endsWith("") ) { first_= Integer.parseInt(first);
		 * } if (second !=null && !second.endsWith("") ) { second_=
		 * Integer.parseInt(first); }
		 */
		
		int x=10;
		int y=10;
		
		if(x_!=null && !x_.equals("")) {
			x=Integer.parseInt(x_);
		}
		if(y_!=null && !y_.equals("")) {
			y=Integer.parseInt(y_);
		}
		
		//int first =Integer.parseInt( request.getParameter("fir"));
		//int second =Integer.parseInt(request.getParameter("sec"));
		
		
			
		int sum = x+y;
		
		
		
		response.getWriter().println("덧셈결과는 "+sum+"입니다");
			
			
			
			
			
			
			
			
	
	}

}

package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servletController
 */
@WebServlet("/add")
public class servletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		
		int x= Integer.parseInt(request.getParameter("x"));
		int y= Integer.parseInt(request.getParameter("y"));
		int sum = x+y;
		
		
		response.getWriter().printf("sum is"+sum);
	}

	

}

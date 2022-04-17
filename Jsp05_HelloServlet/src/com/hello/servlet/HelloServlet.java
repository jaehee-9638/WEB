package com.hello.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller.do")
public class HelloServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	private String initParam;
	private String contextParam;
	

	public HelloServlet() {
		System.out.println("서블릿 생성자");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("서블릿 인잇");
		
		contextParam=config.getServletContext().getInitParameter("name");
		initParam=config.getInitParameter("sports");
		
		System.out.println("contextParam:"+contextParam);
		System.out.println("initParam:"+initParam);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("get방식으로 들어옴 ");
		String command= request.getParameter("command");
		System.out.println("command:"+command);
		
		//PrintWriter은 
		PrintWriter out=response.getWriter();
		out.print("<h1 style='color: red'>Hello Servlet</h1>");
		out.print("<h2>계층구조, lifecycle, url-mapping</h2>");
		out.print("<a href='home.html'>home...</a>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("post방식으로 들어옴 ");
		String command= request.getParameter("command");
		System.out.println("command:"+command);
		
		PrintWriter out=response.getWriter();
		out.println("<h1 style='color: blue'>Hello Servlet</h1>");
		out.println("<h2>init - service - doGet/doPost");
		out.println("<a href='home.html'>home...</a>");
	}

	@Override
	public void destroy() {
		System.out.println("servlet destroy");
	}

	

}

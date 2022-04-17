package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestId=request.getAttribute("requestId")+"";
		HttpSession session=request.getSession();
		String SessionId=session.getAttribute("sessionId")+"";
		ServletContext application=getServletContext();
		String applicationId=application.getAttribute("applicationId")+"";
		
		System.out.println("requestId"+requestId);
		System.out.println("sessionId"+SessionId);
		System.out.println("applicationId"+applicationId);
		
		String myRequest=request.getParameter("myRequest");
		System.out.println("myRequest:"+myRequest);
		
		PrintWriter out=response.getWriter();
		String html ="<h1>응답</h1>"
					+"<table border='1'>"
					+"<tr>"
						+"<th>request</th>"
						+"<td>"+myRequest+"</td>"
					+"</tr>"
					+"<tr>"
						+ "<th>session</th>"
						+ "<td>" + SessionId + "</td>"
					+"</tr>"
					+ "<tr>"
						+ "<th>applicaiton</th>"
						+ "<td>" + applicationId + "</td>"
					+ "</tr>"
				+ "</table>";
		out.println(html);
		
		RequestDispatcher dispatch=request.getRequestDispatcher("result.jsp");
		dispatch.forward(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}

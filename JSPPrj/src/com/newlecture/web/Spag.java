package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/spag")
public class Spag extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		int num=0;
		String num_= request.getParameter("no");
		if (num_ !=null && !num_.equals("")){
			num =Integer.parseInt(num_);
		}
		String model ="짝수일까 홀수일까";
		if (num %2==0){
		 	 model ="짝수입니다.";
		}else{ 
			 model ="홀수입니다.";
		}
		request.setAttribute("result", model);
		
		String []names= {"new","dragon"};
		request.setAttribute("names", names);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",1);
		map.put("title","EL좋아");
		request.setAttribute("map", map);
		RequestDispatcher dispatcher=request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
	
}

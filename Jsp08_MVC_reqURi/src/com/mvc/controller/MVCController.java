package com.mvc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MVCBoardBiz;
import com.mvc.biz.MVCBoardBizImpl;
import com.mvc.dto.MVCBoardDto;


@WebServlet(urlPatterns= {"/selectlist","/selectone"})
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MVCBoardBiz biz;
    
	private void getRequestUri(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		biz=new MVCBoardBizImpl();
		
		String command=request.getRequestURI();
		System.out.println("["+command+"]");
		
		if (command.endsWith("/selectlist")) {
			doSelectList(request,response);
		}else if(command.endsWith("/selectone")) {
			doSelectOne(request,response);
		}
		
	}
	
	private void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq=Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch(request,response,"mvcselectone.jsp");
	}
	private void doSelectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MVCBoardDto> list= new ArrayList<MVCBoardDto>();
		request.setAttribute("list", list);
		dispatch(request,response,"mvcselectlist.jsp");
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequestUri(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequestUri(request,response);
	}

	
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatch =request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
	
	
}

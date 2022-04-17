package com.answer.controller;

import java.io.IOException;


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;



@WebServlet("/AnswerController")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AnswerBiz biz=new AnswerBizImpl();
		
		String command=request.getParameter("command");
		System.out.println("["+command+"]");
		
		if (command.equals("list")) {
			List<AnswerDto> list=biz.selectList();
			request.setAttribute("list", list);
			dispatch(request,response,"boardlist.jsp");
		}else if (command.equals("detail")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request,response,"boardselect.jsp");		
		}else if (command.equals("insertform")) {
			response.sendRedirect("boardInsert.jsp");
		}else if (command.equals("insertres")) {
			System.out.println("1");
			String writer =request.getParameter("writer");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			System.out.println("2");
			AnswerDto dto=new AnswerDto();
			//0,0,1,0,writer,title,content,null
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			System.out.println("3");
			int res=biz.boardInsert(dto);
			System.out.println("4");
			if (res>0) {
				System.out.println("5");
				response.sendRedirect("answer.do?command=list");
			}else {
				System.out.println("6");
				response.sendRedirect("answer.do?commad=insertform");
			}
			
			
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	private void dispatch(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
	
	

}

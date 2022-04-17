package com.mvc.controller;

import java.io.IOException;


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

/**
 * Servlet implementation class MVCController
 */
@WebServlet("/MVCController")
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("command : " + command);
		MVCBoardBiz biz = new MVCBoardBizImpl();

		if (command.equals("list")) {
			List<MVCBoardDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch(request,response,"mvclist.jsp");

		}else if (command.equals("selectlist")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto =biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch(request,response,"mvcselectlist.jsp");
		}else if (command.equals("insertform")) {
			response.sendRedirect("mvcinsert.jsp");
			
		}else if (command.equals("insertres")) {
			String writer=request.getParameter("writer");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			MVCBoardDto dto =new MVCBoardDto(0, writer, title, content, null);
			int res=biz.insert(dto);
			if (res>0) {
				response.sendRedirect("mvcc.do?command=list");
			}else {
				response.sendRedirect("mvcc.do?commad=insertform");
			}
		
		}else if (command.equals("updateform")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto =biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch(request,response,"mvcupdate.jsp");
		}else if (command.equals("updateres")){
			int seq=Integer.parseInt(request.getParameter("seq"));
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			MVCBoardDto dto=new MVCBoardDto(seq,null,title,content,null);
			int res=biz.update(dto);
			if(res>0) {
				response.sendRedirect("mvcc.do?command=selectlist&seq="+seq);
			}else {
				response.sendRedirect("mvcc.do?command=updateform&seq="+seq);
			}
			
			
			
		}else if (command.equals("delete")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			int res=biz.delete(seq);
			
			if(res>0) {
				response.sendRedirect("mvcc.do?command=list");
			}else {
				response.sendRedirect("mvcc.do?command=selectlist&seq="+seq);
			}
		}
	}

	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}

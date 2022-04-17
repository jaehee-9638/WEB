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
//@WebServlet("ServletController")
public class ServletController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		MVCBoardBiz biz = new MVCBoardBizImpl();
		
		if (command.equals("list")) {
			List<MVCBoardDto> list =biz.selectList();
			request.setAttribute("list", list);
			dispatch(request, response, "mylist.jsp");
		}else if (command.equals("selectOne")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			System.out.println("seq:"+seq);
			MVCBoardDto dto = biz.selectOne(seq);
			System.out.println("dto담은거"+dto);
			request.setAttribute("dto", dto);
			
			dispatch(request, response, "selectOne.jsp");
		}else if (command.equals("insert")) {
			
			dispatch(request, response, "myinsert.jsp");
		}else if (command.equals("insertform")) {
			String writer=request.getParameter("writer");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			MVCBoardDto dto = new MVCBoardDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res=biz.insert(dto);
			if (res>0) {
				response.sendRedirect("myservlet.do?command=list");
			}else {
				response.sendRedirect("myservlet.do?command=insert");
			}
			
		}else if (command.equals("update")) {
			//셀렉트원 받아서 화면에 뿌리면서 수정준비 
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch(request, response, "myupdate.jsp");
		}else if (command.equals("updateform")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			System.out.println(seq+":"+title+":"+content);
			
			MVCBoardDto dto = new MVCBoardDto ();
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			int res =biz.update(dto);
			if (res>0) {
				//성공
				response.sendRedirect("myservlet.do?command=list");
			}else {
				//실패
				response.sendRedirect("myservlet.do?command=update&seq="+seq);
			}
			
		}else if (command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = biz.delete(seq);
			if (res>0) {
				response.sendRedirect("myservlet.do?command=list");
			}else {
				//실패
				response.sendRedirect("myservlet.do?cammand=list");
			}
		}else if (command.equals("multidelete")) {
			//얘는 일단 리스트에서 보내온걸 받아서 삭제 진행한다. 
			String[] seqs=request.getParameterValues("chk");
			int res =biz.multiDelete(seqs);
			if (res>0) {
				//성공
				response.sendRedirect("myservlet.do?command=list");
			}else {
				//실패
				response.sendRedirect("myservlet.do?command=list");
			}
		}
		
		
	}
	
	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
}

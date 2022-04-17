package com.daytwo.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.biz.BoardBiz;
import com.board.dto.BoardDto;







@WebServlet("/BoardController")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//나중에 추가할것 페이징 , 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command=request.getParameter("command");
		System.out.println("["+command+"]");
		BoardBiz biz = new BoardBiz();
		if (command.equals("boardlist")) {
			
			List<BoardDto> list = new ArrayList<BoardDto>();
				list=	biz.selectAllList();
			
			request.setAttribute("list", list);
			
			//response.sendRedirect("board_list.jsp");
			
			dispatch(request, response, "board_list.jsp");
		}else if (command.equals("boardinsertres")){
			response.sendRedirect("board_insert.jsp");
		}else if (command.equals("boardinsertform")) {
			String id =request.getParameter("freecomm_id");
			
			String title=request.getParameter("freecomm_title");
			String content=request.getParameter("freecomm_content");
			
			BoardDto dto =new BoardDto(0,id,title,content,null, 0);
			
			int res=biz.insert(dto);
			if(res > 0) {
				//성공시 list 로 
				System.out.println("작성성공");
				response.sendRedirect("board.do?command=boardlist");
			} else {	//실패시 boardinsertres 으로 
				System.out.println("작성실패");
				response.sendRedirect("board.do?command=boardinsertres");
			}
		}else if (command.equals("selectOneByTitle")) {
			//일단 번호 받아서 seq로 보냈음 
			int seq=Integer.parseInt(request.getParameter("freecomm_seq"));
			BoardDto dto=biz.selectOneByTitle(seq);
			request.setAttribute("dto", dto);
			dispatch(request,response,"board_selectone.jsp");
			
			
		}else if (command.equals("delete")) {
			int seq= Integer.parseInt(request.getParameter("freecomm_seq"));
			
			int res = biz.delete(seq);
		
			if (res>0) {
				PrintWriter out = response.getWriter();
				String html = "<script type='text/javascript'>" + "alert('" + "글 삭제 성공" + "');"
						+ "location.href='board.do?command=boardlist';" + "</script>";
				out.println(html);
			}else {
				PrintWriter out = response.getWriter();
				String html = "<script type='text/javascript'>" + "alert('글 삭제 실패');"
						+ "location.href='board.do?command=boardlist;" + "</script>";
				out.println(html);
			}
		}else if (command.equals("updateres")) {
			
			int seq = Integer.parseInt(request.getParameter("freecomm_seq"));
			BoardDto dto =biz.selectOneByTitle(seq);
			request.setAttribute("update", dto);
			
			dispatch(request, response, "board_update.jsp");
			
		}else if (command.equals("updateform")) {
			String title =request.getParameter("freecomm_title");
		
			String content=request.getParameter("freecomm_content");
		
			int seq=Integer.parseInt(request.getParameter("freecomm_seq"));
			
			BoardDto dto=new BoardDto();	
			
			dto.setFreecomm_title(title);
			dto.setFreecomm_content(content);
			dto.setFreecomm_seq(seq);
		
			int res=biz.update(dto);
			
			if (res>0) {
				System.out.println("수정성공");
			}else {
				System.out.println("수정실패");	
			}
			
			
		}else if (command.equals("multidelete")) {
			System.out.println("1");
			String[] seq = request.getParameterValues("chk");
			System.out.println("2 seq:"+seq);
			if(seq == null || seq.length == 0) {
				System.out.println("3");
				System.out.println("하나이상체크해라");
			}else {
		  		int res = biz.multidelete(seq);	//배열 형태로 받음 
		  		//변수 res에 다오의 멀딜 호출했는데 0이 나오는건 다오에서 잘못된것 ?
		  		System.out.println("4 res:"+res);//여기서 res=0으로 나옴 
		  		if(res > 0) {
		  			System.out.println("선택삭제성공");
		  		}else {
		  			System.out.println("선택삭제실패");
		  		}
				
				
			}
		}
		
	}
	
	
	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}

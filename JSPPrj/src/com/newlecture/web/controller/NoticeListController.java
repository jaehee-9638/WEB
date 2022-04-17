package com.newlecture.web.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;
@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//컨트롤러의 역할 : 사용자의 요청을 받는것 
		
		
		//전달 안될수도있으니 일단 임시변수(_)로 사용하자  
		String field_=request.getParameter("f");	//f라는 이름으로 된 값 불러옴
		String query_ = request.getParameter("q");	//옵션으로 온 인자 사용할때는 기본값 어케할지 생각해야함  
		String page_=request.getParameter("p");	//int 형도 무조건 string으로 받아서 변환 해주자
		
		String field="title";
		if (field_ !=null && !field_.equals("")) {	//들어온값있으면 초기값에 들어온 값 넣어준다.~
			field=field_;
		}
		String query="";
		if (query_ != null && !query_.equals("")) {
			query=query_;
		}
		int page =1;
		if (page_ != null && !page_.equals("")) {
			page=Integer.parseInt(page_);
		}
		
		NoticeService service =new NoticeService();
		List <Notice> list =service.getNoticeList(field,query,page);
		
		request.setAttribute("list", list);
		
		
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}
}

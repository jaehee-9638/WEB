package com.ajax.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


@WebServlet("/scoreController")
public class ScoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				doPost(request, response);
	}

	//스튜던트에서 보낸 값 받아서 계산해서 콘솔로그에 뿌린다. 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String name =request.getParameter("name");
		int kor=Integer.parseInt(request.getParameter("kor"));
		int eng=Integer.parseInt(request.getParameter("eng"));
		int math=Integer.parseInt(request.getParameter("math"));
		//일단 여기서 student 값 받음 
		int sum=kor+eng+math;
		double avg=(double)sum/3;
		//총점 평균 계산 변수 만들어둠 
		
		//JSONObject에 다른 객체를 넣어서 
		JSONObject obj =new JSONObject();
		obj.put("name", name);
		obj.put("sum", sum);
		obj.put("avg", String.format("%.2f", avg));
		//이부분이 콘솔에 찍힘 
		//.toJSONString() : 메소드가 호출된 결과를 출력한다 -> json형태로 
		System.out.println("server에서 보내는 데이터: " + obj.toJSONString());
		
		PrintWriter out = response.getWriter();
		out.println(obj.toJSONString());
	}

}

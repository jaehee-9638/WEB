package com.newlecture.web.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		String url="jdbc:oracle:thin:@localhost:1521:xe";

		String sql="SELECT * FROM NOTICE WHERE ID=?";	//전부출력인데 아래 IF 문에 넣어준것만 출력된다 

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,"kh","kh");
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setInt(1,id );
			ResultSet rs=pstm.executeQuery();

			rs.next();

			String title=rs.getString("TITLE");
			Date regdate=rs.getTimestamp("REGDATE") ;
			String writerId=rs.getString("WRITER_ID") ;
			int hit=rs.getInt("HIT") ;
			String files=rs.getString("FILES") ;
			String content=rs.getString("CONTENT") ;
			//객체필요 notice객체 만든다 오버로드생성자 이용해서
			Notice notice = new Notice(
					id,
					title,
					regdate,
					writerId,
					hit,
					files,
					content
					//순서 꼭맞췅(notice클래스에서 오버로드 한애랑 )
					);
			
			request.setAttribute("n", notice);
			  /*
			request.setAttribute("title", title);
			request.setAttribute("writerId", writerId);
			request.setAttribute("regdate", regdate);
			request.setAttribute("hit", hit);
			request.setAttribute("files",files );
			request.setAttribute("content", content);
			    */
			    rs.close();
				pstm.close();
				con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
		
	}
}

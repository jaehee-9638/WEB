package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;
//dao crud 진행 
//다하고 출력 return 으로 바꿔서 한번 해보자 
public class NoticeService {
	String path = "oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String user="kh";
	String password="kh";
	//SELECT
	//이전 다음 을 할수있어야하는데 이전 다음하려면 BETWEEN 1 AND 10 에서1과 10을 ?로 두고 
	//CONSOLE ㅇ서
	public List<Notice> select(int page) throws ClassNotFoundException, SQLException {
		
		int start=1+(page-1)*10;
		int end =10*page;
		
		String sql =" SELECT * FROM NOTICE_VIEW_TEST WHERE NUM BETWEEN ? AND ?  ";
		Class.forName(path);
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pstm =con.prepareStatement(sql);
		pstm.setInt(1, start);
		pstm.setInt(2, end);
		ResultSet rs= pstm.executeQuery();
		List<Notice> list=new ArrayList<Notice>();
		while (rs.next()) {	//출력할것 id title writer_id content regdate 
			int id = rs.getInt("ID");// sql문 인자를 넘기기위한
			String title = rs.getString("TITLE");	
			String writerId=rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content=rs.getString("CONTENT");
			int hit =rs.getInt("hit");	
			String files=rs.getString("FILES");
			
			Notice notice = new Notice(
					id,
					title,
					writerId,
					regDate,
					content,
					hit,
					files
					);
			list.add(notice);
			
		}
		rs.close();
		pstm.close();
		con.close();
		
		return list;
		
	}
	//row의수를 출력하는 select문 
	public int getCount() throws ClassNotFoundException, SQLException {
		//여기서는 페이지 몇개 만들지 알아야하기 때문에 전체 로우 갯수 먼저 출력되게 해보자
		int count=0;
		String sql = " SELECT COUNT(ID) COUNT FROM NOTICE ";
		
		Class.forName(path);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//rs.getInt("ID");
		while(rs.next()){
			count = rs.getInt("COUNT");
			}
		//System.out.println(count);
	
		rs.close();
		stmt.close();
		con.close();
		return count;
	}
	
	//INSERT
	public void insert (String title,String writerId,String content) throws ClassNotFoundException, SQLException {
		
		String sql = " INSERT INTO NOTICE (TITLE,WRITER_ID,CONTENT) VALUES(?,?,?) ";
		Class.forName(path);
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, title);
		pstm.setString(2, writerId);
		pstm.setString(3, content);
		int res =pstm.executeUpdate(); 	//res는 결과 return되는 애
		if (res>0) {System.out.println("insert 성공");}
		
		pstm.close();
		con.close();
		
	}
		
	//UPDATE
	public void update(int update_no,String title,String content) throws ClassNotFoundException, SQLException {
		String sql = " UPDATE NOTICE SET TITLE=?,CONTENT=? WHERE ID =? ";
		
		Class.forName(path);
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, title);
		pstm.setString(2, content);
		pstm.setInt(3, update_no);
		
		int res= pstm.executeUpdate();
		if (res>0) {System.out.println(update_no+"행을 수정했습니다.");}
		pstm.close();
		con.close();
		
	}
	
	//DELETE
	public void delete(int delete_no) throws ClassNotFoundException, SQLException {
		String sql =" DELETE NOTICE WHERE ID=? ";
		Class.forName(path);
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, delete_no);
		int res= pstm.executeUpdate();
		if (res>0) {System.out.println(delete_no+"행 삭제 ");}
		pstm.close();
		con.close();
	}

	//기본적인 구성이 program5에서switch menu 선택하면 여기서 진행되는건데 
	//먼저 delete진행을 program 5에서 id  받아서 사용하고싶음 
	//일단 id 로 받기 성공하면 rownum으로 바꿔보자 
}

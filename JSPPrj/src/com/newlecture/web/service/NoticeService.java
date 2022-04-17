package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	//제공할 서비스라 public 으로 
	
	//공지사항 처음 들어갔을때 화면 
	public List<Notice> getNoticeList() {
		
		return getNoticeList("title","",1);
	}
	
	//pager이용해서 특정 페이지 요청 
	public List<Notice> getNoticeList(int page) {
		
		return  getNoticeList("title","",page);
		//기본검색범주를 title로 하고 빈문자열을 검색하면 다 검색된다. 
	}
	
	//검색요청 :field는 검색영역 ,query는 검색어 ,page는 화면에 보일 페이지 
	public List<Notice> getNoticeList(String field/*TITLE, WRITER_ID*/,String query/*A*/,int page) {
		List <Notice> list = new ArrayList<>();	//1.list라는 배열 객체 만들고 
		
		
		String sql =" SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, N.* "
				+ "    FROM ( SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC ) N "
				+ ") "
				+ "WHERE NUM BETWEEN ? AND ? ";
		//query를 sql문에 넣기위해  제일 안쪽 쿼리에 WHERE TITLE LIKE '%A%' 추가해줌
		//여기서 바로 field를 sql로 바로 꽂아넣은 이유는 ?사용 해서 title넣으면 나중에 결합된 내용을 보면 'title'
		//이렇게 들어간다 컬럼명 이기도해서 엉뚱한 내용 나올수 있음 
		//1,11,21,31.....-> 등차수열 an=1+(page-1)*10
		//10,20,30,40 -> page*10
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,"kh","kh");
			PreparedStatement pstm=con.prepareStatement(sql);	//얘가 sql을 가진다는 말은 미리 준비하게 있다는 소리임 
			pstm.setString(1, "%"+query+"%");
			pstm.setInt(2, 1+(page-1)*10);
			pstm.setInt(3, page*10);
			ResultSet rs=pstm.executeQuery();	//3.그 값을 실행
			while(rs.next()){ 	//2.돌면서 
				int id = rs.getInt("ID");	//4. 레코드 채워서 
				String title=rs.getString("TITLE");
				Date regdate=rs.getTimestamp("REGDATE") ;
				String writerId=rs.getString("WRITER_ID") ;
				int hit=rs.getInt("HIT") ;
				String files=rs.getString("FILES") ;
				String content=rs.getString("CONTENT") ;
				
				Notice notice = new Notice(	
						id,
						title,
						regdate,
						writerId,
						hit,
						files,
						content
						
						);
				list.add(notice);	//5.notice에 담아서 
			}



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
		
		return list; 	//6.반환 
	}
	
	//현재페이지의 개수 (노티스가 화면에 보이는 개수가 아니라 전체 레코드 갯수 )
	public int getNoticeCount() {
		
		return  getNoticeCount("title","");
	}
	
	//검색된 전체 레코드 개수 
	public int getNoticeCount(String field,String query) {
		int count=0; 
		String sql =" SELECT COUNT(ID) COUNT FROM ("
				+ "    SELECT ROWNUM NUM, N.* "
				+ "    FROM ( SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC ) N "
				+ ") ";	
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		//페이징을 위한 where 절은 뺐음 그외에는 위에 메소드랑 거의 똑같음 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,"kh","kh");
			PreparedStatement pstm=con.prepareStatement(sql);	//얘가 sql을 가진다는 말은 미리 준비하게 있다는 소리임 
			pstm.setString(1, "%"+query+"%");
			
			ResultSet rs=pstm.executeQuery();	//3.그 값을 실행
			count =rs.getInt("count");	//소문자로 컬럼명해도 영향없음 
			
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
		return count;
	}
	
	//id를 이용하여 특정 공지사항의 내용을 보여준다 
	public Notice getNotice(int id){
		Notice notice = null; // 노티스를 반환하기 위한 선언 
		String sql =" SELECT * FROM NOTICE WHERE ID=? ";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
 

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,"kh","kh");
			PreparedStatement pstm=con.prepareStatement(sql);	//얘가 sql을 가진다는 말은 미리 준비하게 있다는 소리임 
			pstm.setInt(1, id);
			ResultSet rs=pstm.executeQuery();	//3.그 값을 실행
			if(rs.next()){ 	//2.돌면서 
				int nid = rs.getInt("ID");	//4. 레코드 채워서 
				String title=rs.getString("TITLE");
				Date regdate=rs.getTimestamp("REGDATE") ;
				String writerId=rs.getString("WRITER_ID") ;
				int hit=rs.getInt("HIT") ;
				String files=rs.getString("FILES") ;
				String content=rs.getString("CONTENT") ;
				
				notice = new Notice(	
						nid,
						title,
						regdate,
						writerId,
						hit,
						files,
						content
						
						);
				
			}



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
		return notice ;
	}
	
	//다음글 :여기서 ID 는 현재 글에대한 ID 임 
	public Notice getNextNotice(int id){
		Notice notice = null; // 노티스를 반환하기 위한 선언 
		String sql =" SELECT * FROM NOTICE "
				+ " WHERE ID=( "
				+ "    SELECT ID FROM NOTICE "
				+ "    WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID=?) "
				+ "    AND ROWNUM=1"
				+ " ) ";
		String url="jdbc:oracle:thin:@localhost:1521:xe";


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,"kh","kh");
			PreparedStatement pstm=con.prepareStatement(sql);	//얘가 sql을 가진다는 말은 미리 준비하게 있다는 소리임 
			pstm.setInt(1, id);
			ResultSet rs=pstm.executeQuery();	//3.그 값을 실행
			if(rs.next()){ 	//2.돌면서 
				int nid = rs.getInt("ID");	//4. 레코드 채워서 
				String title=rs.getString("TITLE");
				Date regdate=rs.getTimestamp("REGDATE") ;
				String writerId=rs.getString("WRITER_ID") ;
				int hit=rs.getInt("HIT") ;
				String files=rs.getString("FILES") ;
				String content=rs.getString("CONTENT") ;
				
				notice = new Notice(	
						nid,
						title,
						regdate,
						writerId,
						hit,
						files,
						content
						
						);
				
			}



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
		return notice;
	}
	
	//이전글
	public Notice getPrevNotice(int id){
		Notice notice = null; // 노티스를 반환하기 위한 선언 
		String sql=" SELECT ID FROM "
				+ " (SELECT * FROM NOTICE ORDER BY REGDATE DESC)"
				+ " WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID =?)"
				+ " AND ROWNUM =1 ";
		String url="jdbc:oracle:thin:@localhost:1521:xe";


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,"kh","kh");
			PreparedStatement pstm=con.prepareStatement(sql);	//얘가 sql을 가진다는 말은 미리 준비하게 있다는 소리임 
			pstm.setInt(1, id);
			ResultSet rs=pstm.executeQuery();	//3.그 값을 실행
			if(rs.next()){ 	//2.돌면서 
				int nid = rs.getInt("ID");	//4. 레코드 채워서 
				String title=rs.getString("TITLE");
				Date regdate=rs.getTimestamp("REGDATE") ;
				String writerId=rs.getString("WRITER_ID") ;
				int hit=rs.getInt("HIT") ;
				String files=rs.getString("FILES") ;
				String content=rs.getString("CONTENT") ;
				
				notice = new Notice(	
						nid,
						title,
						regdate,
						writerId,
						hit,
						files,
						content
						
						);
				
			}



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
		return notice; 
	}
}

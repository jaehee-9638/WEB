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

public class NoticeService {
	//NOTICE를 서비스하는애들을 NoticeService클래스에서 담당할거임
	private String url="jdbc:oracle:thin:@localhost:1521:xe";
	//4가지 객체는 매번 실행할때마다 따로 있어야 한다. 사용자 계정정보는 올려도된다. 
	private String user="kh";
	private String password="kh";	//id나 pw변경시 일괄 적용할수있다는 장점있음 
	private String driver="oracle.jdbc.driver.OracleDriver";
	
	//select 
	public List<Notice> getList(int page,String field,String query) throws SQLException, ClassNotFoundException{
		int start=1+(page-1)*10;	//1-1,2-11,3-21
		int end=10*page;//10,20,30,40
		
		//SQL에서 VIEW로 NOTICE_VIEW_PAGING 생성했고 WHERE 절은 테이블에 정의했어서 여기서 붙혀준거임 
		String sql=" SELECT * FROM NOTICE_VIEW_PAGING WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ? ";
		//field는 ?로넣으면 컬럼이 아닌 값으로 들어가게 되서 이렇게 표시 해준거임 
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		PreparedStatement pstm = con.prepareStatement(sql);
		//? 값 채워주기 
		//
		pstm.setString(1, "%"+query+"%");
		pstm.setInt(2,start);
		pstm.setInt(3,end);
		ResultSet rs=pstm.executeQuery();
		
		//반환하기 위한 준비
		List<Notice> list=new ArrayList<Notice>();
		//기본연결 실행 완료 된거임 
		while(rs.next()) {	
			int id = rs.getInt("ID");// sql문 인자를 넘기기위한
			String title = rs.getString("TITLE");	
			String writerId=rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content=rs.getString("CONTENT");
			int hit =rs.getInt("hit");	
			String files=rs.getString("FILES");
			//Notice 개체를 만들면서 초기화하는애를 오버로드 
			Notice notice =new Notice(
					//순서는 dto순서에 맞게 적어주자 
					id,
					title,
					writerId,
					regDate,
					content,
					hit,
					files
					);
			
			list.add(notice);	//반복하면서 바로 출력은아니고 일단 담아준다.
			
		}
		rs.close();
		pstm.close();
		con.close();
		
		return list;
	}
	
	//count를 얻는 메소드 ,,단일 값을 얻어온다는거는 scalar값을 얻어온다고 말하기도 함 
	public int getCount() throws SQLException, ClassNotFoundException {
		int count=0;
		String sql=" SELECT COUNT(ID) COUNT FROM NOTICE ";	//count집계함수 사용해서 id 의 개수확인 
		//별칭 안주면 컬럼명이 COUNT(ID)로 나와서 복잡해진다. 
		
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();	//인자 넘길필요업으니까 stmt사용해도된다. 
		
		ResultSet rs=stmt.executeQuery(sql);
		
		if(rs.next())	//값이 있는지 확인하기위해 if문 사용하고,결과집합 가져오기위해 next()사용
			count = rs.getInt("COUNT");	//별칭을 줘서 "COUNT"로 얻을 수 있는거 
			//결과집합에서 count 만 얻어오면된다. 
		
		
		rs.close();
		stmt.close();
		con.close();
		//위에 if 문에서 가져온 값이 없으면 내가 설정한 초기값 0이 출력된다.
		return count;
	}

	//insert : return타입은 int , notice데이터 넘겨받아서 구현
	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		//Notice에서 넘겨받아서 사용할수 있도록 
		String title =notice.getTitle();
		String writerId=notice.getWriterId();
		String content=notice.getContent();
		String files=notice.getFiles();
		 
		String sql=" INSERT INTO notice ("
				+ "    title,"
				+ "    writer_id,"
				+ "    content,"
				+ "    files"
				+ ") VALUES (?,?,?,?) ";
		
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		PreparedStatement pstm=con.prepareStatement(sql);
		
		pstm.setString(1, title);
		pstm.setString(2, writerId);
		pstm.setString(3, content);
		pstm.setString(4, files);
		int res=pstm.executeUpdate();	
		System.out.println(res+"개의 row가 insert되었습니다. ");
		
		pstm.close();
		con.close();
		return res;
	}
	
	//update : return타입은 int , notice데이터 넘겨받아서 구현
	public int update(Notice notice) throws SQLException, ClassNotFoundException {
		String title =notice.getTitle();
		String content=notice.getContent();
		String files=notice.getFiles();
		int id =notice.getId();
		
		String sql=" UPDATE  NOTICE "
				+ "SET "
				+ "    TITLE=?,"
				+ "    CONTENT=?,"
				+ "    FILES=?"
				+ " WHERE ID =? " ;
	
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		PreparedStatement pstm=con.prepareStatement(sql);
		
		pstm.setString(1, title);
		pstm.setString(2, content);
		pstm.setString(3, files);
		
		pstm.setInt(4, id);
		int res=pstm.executeUpdate();	
		System.out.println(res+"개의 row가 update되었습니다. ");
		
		pstm.close();
		con.close();
		return res;
	}
	
	//delete : return타입은 int , int id 넘겨받아서 구현
	public int delete(int id) throws SQLException, ClassNotFoundException {
		String sql=" DELETE NOTICE WHERE id = ? " ;
		
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		PreparedStatement pstm=con.prepareStatement(sql);
		
		
		pstm.setInt(1, id);
		int res=pstm.executeUpdate();	
		 
		System.out.println(id+"행의 row가 delete되었습니다. ");
		System.out.println(res+"개의 row가 DELETE되었습니다. ");
		
		pstm.close();
		con.close();

		return res;
	}

	
	
	
}

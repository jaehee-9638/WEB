package user;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class BoardDAO {

	private Connection conn;
	//여러개의 함수가 사용되기때문에 각각함수끼리 데이터베이스 접근에 있어서 마찰이 일어나지 않도록 pstm는 안쪽에 넣어준다. 
	private ResultSet rs;
	
	public BoardDAO() {
		try {
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="kh";
			String password="kh";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,user , password);
			//접속완료되면 conn객체에 접속된 정보가 담기게 되는거다 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int write(String board_title,String user_id,String board_content) {
String SQL=" INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL,?,?,SYSDATE,?) ";	
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			
			pstmt.setString(1, board_title);	
			pstmt.setString(2, user_id );
			pstmt.setString(3, board_content);
			
			
			
			return pstmt.executeUpdate();	//insert는 성공적으로 수행했을때 0 이상의 결과 반환
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;	//오류발생했을때 
		
	}
	public ArrayList<Board> getList(int pageNumber){
		
		
		return null;
		
	}
}

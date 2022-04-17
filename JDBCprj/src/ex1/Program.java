package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//SELECT
	
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		
		String sql="SELECT * FROM NOTICE WHERE HIT >=10";	//전부출력인데 아래 IF 문에 넣어준것만 출력된다 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection(url,"kh","kh");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//기본연결 실행 완료 된거임 
		while(rs.next()) {	//가져오자
			
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");	
			String writerId=rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content=rs.getString("CONTENT");
			int hit =rs.getInt("hit");	//대소문자 구분안함 하지만 보낸대로 가져오는게 좋음 
			
			
			System.out.printf(" id: %d ,title : %s, writerId: %s ,regDate: %s,content : %s, hit : %d \n",id,title,writerId,regDate,content,hit);
		}
		rs.close();
		stmt.close();
		con.close();

	}

}

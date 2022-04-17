package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class program2 {
	//insert
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		String title ="제목입니다.";
		String writerId="작성자입니다.";
		
		
		String sql =" INSERT INTO NOTICE (TITLE,WRITER_ID) VALUES(?,?) ";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
		PreparedStatement pstm = con.prepareStatement(sql);
		
		
		pstm.setString(1, title);
		pstm.setString(2, writerId);
	
		int res= pstm.executeUpdate();
		if (res>0) {System.out.println(res+"insert 되었습니다.");}
		
		pstm.close();
		con.close();
		//ID TITLE WRITER_ID 
		
	}
}

package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class program3 {
	//update
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String title ="변경한 제목";
		String content="변경한 내용";
		int id =24;
		
		String sql =" UPDATE NOTICE SET TITLE =?,CONTENT=? WHERE ID=? ";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, title);
		pstm.setString(2, content);
		pstm.setInt(3, id);
		
		int res=pstm.executeUpdate();
		if (res>0) {System.out.println("update완료 ");}
		
	}
}

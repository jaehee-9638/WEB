package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class program {

	//select
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		String sql= " SELECT * FROM NOTICE ORDER BY ID DESC";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
		PreparedStatement pstm= con.prepareStatement(sql);
		ResultSet rs=pstm.executeQuery();
		System.out.println("<<최신순으로 출력>> ");
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title =rs.getString("TITLE");
			String writerId =rs.getString("WRITER_ID");
			String content =rs.getString("CONTENT");
			Date regdate=rs.getDate("REGDATE");
			
			System.out.printf("%d %s %s %s %s \n",id,title,writerId,content,regdate);
		}
		rs.close();
		pstm.close();
		con.close();
	}
}

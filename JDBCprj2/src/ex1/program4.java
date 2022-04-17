package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class program4 {
	//delete
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		int id =24;
		String sql =" DELETE FROM NOTICE WHERE ID =? ";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
		PreparedStatement pstm =con.prepareStatement(sql);
		pstm.setInt(1, id);
		int res= pstm.executeUpdate();
		if (res>0) {System.out.println(id+"행 delete완료");}
		
	}

}

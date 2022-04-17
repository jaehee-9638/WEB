package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;



public class Program4 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//DELETE
		
		int id =10;
		//아직 input 할수있는 페이지 없어서 일단 페이지에 이런식으로 고정 해줬다. 
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		
		String sql=" DELETE NOTICE WHERE id = ? " ;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection(url,"kh","kh");
		PreparedStatement pstm=con.prepareStatement(sql);
		//PreparedStatement 는 sql문 작성시 변수를 ?로 지정할수있는 기능만 추가된거임 
		
		pstm.setInt(1, id);
		int res=pstm.executeUpdate();	//반환타입은 row 수 -> 
		// res는 실행결과를 확인하기 위한 변수라고 보면된다. 
		System.out.println(id+"행의 row가 delete되었습니다. ");
		System.out.println(res+"개의 row가 DELETE되었습니다. ");
		//기본연결 실행 완료 된거임 
		
		
		pstm.close();
		con.close();

	}

}

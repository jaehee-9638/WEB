package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	//jsp에서 회원 데이터 베이스에 접근할수있또록 
	//데이터 베이스 접근 객체의 약자 
	//실질적으로 데이터베이스에서 회원정보를 가져오거나 데이터베이스에 회원정보를 넣고싶을 때 사용 한다. 
	
	private Connection conn;
	//Connection : db에 접근하게해주는 객체
	private PreparedStatement pstmt;
	private ResultSet rs;
	//rs:어떤 정보를 담을 수 있는 객체 
	
	//생성자 만들어주자
	//userDAO를 객체로 만들었을때 
	//자동으로 데이터베이스 커넥션이 이뤄질수있도록 하자 
	public UserDAO() {
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
	//여기까지가 실제 sql에 접속을 해주게 하는 부분임 
	
	//실제로 로그인을 시도하는 함수를 만들어준다. 파라미터 2개를 입력받아서 처리할수있음 
	public int login(String user_id, String user_password) {
		String SQL=" SELECT USER_PASSWORD FROM BBS WHERE USER_ID=? ";
		//실제 db에입력할 명령어를 이부분에 입력해주면 된다. ->user테이블에서 해당 사용자의 비밀번호를 가져올수 있게 하는 sql문임 
		try {
			pstmt=conn.prepareStatement(SQL);
			//pstmt에 어떤 정해진 sql문을 데이터베이스에 삽입하는 형식으로 인스턴스를 가져오고 
			pstmt.setString(1, user_id);
			//sql인젝션 같은 해킹기법을 방어하기 위한 수단으로 pstm을 사용하는데 하나의문장을 준비해뒀다 중간에 '?'를 넣어뒀다가 
			//나중에 그'?'에 해당하는 내용으로 userID를 넣어준거임 -> 위에 sql문에있는 '?'말하는거임 
			//즉 매개변수로 넘어온 userID를 ?에 들어 갈수 있게 해줘서 실제 db에는 현재 접속을 시도하고자 하는 
			//사용자의 아이디를 입력받아서 그 아이디가 실제로 존재 하는지 ,,실제한다면 그 아이디의 비밀번호는 뭔지 db에서 가져 오도록 하는거임 
			rs=pstmt.executeQuery();
			//rs에 (결과를 담을수있는 하나의 객체에) 실행한 결과를 넣어주자 
			if(rs.next()) {	//결과가 존재한다면 이안쪽 실행 
				if(rs.getString(1).equals(user_password)) 	//결과로 나온 userpassword받아서 이게 접속시도한 userpassword와 동일하다면 실행 
					return 1;	//로그인 성공 
				else 
					return 0; 	//비밀번호 불일치 
					
			}
			//결과가 존재하지 않는다면 이쪽 실행 
			return -1; //아이디가 없음 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;	//데이터 베이스 오류 
	}
	
	public int join(User user) {
		String SQL=" INSERT INTO BBS VALUES(?,?,?,?,?) ";
		try {
			
			pstmt=conn.prepareStatement(SQL);	
			pstmt.setString(1, user.getUser_id());	//각각 ?에 해당하는 내용이 뭐가 들어갈수있는지넣어주자 
			pstmt.setString(2, user.getUser_password());
			pstmt.setString(3, user.getUser_name());
			pstmt.setString(4, user.getUser_gender());
			pstmt.setString(5, user.getUser_email());
			return pstmt.executeUpdate();	//해당 스테이트먼트를 실행한 결과를 넣자 
			//인서트문장은 반드시 0이상의 숫자가 반환되기 때문에 -1이 아닌경우는성공적으로 회원가입이 이루어졌다고 볼수있다. 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 	//데이터베이스오류나면 
		
	}
	

}

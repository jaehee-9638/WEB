package user;

public class User {
	
	private String user_id;
	private String user_password;
	private String user_name;
	private String user_gender;
	private String user_email;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	//회원정보를 담을수잇는 유저 테이블 구축 -> mysql로 진행함 
	
	//하나의 데이터를 관리하고 처리할수 있는 기법을 jsp에서 구현하는걸을 자바 빈즈 라고 한다. 
	
}

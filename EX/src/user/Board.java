package user;

import java.util.Date;

public class Board {
	
	private int board_id;
	private String board_title; 
	private String user_id;
	private Date board_date;
	private String board_content;
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int board_id, String board_title, String user_id, Date board_date, String board_content) {
		super();
		this.board_id = board_id;
		this.board_title = board_title;
		this.user_id = user_id;
		this.board_date = board_date;
		this.board_content = board_content;
		
	}
	
	

}

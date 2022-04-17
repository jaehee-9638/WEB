package com.board.dto;

public class BoardDto {

	
	private int freecomm_seq;
	private String freecomm_id;
	private String freecomm_title;
	private String freecomm_content;
	private String freecomm_regdate;
	private int freecomm_count;
	public BoardDto() {
		
	}
	public BoardDto(int freecomm_seq, String freecomm_id, String freecomm_title, String freecomm_content,
			String freecomm_regdate, int freecomm_count) {
		super();
		this.freecomm_seq = freecomm_seq;
		this.freecomm_id = freecomm_id;
		this.freecomm_title = freecomm_title;
		this.freecomm_content = freecomm_content;
		this.freecomm_regdate = freecomm_regdate;
		this.freecomm_count = freecomm_count;
	}
	public int getFreecomm_seq() {
		return freecomm_seq;
	}
	public void setFreecomm_seq(int freecomm_seq) {
		this.freecomm_seq = freecomm_seq;
	}
	public String getFreecomm_id() {
		return freecomm_id;
	}
	public void setFreecomm_id(String freecomm_id) {
		this.freecomm_id = freecomm_id;
	}
	public String getFreecomm_title() {
		return freecomm_title;
	}
	public void setFreecomm_title(String freecomm_title) {
		this.freecomm_title = freecomm_title;
	}
	public String getFreecomm_content() {
		return freecomm_content;
	}
	public void setFreecomm_content(String freecomm_content) {
		this.freecomm_content = freecomm_content;
	}
	public String getFreecomm_regdate() {
		return freecomm_regdate;
	}
	public void setFreecomm_regdate(String freecomm_regdate) {
		this.freecomm_regdate = freecomm_regdate;
	}
	public int getFreecomm_count() {
		return freecomm_count;
	}
	public void setFreecomm_count(int freecomm_count) {
		this.freecomm_count = freecomm_count;
	}
	
	
}

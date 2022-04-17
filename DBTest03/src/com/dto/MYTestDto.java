package com.dto;

public class MYTestDto {
	
	private int mno;
	private String mname;
	private String nickname;
	
	public MYTestDto() {
		
	}
	
	public MYTestDto(int mno, String mname, String nickname) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.nickname = nickname;
	}

	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}

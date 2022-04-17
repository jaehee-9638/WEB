package com.newlecture.app.entity;

import java.util.Date;

public class Notice {
	//공지사항의 그릇 
	private int id;
	private String title ;
	private String writerId;
	private Date regDate;
	private String content;
	private int hit ;	
	private String files;
	//값을 담을수 있는 좀더 그룹화된 자료형 
	
	//생성자 : 여러가지 용도로 초기화 하면서 값을 채우기위해 만들자 
	//보통 객체 생성되면 자동적으로 실행되는데 객체의 초기화 하는역활을 한다. 
	//만드는이유는 매개인자가 있는 생성자만 생성하게되면 기본 생성자는 묵시적으로 생성되지 않기때문이다. 
	//여기에서는 객체생성과 동시에 값을 바로 전달하기 위함이다. 
	public Notice() {
		//아래에 있는 필드가진생성자와 중복이있다면 중복을 피할수있는 조치가 필요하다. 
	}
	//필드를 가지고있는 생성자
	public Notice(int id, String title, String writerId, Date regDate, String content, int hit, String files) {
		super();
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regDate = regDate;
		this.content = content;
		this.hit = hit;
		this.files = files;
	}
	
	//값들을 세팅하기위한 getter setter 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	
	
}

package com.newlecture.app.console;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {
	//콘솔 페이지 
	//여러 코드블럭에서 사용하기 위해 
	private NoticeService service;
	private int page;
	private String searchField;
	private String searchWord;

	public NoticeConsole() {//초기화 
		service =new NoticeService();
		page=1;
		searchField="";
		searchWord="";
	}

	Scanner scan=new Scanner(System.in);
	
	
	public void select() throws ClassNotFoundException, SQLException {
		List<Notice> list=service.select(page);
		int count = service.getCount(); 
		int lastPage=count/10;
		
		lastPage =count%10>0?lastPage+1:lastPage;	//삼항연산 count가 나머지가 있으면 +1하고 아니면 그냥 출력 
		
		for (Notice n: list) {
			System.out.printf("%d %s %s %s %s \n",n.getId(),n.getTitle(),n.getWriterId(),n.getContent(),n.getRegDate());
		}
		System.out.println("------------------------------");
		System.out.printf("%d %d 게시글 pages \n",page,lastPage);
		
		//System.out.printf("%d %s %s %s %s \n",id,title,writerId,content,regdate);
	}
	public int inputNoticeMenu() {
		Scanner sca = new Scanner(System.in);
		System.out.println("1.상세조회 2.이전 3.다음 4.글쓰기 5.검색 6.글수정 7.글삭제 6.종료");
		String menu_=scan.nextLine();
		int menu=Integer.parseInt(menu_);
		return menu;
		
	}

	public void insert() throws ClassNotFoundException, SQLException {
		System.out.println("이름을 입력해주세요 : ");
		String i_name = scan.next();
		System.out.println("제목을 입력해주세요 : ");
		String i_title= scan.next();
		System.out.println("내용을 입력해주세요 : ");
		String i_content=scan.next();
		service.insert(i_name, i_title, i_content);
		
	}

	public void update() throws ClassNotFoundException, SQLException {
		System.out.println("수정하려는 글 번호 입력 : ");
		int update_no = scan.nextInt();
		System.out.println("제목수정 : ");
		String u_title =scan.next();
		System.out.println("내용수정 : ");
		String u_content= scan.next();
		service.update(update_no,u_title,u_content);
		
	}

	public void delete() throws ClassNotFoundException, SQLException {
		System.out.println("삭제하고 싶은 글의 id 를 입력 해주세요 ");
		int delete_no= scan.nextInt();
		service.delete(delete_no);
	}


	
	public void prePage() {
		
		
		if (page ==1) {
			
			System.out.println("이전페이지는 없습니다.");
		}
		
		page--;
		
		
		
	}
//만약 페이지가 로우가 1-10 개면 page 1 
//만약 페이지가 로우가 11-20개면 page 2
//만약 페이지가 로우가 21-30개면 page 3
//만약 페이지가 로우가 31-40개면 page 4
	//row수 별로 연산하는 

	public void nextPage() throws ClassNotFoundException, SQLException {
		int count=service.getCount();
		int lastPage= count/10;
		if (page ==lastPage) {	//이다음 페이지가 없는경우 
			
			System.out.println("다음 페이지가 없습니다. ");
			return ;
		}
		
		page++;
		
	}
	public void inputSearchWord() {
		//검색기능 기능은 아니고 그냥 
		Scanner scan = new Scanner(System.in);
		System.out.println("검색범주(title/content/writerId)중에 하나를 입력하세요");
		System.out.print(">");
		searchField=scan.nextLine();
		System.out.print("검색어 >");
		searchWord=scan.nextLine();
	}
	

	

}

package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {
	//생성자 만들기 전에 선언하는 이유는 생성자 안에서 바로 변수 선언하면 다른 코드블럭에서 그 변수를 사용할 수 없다
	private NoticeService service;	//출력을 구현하기위한 데이터 필요하다.
	private int page;	//콘솔이 페이지에대한 상태값을 가지고 있도록 상태변수 추가 해준거임 
	private String searchField;
	private String searchWord;
	//private int count;	//notice의 개수를 의미하는 변수 
	
	public NoticeConsole() {	//생성자
		service =new NoticeService();
		page=1;
		searchField="TITLE";
		searchWord ="";
		//count=0; 
		
	}

	public void printNoticeList() throws ClassNotFoundException, SQLException {
		//list얻어서 service가 제공하는 list를 얻자 
		List<Notice> list=service.getList(page,searchField,searchWord);	//page라는 상태변수를 통해 서비스 된다. 
		int count = service.getCount();	//list 를 구할때마다 count 매번 구해야해서 멤버변수호 만든거임 
		int lastPage = count/10;	//	레코드 페이지 91~100,10 81~90,9 
		lastPage =count%10>0?lastPage+1:lastPage;	//삼항연산 count가 나머지가 있으면 +1하고 아니면 그냥 출력 
		System.out.println("───────────────────────────────────────────");
		System.out.printf("<공지사항> 총 %d 게시글 \n ",count);
		System.out.println("───────────────────────────────────────────");
		for(Notice n: list) {
		System.out.printf("%d. %s / %s / %s \n",
						n.getId(),
						n.getTitle(),
						n.getWriterId(),
						n.getRegDate());
		}
		System.out.println("───────────────────────────────────────────");
		System.out.printf("              %d/%d 게시글 pages\n",page,lastPage);
	}
	
	public int inputNoticeMenu() {
		//모양 1.상세조회/2.이전/3.다음/4.글쓰기 > _
		//콘솔을 위한 스캐너
		Scanner scan=new Scanner(System.in);
		
		System.out.printf("1.상세조회/2.이전/3.다음/4.글쓰기/5.검색/6.종료 >");
		String menu_ =scan.nextLine();
		int menu=Integer.parseInt(menu_);
		
		return menu;
	}

	public void movePrevList() {
		if(page==1) {
			System.out.println("=================");
			System.out.println("이전 페이지가 없습니다.");
			System.out.println("=================");
			return;
		}
		page--;
		
	}
	//마지막 페이지를 구현하는 메소드 
	public void moveNextList() throws ClassNotFoundException, SQLException {
		int count = service.getCount();
		int lastPage = count/10;	
		lastPage =count%10>0?lastPage+1:lastPage;	 
		if(page==lastPage) {	//여기에서의 lastPage는 Program5클래스에서 printNoticeList()메소드 호출될때 만들어졌던 값과 
								//이 메소드가 호출될때 사용하는 값이 다를 수 있다. 사이에 값이 들어갔을수도 잇는거라
								//여기서 다시 메소드 만들어서 값 받아와서 써야함
			System.out.println("=================");
			System.out.println("다음 페이지가 없습니다..");
			System.out.println("=================");
			return;
		}
		page++;
		
	}

	public void inputSearchWord() {
		Scanner scan=new Scanner(System.in);	//함수안에서 다른것과 공유하는게 많은게 좋은건 아니다. (꼭 공유해야하는것만 멤버변수로 빼자 )
		System.out.println("검색범주(title/content/writerId)중에 하나를 입력하세요");
		System.out.print(">");
		searchField=scan.nextLine();	//얘도 목록 조회시 필요해서 전역변수로 
		
		System.out.print("검색어 >");
		searchWord=scan.nextLine();	//searchWord는 목록 조회할때 printNoticeList()얘가 사용할 내용이라 전역변수로 선언 
	}


}

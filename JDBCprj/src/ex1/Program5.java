package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program5 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//콘솔출력을위해 Notice를 관리하는 console을 만들고
		NoticeConsole console =new NoticeConsole();
		//int page; //페이지를 기억하기 위한 상태변수 ,,이렇게 해서 아래에서.printNoticeList(page); 이렇게 할수 있겠지만
		
		EXIT:	//라벨문 종료 SWITCH문 CASE 5에서 저런식으로 하고 이렇게 라벨하면 종료 선택하면 WHILE문 종료
		while(true) {
			//콘솔을 통해 프린트 할거임 -> 출력파트
			console.printNoticeList();
			//NoticeList의 관리 메뉴 -> paging 파트 -> 입력파트
			int menu =console.inputNoticeMenu();
			
			//메뉴하면 떠오르는 제어구조는 switch
			switch(menu) {
			case 1:	//상세조회 
				break;
			case 2:	//이전
				console.movePrevList();
				//page--;
				break;
			case 3:	//다음
				console.moveNextList();
				//page++;
				break;
			case 4:	//글쓰기
				break;
			case 5: //검색
				console.inputSearchWord();
				break;
			case 6:	//종료
				System.out.println("Bye~~");
				break EXIT;
			default:	//1-4 이외의 값을 입력했을때 경고문 
				System.out.println("<<사용방법>> 메뉴는 1~4 까지만 입력할 수 있습니다. ");
				break;
			}	
		}	
		
		
	}
}

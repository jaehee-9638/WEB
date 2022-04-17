package ex1;

import java.sql.SQLException;

import java.util.Scanner;

import com.newlecture.app.console.NoticeConsole;

public class program5 {
	// 콘솔로 해서 번호 선택하면 진행
	// 1.상세조회 2.이전 3.다음 4.글쓰기 5.검색 -> 일단 이거하고 나중에
	// 5번에 끼워넣을것 -> 검색
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 이쪽에서 CONSOLE을 호출해서 사용해야한다.

		NoticeConsole console = new NoticeConsole();
		EXIT:
		while (true) {
			console.select();
			int menu = console.inputNoticeMenu();
			switch (menu) {

			case 1:
				// 조회
				console.select();
				break;
			case 2:
				// 이전
				console.prePage();
				break;
			case 3:
				// 다음
				console.nextPage();
				break;
			case 4:
				// 글쓰기
				console.insert();
				break;
			case 5:
				// 검색
				console.inputSearchWord();
				break;
			case 6:
				// 글 수정
				console.update();
				break;
			case 7:
				// 글 삭제
				console.delete();
				break;
			case 8:
				// 종료
				System.out.println("BYE~~");
				break EXIT;
			
			default:
				System.out.println("1~8 사이의 값만 입력 가능합니다.");
				break;
			}
		}

	}
}

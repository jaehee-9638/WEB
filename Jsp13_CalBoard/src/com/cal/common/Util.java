package com.cal.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cal.dto.CalDto;

public class Util {

	//필드 getter setter 
	private String todates;

	public String getTodates() {
		return todates;
	}

	public void setTodates(String mdate) {
		//yyyy-MM-dd hh:mm:00형태로 바꾸자
		//.substring(start,end ) 은 문자열 자르기 start위치 부터 end전까지 문자열 발췌 
		String temp =mdate.substring(0,4)+"-"	//연도
					+mdate.substring(4,6)+"-"	//월
					+mdate.substring(6,8)+"-"	//일
					+mdate.substring(8,10)+"-"	//시간
					+mdate.substring(10)+":00";	//분
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm=Timestamp.valueOf(temp);
		todates = sdf.format(tm);
	}
	
	//토요일,일요일,평일 색상
	public static String fontColor(int date ,int dayOfWeek) {
		String color="";
		//jsp에서 얘 호출해서 글씨 색상 바꿀거임 
		if ((dayOfWeek -1 +date)%7 ==0) {
			color="blue";	//토요일
		}else if ((dayOfWeek -1 +date)%7 ==1) {
			color="red";	//일요일
		}else {
			color="black";	//평일 
		}
		
		return color;
	}
	//일정의 한자리수 -> 두자리수 변환 
	public static String isTwo(String msg) {
		//삼항연산자 이용 msg의 길이가 한자리수면 msg앞에 0붙혀준다 
		return (msg.length()<2)?"0"+msg :msg;
	}
	
	//일정 제목이 긴 경우, 뒷부분을 ...으로 
	public static String getCalView(int i ,List<CalDto> list) {
		
		String d=isTwo(i+"");
		String res="";
		
		for (CalDto dto:list) {
			if (dto.getMdate().substring(6,8).equals(d)) {
				res +="<p>"
						+((dto.getTitle().length()>6)? dto.getTitle().substring(0,6)+"...":dto.getTitle())
						+"<p>";
			}
		}
		
		return res;
		
	}
	
	
}

package com.controller;

import java.util.List;
import java.util.Scanner;

import com.biz.MemberBiz;
import com.biz.MemberBizImpl;
import com.dto.MemberDto;

public class MemberController {

	private static Scanner sc=new Scanner(System.in);
	
	public static int getMenu() {
		StringBuffer sb=new StringBuffer();
		
		sb.append("1.전체출력\n")
		.append("2.선택출력\n")
		.append("3.추   가\n")
		.append("4.수   정\n")
		.append("5.삭   제\n")
		.append("5.종   료\n")
		 .append("**********\n") 
		.append("input select:");
		System.out.println(sb);
		
		int select = sc.nextInt();
		return select;
	}
	
	public static void main(String[] args) {
		int select =0;
		MemberBiz biz=new MemberBizImpl();
		while (select !=6) {
			select=getMenu();
			
			switch(select) {
			case 1:
				//전체 출력 
				List<MemberDto> selectList=biz.selectList();
				for (MemberDto dto:selectList) {
					System.out.println(dto);
				}
				break;
			case 2:
				System.out.println("출력할 번호");
				int selecetM_no=sc.nextInt();
				MemberDto selectOne=biz.seleceOne(selecetM_no);
				System.out.println(selectOne);
				break;
			case 3:
				System.out.println("추가할 이름 :");
				String insertName =sc.next();
				System.out.println("추가할 나이 :");
				int insertAge = sc.nextInt();
				System.out.println("추가할 성별 (M or F:");
				String insertGender =sc.next();
				System.out.println("추가할 지역 :");
				String insertLocation =sc.next();
				System.out.println("추가할 직업 :");
				String insertJob =sc.next();
				System.out.println("추가할 전화번호 :");
				String insertTel =sc.next();
				System.out.println("추가할 이메일 :");
				String insertEmail =sc.next();
				
				MemberDto insertDto =new MemberDto(0,insertName,insertAge,insertGender,insertLocation,insertJob,insertTel,insertEmail);
				int insertRes=biz.insert(insertDto);
				if (insertRes>0) {
					System.out.println("추가성공");
				}else {
					System.out.println("추가실패");
				}
				break;
			case 4:
				System.out.println("수정할 번호: ");
				int updateNo = sc.nextInt();
				System.out.println("수정할 이름: ");
				String updateName = sc.next();
				System.out.println("수정할 나이: ");
				int updateAge = sc.nextInt();
				System.out.println("수정할 성별 (M or F) : ");
				String updateGender = sc.next();
				System.out.println("수정할 지역: ");
				String updateLocation = sc.next();
				System.out.println("수정할 직업: ");
				String updateJob = sc.next();
				System.out.println("수정할 전화번호: ");
				String updateTel = sc.next();
				System.out.println("수정할 이메일: ");
				String updateEmail = sc.next();
				MemberDto updateDto=new MemberDto(updateNo,updateName,updateAge,updateGender,updateLocation,updateJob,updateTel,updateEmail);
				int updateRes = biz.update(updateDto);
				if(updateRes>0) {
					System.out.println("수정 성공");
				}else {
					System.out.println("수정 실패");
				}
				break;
			case 5:
				//public int delete(int m_no);
				System.out.println("삭제할 번호:");
				int deleteNo=sc.nextInt();
				int deleteRes=biz.delete(deleteNo);
				if (deleteRes>0) {
					System.out.println("삭제성공");
				}else {
					System.out.println("삭제 실패 ");
				}
				
				break;
			case 6:
				break;
			}
			
		}
		System.out.println("프로그램을 종료합니다. ");
		
	}
	
	
}

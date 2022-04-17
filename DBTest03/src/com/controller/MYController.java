package com.controller;

import java.util.List;
import java.util.Scanner;

import com.biz.MYTestBiz;
import com.dto.MYTestDto;

public class MYController {
	
	private static Scanner sc=new Scanner(System.in);
	
	
	public static int getMenu() {
		StringBuffer sb=new StringBuffer();
		sb.append("1.전체출력\n")
		.append("2.선택출력\n")
		.append("3.추   가\n")
		.append("4.수   정\n")
		.append("5.삭   제\n")
		.append("6.종   료\n")
		.append("input select:");
		System.out.println(sb);
		int select =sc.nextInt();	
		return select;
	}
	public static void main(String[] args) {
		
		int select =0;
		MYTestBiz biz=new MYTestBiz();
		
	
		do {
			select=getMenu();
			
			switch(select) {
			case 1:
				List<MYTestDto> list=biz.selectList();
				for(MYTestDto dto:list) {
					System.out.printf("%3d %10s %10s",dto.getMno(),dto.getMname(),dto.getNickname());
				}
				break;
			case 2:
				System.out.println("선택할 번호:");
				int selectOneNo=sc.nextInt();
				MYTestDto selectOneDto=biz.selectOne(selectOneNo);
				System.out.printf("%3d %10s %10s",selectOneDto.getMno(),selectOneDto.getMname(),selectOneDto.getNickname());
				break;
			case 3:
				System.out.println("추가할 번호:");
				int insertNo=sc.nextInt();
				System.out.println("추가할 이름:");
				String insertName=sc.next();
				System.out.println("추가할 별명:");
				String insertNickName=sc.next();
				
				MYTestDto insertDto=new MYTestDto();
				insertDto.setMno(insertNo);
				insertDto.setMname(insertNickName);
				insertDto.setNickname(insertNickName);
				
				int insertRes=biz.insert(insertDto);
				if(insertRes>0) {
					System.out.println("추가성공");
				}else {
					System.out.println("추가실패");
				}
				
				break;
			case 4:
				System.out.println("수정할 번호:");
				int updateNo=sc.nextInt();
				System.out.println("수정할 이름:");
				String updateName=sc.next();
				System.out.println("수정할 별명:");
				String updateNickName=sc.next();
				
				MYTestDto updateDto=new MYTestDto(updateNo,updateName,updateNickName);
				
				int updateRes=biz.update(updateDto);
				if(updateRes>0) {
					System.out.println("수정성공");
				}else {
					System.out.println("수정실패");
				}
				
				break;
			case 5:
				System.out.println("삭제할 번호:");
				int deleteNo=sc.nextInt();
				int deleteRes=biz.delete(deleteNo);
				if(deleteRes>0) {
					System.out.println("삭제성공");
				}else {
					System.out.println("삭제실패");
				}
				
				break;
			case 6:
				System.out.println("시스템을 종료합니다. ");
				break;
			}
			
		}while(select !=6);
	}
}

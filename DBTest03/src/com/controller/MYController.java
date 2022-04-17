package com.controller;

import java.util.List;
import java.util.Scanner;

import com.biz.MYTestBiz;
import com.dto.MYTestDto;

public class MYController {
	
	private static Scanner sc=new Scanner(System.in);
	
	
	public static int getMenu() {
		StringBuffer sb=new StringBuffer();
		sb.append("1.��ü���\n")
		.append("2.�������\n")
		.append("3.��   ��\n")
		.append("4.��   ��\n")
		.append("5.��   ��\n")
		.append("6.��   ��\n")
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
				System.out.println("������ ��ȣ:");
				int selectOneNo=sc.nextInt();
				MYTestDto selectOneDto=biz.selectOne(selectOneNo);
				System.out.printf("%3d %10s %10s",selectOneDto.getMno(),selectOneDto.getMname(),selectOneDto.getNickname());
				break;
			case 3:
				System.out.println("�߰��� ��ȣ:");
				int insertNo=sc.nextInt();
				System.out.println("�߰��� �̸�:");
				String insertName=sc.next();
				System.out.println("�߰��� ����:");
				String insertNickName=sc.next();
				
				MYTestDto insertDto=new MYTestDto();
				insertDto.setMno(insertNo);
				insertDto.setMname(insertNickName);
				insertDto.setNickname(insertNickName);
				
				int insertRes=biz.insert(insertDto);
				if(insertRes>0) {
					System.out.println("�߰�����");
				}else {
					System.out.println("�߰�����");
				}
				
				break;
			case 4:
				System.out.println("������ ��ȣ:");
				int updateNo=sc.nextInt();
				System.out.println("������ �̸�:");
				String updateName=sc.next();
				System.out.println("������ ����:");
				String updateNickName=sc.next();
				
				MYTestDto updateDto=new MYTestDto(updateNo,updateName,updateNickName);
				
				int updateRes=biz.update(updateDto);
				if(updateRes>0) {
					System.out.println("��������");
				}else {
					System.out.println("��������");
				}
				
				break;
			case 5:
				System.out.println("������ ��ȣ:");
				int deleteNo=sc.nextInt();
				int deleteRes=biz.delete(deleteNo);
				if(deleteRes>0) {
					System.out.println("��������");
				}else {
					System.out.println("��������");
				}
				
				break;
			case 6:
				System.out.println("�ý����� �����մϴ�. ");
				break;
			}
			
		}while(select !=6);
	}
}

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
		
		sb.append("1.��ü���\n")
		.append("2.�������\n")
		.append("3.��   ��\n")
		.append("4.��   ��\n")
		.append("5.��   ��\n")
		.append("5.��   ��\n")
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
				//��ü ��� 
				List<MemberDto> selectList=biz.selectList();
				for (MemberDto dto:selectList) {
					System.out.println(dto);
				}
				break;
			case 2:
				System.out.println("����� ��ȣ");
				int selecetM_no=sc.nextInt();
				MemberDto selectOne=biz.seleceOne(selecetM_no);
				System.out.println(selectOne);
				break;
			case 3:
				System.out.println("�߰��� �̸� :");
				String insertName =sc.next();
				System.out.println("�߰��� ���� :");
				int insertAge = sc.nextInt();
				System.out.println("�߰��� ���� (M or F:");
				String insertGender =sc.next();
				System.out.println("�߰��� ���� :");
				String insertLocation =sc.next();
				System.out.println("�߰��� ���� :");
				String insertJob =sc.next();
				System.out.println("�߰��� ��ȭ��ȣ :");
				String insertTel =sc.next();
				System.out.println("�߰��� �̸��� :");
				String insertEmail =sc.next();
				
				MemberDto insertDto =new MemberDto(0,insertName,insertAge,insertGender,insertLocation,insertJob,insertTel,insertEmail);
				int insertRes=biz.insert(insertDto);
				if (insertRes>0) {
					System.out.println("�߰�����");
				}else {
					System.out.println("�߰�����");
				}
				break;
			case 4:
				System.out.println("������ ��ȣ: ");
				int updateNo = sc.nextInt();
				System.out.println("������ �̸�: ");
				String updateName = sc.next();
				System.out.println("������ ����: ");
				int updateAge = sc.nextInt();
				System.out.println("������ ���� (M or F) : ");
				String updateGender = sc.next();
				System.out.println("������ ����: ");
				String updateLocation = sc.next();
				System.out.println("������ ����: ");
				String updateJob = sc.next();
				System.out.println("������ ��ȭ��ȣ: ");
				String updateTel = sc.next();
				System.out.println("������ �̸���: ");
				String updateEmail = sc.next();
				MemberDto updateDto=new MemberDto(updateNo,updateName,updateAge,updateGender,updateLocation,updateJob,updateTel,updateEmail);
				int updateRes = biz.update(updateDto);
				if(updateRes>0) {
					System.out.println("���� ����");
				}else {
					System.out.println("���� ����");
				}
				break;
			case 5:
				//public int delete(int m_no);
				System.out.println("������ ��ȣ:");
				int deleteNo=sc.nextInt();
				int deleteRes=biz.delete(deleteNo);
				if (deleteRes>0) {
					System.out.println("��������");
				}else {
					System.out.println("���� ���� ");
				}
				
				break;
			case 6:
				break;
			}
			
		}
		System.out.println("���α׷��� �����մϴ�. ");
		
	}
	
	
}

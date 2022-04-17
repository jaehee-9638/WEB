package com.login.biz;

import java.util.List;

import com.login.dao.MYMemberDao;
import com.login.dto.MYMemberDto;

public class MYMemberBiz {
	
	MYMemberDao dao;
	
	public MYMemberBiz() {
		dao=new MYMemberDao();
	}
	//1회우너 전체 조회 (탈퇴 회원 포함 )
	public List<MYMemberDto> selectMemberAll(){
		return dao.selectMemberAll();
		
	}
	//2회원 전체 조회( MYENABLED = 'Y')
	public List<MYMemberDto> selectMemberEn(){
		return dao.selectMemberEn();
		
	}
	//3등급변경
	public int updateRole(int myno, String myrole) {
		return dao.updateRole(myno, myrole);
		
	}
	
	//1로그인
	public MYMemberDto login (String myid,String mypw) {
		
		return dao.login(myid, mypw);
	}
	//2.아이디 중복체크 
	public MYMemberDto idcheck(String myid) {
		return dao.idcheck(myid);
	}
	//회원가입
	public int regist(MYMemberDto dto) {
		return dao.regist(dto);
	}
	//4.정보조회
	public MYMemberDto selectUser(int myno) {
		return dao.selectUser(myno);
		
	}
	//5정보수정
	public int updateUser(MYMemberDto dto) {
		return dao.updateUser(dto);
	}

}

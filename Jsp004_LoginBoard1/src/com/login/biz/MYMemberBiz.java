package com.login.biz;

import java.util.ArrayList;
import java.util.List;

import com.login.dao.MYMemberDao;
import com.login.dto.MYMemberDto;

public class MYMemberBiz {
	
	MYMemberDao dao = new MYMemberDao();
	//1 (admin)회원 전체 정보 확인 (탈퇴회원 포함)
	public List<MYMemberDto> selectUserListAll(){
		return dao.selectUserListAll();
	}
	//2 (admin)회원 전체 정보 확인(탈퇴회원 포함안함)
	public List<MYMemberDto> selectUserListEn(){
		return dao.selectUserListEn();
	}
	//3 (admin)회원 등급 조절 
	public int updateUserRole(int myno,String role) {
		return dao.updateUserRole(myno,role);
	}
	
	//select insert update delete
		
	//1 (user)로그인 select -> 회원번호 조회해서 이름뜨게 해보깡 ? 
	public MYMemberDto login (String myid,String mypw) {
		return dao.login(myid,mypw);
	}
	//2 (user)아이디체크 select
	public MYMemberDto idchk(){
		
		
		return dao.idchk();
	}
	//3 (user)회원가입 insert
	public int regist(MYMemberDto dto){
		return dao.regist(dto);
	}
	//4 (user)내정보 조회 select
	public MYMemberDto selectUser(int myno) {
		return dao.selectUser(myno);
	}
	public MYMemberDto selectUser2(String myid) {
		return dao.selectUser2(myid);
	}
	//5 (user)내정보 수정 update
	public int mypageUpdate(String mypw,String myaddr,String myphone, String myemail,int myno) {
		return dao.mypageUpdate(mypw, myaddr, myphone, myemail, myno);
		
	}
	//6 (user)회원탈퇴 (update) update
	public int idDrop (int myno) {
		return dao.idDrop(myno);
	}
}

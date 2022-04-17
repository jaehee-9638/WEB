package com.login.dao;

import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.login.db.SqlMapConfig;
import com.login.dto.LoginDto;

public class LoginDao extends SqlMapConfig{
	
	//전체 아이디 조회
	public List<LoginDto> selectAllUser(){
		return null;
		
	}
	//회원가입
	public int insertUser(LoginDto dto) {
		int res=0; 
		System.out.println("insert:"+dto);
		
		try(SqlSession session=getSqlSessionFactory().openSession(true)) {
			res=session.insert("loginmapper.reginsert",dto);
			System.out.println("insert2:"+dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	//로그인 기능 
	public LoginDto login(String member_id, String member_pw) {
		//select구문 사용 했음 
		ResultSet rs=null;
		System.out.println("userLogin: "+member_id+" "+member_pw);
		try(SqlSession session=getSqlSessionFactory().openSession(true)) {
			rs=session.select("loginmapper.login",member_id,member_pw);
			System.out.println("insert2:"+dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//id중복확인
	public int idCheck(String member_id) {
		return 0;
		
	}
	public LoginDto getlogininfo(String member_email) {
		return null;
		
	}
	public String encodeHash(String pass) {
		return pass;
		
	}
}

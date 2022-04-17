package com.myboard.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.myboard.db.SqlSessionConfig;
import com.myboard.dto.MyBoardDto;

public class MyBoardDao extends SqlSessionConfig {
	
	//전체조회 
	public List<MyBoardDto> selectList(){
		SqlSession session = null;
		
		List<MyBoardDto> list = new ArrayList<>();//제네릭 비워도되는지 체크하려고 비워둠 
		session =getSqlSessionFactory().openSession(true);
		list = session.selectList("myBoardMapper.selectList");
		
		session.close();
		return list;
	}
	
	//글작성
	public int insert(MyBoardDto dto ) {
		SqlSession session = null; 
		int res=0; 
		
		session = getSqlSessionFactory().openSession(true); //true안함녀 session적용안되서 저장소 저장안되네;;;
		res=session.insert("myBoardMapper.insert",dto);	//insert호출할대 파라미터도 꼭 넣어줘야 하나부다 
		session.close();
		return res;
	}
	
	//상세조회 제목 누르면 상세페이지로 이동하게 할거임 ~ 
	public MyBoardDto selectOne(int seq) {
		SqlSession session =null;
		session = getSqlSessionFactory().openSession(true);
		MyBoardDto dto = new MyBoardDto ();
		dto=session.selectOne("myBoardMapper.selectOne", seq);
		
		return dto;	
	}
	
	//글수정 
	public int update (MyBoardDto dto ) {
		SqlSession session = null;
		int res =0; 
		session = getSqlSessionFactory().openSession(true);
		res=session.update("myBoardMapper.update", dto);
		session.close();
		
		return res;
	}
	
	//글삭제 
	public int delete (int seq) {
		int res =0; 
		SqlSession session = null;
		session=getSqlSessionFactory().openSession(true);
		res=session.delete("myBoardMapper.delete", seq);
		return res;
	}
	
}

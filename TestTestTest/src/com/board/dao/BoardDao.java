package com.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.board.db.SqlMapConfig;
import com.board.dto.BoardDto;

public class BoardDao extends SqlMapConfig{
	private String namespace = "com.board.mapper.";
	//muldel프로젝트가 세션사용해씅ㅁ 
	public List<BoardDto> selectAllList() {
		
		List<BoardDto> list = new ArrayList<BoardDto>();

		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList(namespace + "selectAllList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
		
		
	}
	
	public int insert(BoardDto dto) {
		int res=0; 
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"insert",dto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		
		return res;
	}
	
	public BoardDto selectOneByTitle(int freecomm_seq) {
		
		BoardDto dto =null;
		SqlSession session =null;
		try {
			session=getSqlSessionFactory().openSession(true);
			dto=session.selectOne(namespace + "selectOneByTitle", freecomm_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		return dto;
	}
	public int delete(int freecomm_seq) {
		
		int res=0;
		SqlSession session=null;
		
		try {
			session=getSqlSessionFactory().openSession(true);
			res=session.delete(namespace+"delete",freecomm_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		
		return res;
	}
	
	public int update (BoardDto dto) {
		
		int res=0;
		SqlSession session=null;
		try {
			session=getSqlSessionFactory().openSession(true);
			res=session.update(namespace+"update",dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		
		
		return res;
	}
	//멀티 딜리트 
	
	public int multidelete(String[] seq) {
		//https://github.com/skwak0205/JSP/commit/7a94e5f710946e6b488636cbdf4299b355eca4cb
		//매퍼에 muldel태그 추가해주는 방식으로 함 ...나중에 해결하자 ,.
		
		//데이터 넘어오는 값이 문자열이기때문에 String 배열로 사용해줌 
		//int[] 쓰고 싶으면 jsp단에서 parseInt해줘서 넘겨야함.
		
		//카운트 변수 선언하고 
		int count = 0;
		//key:String , value:String배열  -> 여기서 키와 값은 모두 객체임 
		//hashmap : 맵 인터페이스를 구현한 대표적인 map컬렉션 , 많은 양을 검색할때 사용 
		Map<String, String[]> map = new HashMap<String, String[]>();
		//map에seq란 이름으로seq넣자 
		System.out.println("5 map:"+map);
		map.put("seq", seq);
		
		try (SqlSession session = getSqlSessionFactory().openSession(false);) {
			System.out.println(seq);
			count = session.delete(namespace+"multiDelete", map);
			System.out.println("7");
			if(count == seq.length) {
				System.out.println("8");
				session.commit();
			}
		} catch (Exception e) {
			
			// TODO: handle exception
		}
		
		
		return count;
	}
	//paging 을위한 dao 
	/*
	 * public List<BoardDto> selectPaging(int page){ //1번 페이지 1~10 //2번 페이지 11~20
	 * int startNum = (page-1)*10+1; int endNum = page*10; SqlSession session=null;
	 * List<BoardDto> list = new ArrayList<BoardDto>(); try {
	 * session=getSqlSessionFactory().openSession(true); list=session.
	 * //.selectPaging(namespace+"selectPaging",page);
	 * //.update(namespace+"update",dto); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return list; }
	 */


	

	
	
}

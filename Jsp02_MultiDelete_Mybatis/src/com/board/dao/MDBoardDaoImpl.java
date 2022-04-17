package com.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.board.db.SqlMapConfig;
import com.board.dto.MDBoardDto;

public class MDBoardDaoImpl extends SqlMapConfig implements MDBoardDao {

	private String namespace="com.muldel.mapper.";
	@Override
	public List<MDBoardDto> selectList() {
	
		List<MDBoardDto> list=new ArrayList<MDBoardDto>();
		
		SqlSession session=null;
		
		try {
			session=getSqlSessionFactory().openSession(false);
			list=session.selectList(namespace+"selectList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return list;
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		MDBoardDto dto=null;
		SqlSession session=null;
		try {
			session=getSqlSessionFactory().openSession(true);
			dto=session.selectOne(namespace+"selectOne",seq);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return dto;
	}

	@Override
	public int insert(MDBoardDto dto) {
		int res=0; 
		SqlSession session=null;
		
		try {
			session=getSqlSessionFactory().openSession(true);
			res=session.insert(namespace+"insert",dto);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public int update(MDBoardDto dto) {
		int res=0; 
		SqlSession session=null;
		
		try {
			session=getSqlSessionFactory().openSession(true);
			res=session.update(namespace+"update",dto);
		} catch (Exception e) {
			
		}finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		int res=0; 
		SqlSession session=null;
		
		try {
			session=getSqlSessionFactory().openSession(true);
			res=session.delete(namespace+"delete",seq);
		} catch (Exception e) {
		}finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public int multidelete(String[] seqs) {
		int count=0;
		for (int i =0; i<seqs.length; i++) {
		System.out.println("다오에서 받는 파라미터 seqs는"+seqs[i]);
		}
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("seqs", seqs);
		try(SqlSession session=getSqlSessionFactory().openSession(false);) {
			count=session.delete(namespace+"multiDelete",map);
			if(count==seqs.length) {
				session.commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

}

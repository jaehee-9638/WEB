package com.board.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.board.db.SqlSessionConfig;
import com.board.dto.MDBoardDto;

public class MDBoardDaoImpl extends SqlSessionConfig implements MDBoardDao  {
	private String namespace="com.muldel.mapper.";
	@Override
	public List<MDBoardDto> selectList() {
		SqlSession session =null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		session =getSqlSessionFactory().openSession(true);
		list = session.selectList("mdBoardMapper.selectList");
		
		return list;
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		SqlSession session = null;
		MDBoardDto dto = new MDBoardDto();
		session=getSqlSessionFactory().openSession(true);
		dto = session.selectOne("mdBoardMapper.selectOne", seq);
		return dto;
	}

	@Override
	public int insert(MDBoardDto dto) {
		SqlSession session =null;
		int res=0;
		session=getSqlSessionFactory().openSession(true);
		res=session.insert("mdBoardMapper.insert", dto);
		return res;
	}

	@Override
	public int update(MDBoardDto dto) {
		SqlSession session = null;
		int res =0;
		session = getSqlSessionFactory().openSession(true);
		res=session.update("mdBoardMapper.update", dto);
		return res;
	}

	@Override
	public int delete(int seq) {
		SqlSession session = null;
		int res =0;
		session = getSqlSessionFactory().openSession(true);
		res=session.delete("mdBoardMapper.delete", seq);
		return res;
	}

	@Override
	public int muldel(String[] seqs) {
		int count=0;

		Map<String,String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seqs);	//맵에넣어줬고 
		
		
		try (SqlSession session=getSqlSessionFactory().openSession(true);){
			
			count =session.delete(namespace+"multiDelete", map);
			if (count==seqs.length) {
				session.commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return count;
	}

}

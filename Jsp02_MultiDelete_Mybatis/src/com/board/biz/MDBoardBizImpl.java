package com.board.biz;

import java.util.List;

import com.board.dao.MDBoardDao;
import com.board.dao.MDBoardDaoImpl;
import com.board.dto.MDBoardDto;

public class MDBoardBizImpl implements MDBoardBiz{

	MDBoardDao dao=new MDBoardDaoImpl();
	@Override
	public List<MDBoardDto> selectList() {
		
		return dao.selectList();
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		
		return dao.selectOne(seq);
	}

	@Override
	public int insert(MDBoardDto dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int update(MDBoardDto dto) {
		
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		
		return dao.delete(seq);
	}

	@Override
	public int multidelete(String[] seq) {
		
		return dao.multidelete(seq);
	}

}

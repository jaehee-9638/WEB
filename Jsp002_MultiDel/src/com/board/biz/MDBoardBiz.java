package com.board.biz;

import java.util.List;

import com.board.dto.MDBoardDto;

public interface MDBoardBiz {
	
	public List<MDBoardDto> selectList();
	public MDBoardDto selectOne(int seq); 
	public int insert (MDBoardDto dto);
	public int update (MDBoardDto dto);
	public int delete (int seq);
}

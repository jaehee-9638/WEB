package com.mvc.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.mvc.db.JDBCTemplate.*;
import com.mvc.dto.MVCBoardDto;

public class MVCBoardDaoImpl implements MVCBoardDao {

	@Override
	public List<MVCBoardDto> selectList() {
		Connection con=getConnection();
		PreparedStatement pstm = null; 
		
		List<MVCBoardDto> list=new ArrayList<MVCBoardDto>();
		ResultSet rs=null;
		
		try {
			pstm=con.prepareStatement(SELECT_LIST_SQL);
			rs=pstm.executeQuery();
			
			while (rs.next()) {
			MVCBoardDto dto = new MVCBoardDto();
			dto.setSeq(rs.getInt("SEQ"));
			dto.setWriter(rs.getString("WRITER"));
			dto.setTitle(rs.getString("TITLE"));
			dto.setContent(rs.getString("CONTENT"));
			dto.setRegdate(rs.getDate("REGDATE"));
				list.add(dto);
				//원본이랑 좀 다름 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			
		}
		
		
		
		
		return list;
	}

	@Override
	public MVCBoardDto selectOne(int seq) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		MVCBoardDto dto=null;
		
		try {
			pstm=con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				dto=new MVCBoardDto();
				
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			
		}
		
		
		return dto;
	}

	@Override
	public int insert(MVCBoardDto dto) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		try {
			pstm=con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			res=pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		
		
		return res;
	}

	@Override
	public int update(MVCBoardDto dto) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			res=pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			res=pstm.executeUpdate();
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
			close(pstm);
			close(con);
			
		}
		return res;
	}

}

package com.board.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.board.dto.MDBoardDto;
import static com.board.db.JDBCTemplate.*;


public class MDBoardDaoImpl implements MDBoardDao {

	@Override
	public List<MDBoardDto> selectList() {
		Connection con =getConnection();
		List<MDBoardDto>list =new ArrayList<MDBoardDto>();
		Statement stmt = null;
		ResultSet rs=null;
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(SELECT_LIST_SQL);
			
			  while (rs.next()) { 
				  MDBoardDto dto = new MDBoardDto( 
						  			rs.getInt("SEQ"),
						  			rs.getString("WRITER"), 
						  			rs.getString("TITLE"), 
						  			rs.getString("CONTENT"),
						  			rs.getDate("REGDATE") ); 
				  list.add(dto); 
				  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
		}
		
		
		return list;
	}

	@Override
	public MDBoardDto selectOne(int seq) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		ResultSet rs = null;
		MDBoardDto dto = null;
		try {
			pstm =con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			rs=pstm.executeQuery();
			while (rs.next()) {
				dto = new MDBoardDto(
									rs.getInt("SEQ"),
									rs.getString("WRITER"),
									rs.getString("TITLE"),
									rs.getString("CONTENT"),
									rs.getDate("REGDATE")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int insert(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		int res=0; 
		try {
			pstm =con.prepareStatement(INSERT_SQL);
			pstm.setString(1,dto.getWriter() );
			pstm.setString(2,dto.getTitle() );
			pstm.setString(3,dto.getContent() );
			res=pstm.executeUpdate();
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
	public int update(MDBoardDto dto) {
		Connection con =getConnection();
		PreparedStatement pstm = null;
		int res =0;
		try {
			pstm =con.prepareStatement(UPDATE_SQL);
			
			
			 pstm.setString(1,dto.getTitle() ); 
			 pstm.setString(2, dto.getContent());
			 pstm.setInt(3, dto.getSeq());
			 
			
			res=pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		int res =0;
		try {
			pstm=con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
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
	public int multidel(String[] seq) {
		Connection con = getConnection();
		int res =0; //리턴ㄴ할해
		int [] cnt =null;
		PreparedStatement pstm=null;
		try {
			pstm =con.prepareStatement(DELETE_SQL);
			for (int i=0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
			}
			cnt = pstm.executeBatch();
			
			for (int i=0; i<cnt.length; i++) {
				if (cnt[i]==-2) {
					res++;
				}
			}
			if (res==cnt.length) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
		
	}

}

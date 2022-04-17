package com.board.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.board.db.JDBCTemplate.*;
import com.board.dto.MDBoardDto;



public class MDBoardDaoImpl implements MDBoardDao {

	@Override
	public List<MDBoardDto> selectList() {
		Connection con=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		List<MDBoardDto> list=new ArrayList<MDBoardDto>();
		
		try {
			stmt=con.createStatement();
			System.out.println("3. query 준비 및 실행");
			rs=stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				commit(con);
				MDBoardDto dto=new MDBoardDto();
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
				list.add(dto);
			}
		} catch (SQLException e) {
			
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
		Connection con=getConnection();
		PreparedStatement pstm=null;
		ResultSet rs=null;
		MDBoardDto dto =null;
		try {
			pstm=con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3. query 준비 및 실행");
			
			rs=pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto=new MDBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}

	@Override
	public int insert(MDBoardDto dto) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		try {
			pstm=con.prepareStatement(INSERT_SQL);
			
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3. query 준비 및 실행");
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
		}
		
		
		return res;
	}

	@Override
	public int update(MDBoardDto dto) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3. query 준비 및 실행");
			
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료");
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
			System.out.println("3. query 준비");
			
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

	public int multidelete(String[] seq) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		
		int[] cnt=null;
		
		try {
			pstm=con.prepareStatement(DELETE_SQL);
			for (int i=0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
				System.out.println("3.query준비");
			}
			cnt=pstm.executeBatch();
			System.out.println("4.query실핼");
			for (int i =0; i<cnt.length; i++) {
				if(cnt[i]==-2) {
					res++;
				}
			}
			if(cnt.length==res) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		return res;
	}

}

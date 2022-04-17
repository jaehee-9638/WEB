package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCBoardDto;
import static com.mvc.db.JDBCTemplate.*;

public class MVCBoardDaoImpl implements MVCBoardDao{

	@Override
	public List<MVCBoardDto> selectList() {
		Connection con=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		List<MVCBoardDto> list =new ArrayList<MVCBoardDto>();
		
		try {
			stmt=con.createStatement();
			System.out.println("3. query 준비");
			
			rs=stmt.executeQuery(SELECT_ONE_SQL);
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				MVCBoardDto dto =new MVCBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stmt,con);
		}
		
		
		return list;
	}

	@Override
	public MVCBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCBoardDto dto =null;
		try {
			pstm=con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3. query 준비");
			rs=pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto = new MVCBoardDto();
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getString("REGDATE"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
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
			System.out.println("3. query 준비");
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴 : " + INSERT_SQL);
			if (res>0) {
				commit(con);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm,con);
			System.out.println("5. db 종료");
		}
				
		return res;
	}

	@Override
	public int update(MVCBoardDto dto) {
		Connection con= getConnection();
		PreparedStatement pstm = null;
		int res=0; 
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3. query 준비");
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴 : " + UPDATE_SQL);
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm,con);
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
			System.out.println("4. query 실행 및 리턴 : " + DELETE_SQL);
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm,con);
			System.out.println("5. db 종료");
		}
		return res;
	}

}





















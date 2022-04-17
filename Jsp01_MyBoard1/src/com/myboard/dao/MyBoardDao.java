package com.myboard.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.myboard.db.JDBCTemplate.*;
import com.myboard.dto.MyBoardDto;

public class MyBoardDao {
	
	public List<MyBoardDto> selectList(){
		Connection con=getConnection();
		String sql =" SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE "
					+" FROM MYBOARD "
					+" ORDER BY SEQ DESC ";
		
		Statement stmt=null;
		ResultSet rs=null;
		List<MyBoardDto> list=new ArrayList<MyBoardDto>();
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				commit(con);
				MyBoardDto dto=new MyBoardDto();
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
	
	public MyBoardDto selectOne(int seq) {
		Connection con=getConnection();
		String sql=" SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE "
					+" FROM MYBOARD "
					+" WHERE SEQ=? ";
		PreparedStatement pstm=null;
		ResultSet rs=null;
		MyBoardDto dto=null;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3.query준비"+sql);
			
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				commit(con);
					dto=new MyBoardDto();
					dto.setSeq(rs.getInt("SEQ"));
					dto.setWriter(rs.getString("WRITER"));
					dto.setTitle(rs.getString("TITLE"));
					dto.setContent(rs.getString("CONTENT"));
					dto.setRegdate(rs.getDate("REGDATE"));
				}
				System.out.println("4.query실행 및 리턴 ");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		return dto;
		
	}
	
	public int insert(MyBoardDto dto) {
		Connection con=getConnection();
		String sql= " INSERT INTO MYBOARD "
					+" VALUES (MYBOARDSEQ.NEXTVAL,?,?,?,SYSDATE) ";
		PreparedStatement pstm=null;
		
		int res=0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			res=pstm.executeUpdate();
			
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
	public int update(MyBoardDto dto) {
		Connection con=getConnection();
		String sql=" UPDATE MYBOARD "+" SET TITLE=?,CONTENT=? " +" WHERE SEQ=? ";
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			res=pstm.executeUpdate();
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
	
	public int delete(int seq) {
		Connection con=getConnection();
		String sql= " DELETE FROM MYBOARD "+" WHERE SEQ=? ";
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, seq);

			res=pstm.executeUpdate();
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

}

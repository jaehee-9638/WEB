package com.myboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myboard.dto.MyBoardDto;
import static com.myboard.db.JDBCTemplate.*;
public class MyBoardDao {

	//전체조회
	public List<MyBoardDto> selectList() {
		Connection con =getConnection();
		String sql =" SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE FROM MYBOARD ";
		List<MyBoardDto> list = new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				/*
				 * MyBoardDto dto =new MyBoardDto(); dto.setSeq(rs.getInt("SEQ"));
				 * dto.setWriter(rs.getString("WRITER")); dto.setTitle(rs.getString("TITLE"));
				 * dto.setContent(rs.getString("CONTENT"));
				 * dto.setRegdate(rs.getDate("REGDATE"));
				 */
				
				 int seq = rs.getInt("SEQ"); 
				 String writer =rs.getString("WRITER"); 
				 String title=rs.getString("TITLE"); 
				 String content= rs.getString("CONTENT"); 
				 Date regdate=rs.getDate("REGDATE");
				 
				 MyBoardDto dto =new MyBoardDto(seq,writer,title,content,regdate);
				 
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
		
	//상세조회 
	public MyBoardDto selectOne(int seq) {
		Connection con =getConnection();
		String sql =" SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE FROM MYBOARD WHERE SEQ =? ";

		PreparedStatement pstm=null;
		ResultSet rs=null;
		MyBoardDto dto =null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,seq);
			rs=pstm.executeQuery();
			while(rs.next()) {
			dto=new MyBoardDto();
			dto.setSeq(rs.getInt("SEQ"));
			dto.setWriter(rs.getString("WRITER"));
			dto.setTitle(rs.getString("TITLE"));
			dto.setContent(rs.getString("CONTENT"));
			dto.setRegdate(rs.getDate("REGDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return dto;
	}
		
	//글작성
	public int insert(MyBoardDto dto) {
		Connection con =getConnection ();
		String sql =" INSERT INTO MYBOARD VALUES(MYBOARDSEQ.NEXTVAL,?,?,?,SYSDATE) ";

		PreparedStatement pstm=null;
		int res =0; 
		try {
			commit(con);
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
		
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
	
	//글수정 
	public int update(MyBoardDto dto) {
		Connection con =getConnection();
		String sql =" UPDATE MYBOARD SET TITLE=?,CONTENT=? WHERE SEQ=? ";
		
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm =con.prepareStatement(sql);
			
			
			
			  pstm.setString(1, dto.getTitle()); 
			  pstm.setString(2, dto.getContent());
			  pstm.setInt(3, dto.getSeq());
			 
			res=pstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		System.out.println(res);
		return res;
	}
	
	//삭제 
	public int delete(int seq) {
		Connection con =getConnection();
		String sql =" DELETE FROM MYBOARD WHERE SEQ =? ";
		PreparedStatement pstm=null;
		int res =0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
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
}

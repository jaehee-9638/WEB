package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.dto.MVCBoardDto;
import static com.mvc.db.JDBCTemplate.*;

public class MVCBoardDaoImpl implements MVCBoardDao {

	@Override
	public List<MVCBoardDto> selectList() {
		Connection con = getConnection();
		List<MVCBoardDto> list= new ArrayList<MVCBoardDto>(); 
		Statement stmt =null;
		ResultSet rs= null;
		MVCBoardDto dto = null;
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery(SELECT_LIST_SQL);
			while(rs.next()) {
				
				int seq = rs.getInt("SEQ");
				String writer=rs.getString("WRITER");
				String title=rs.getString("TITLE");
				String content=rs.getString("CONTENT");
				Date regdate=rs.getDate("REGDATE");
				dto =new MVCBoardDto(seq,writer,title,content,regdate);
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public MVCBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		ResultSet rs= null;
		MVCBoardDto dto = new MVCBoardDto();
		try {
			pstm =con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			rs=pstm.executeQuery();
			while(rs.next()) {
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

	@Override
	public int insert(MVCBoardDto dto) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		int res =0;
		try {
			pstm=con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			res=pstm.executeUpdate();
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(MVCBoardDto dto) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		int res=0; 
		try {
			pstm=con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			
			res=pstm.executeUpdate();
			if (res>0) {
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
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		try {
			pstm =con.prepareStatement(DELELE_SQL);
			pstm.setInt(1, seq);
			res=pstm.executeUpdate();
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int multiDelete(String[] seq) {
		Connection con =getConnection();
		PreparedStatement pstm =null;
		int res=0;
		int []cnt= null;
		
		try {
			pstm=con.prepareStatement(DELELE_SQL);
			for (int i=0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
			}
			cnt=pstm.executeBatch();
			for (int i=0; i<cnt.length; i++) {
				if (cnt[i]==-2) {
					res++;
				}
			}
			System.out.println("11.res "+res);
			System.out.println("12.seq.length"+seq.length);
			if (res==seq.length) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("dao에서 res"+res);
		return res;
	}

}

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
		Connection con =getConnection();
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt =con.createStatement();
			rs=stmt.executeQuery(SELECT_LIST_SQL);
			while(rs.next()) {
				int seq = rs.getInt("SEQ");
				String writer=rs.getString("WRITER");
				String title=rs.getString("TITLE");
				String content=rs.getString("CONTENT");
				Date regdate=rs.getDate("REGDATE");
				
				MVCBoardDto dto = new MVCBoardDto(seq,writer,title,content,regdate);
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
	public MVCBoardDto selectOne(int seq) {
		Connection con =getConnection();
		System.out.println("dao호출까진 되는건가 ?");
		PreparedStatement pstm = null;
		ResultSet rs= null;
		MVCBoardDto dto =null;
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
		System.out.println("dtodtodtotdotodtodotod"+dto.getSeq()+dto.getTitle()+dto.getWriter());
		return dto;
	}

	@Override
	public int insert(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		try {
			pstm =con.prepareStatement(INSERT_SQL);
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
		}
		
		
		return res;
	}

	@Override
	public int update(MVCBoardDto dto) {
		Connection con =getConnection();
		PreparedStatement pstm = null;
		int res =0; 
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
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
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
		}
		
		return res;
	}

	@Override
	public int multiDelete(String [] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0; 
		int []cnt = null;
		try {
			pstm=con.prepareStatement(DELETE_SQL);
			for (int i =0; i< seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
			}
			cnt = pstm.executeBatch();	// executeBatch는 결과값이 int 형으로 넘어온다~
			
			for (int i =0; i<cnt.length; i++) {	//cnt길이만큼 
				if (cnt[i]==-2) {	//성공했을때 
					res++;	//res 1씩 커짐 
				}
			}
			if (res==seq.length) {	//res가 처음들어왔던 seq만큼 같아졌을때 	
				commit(con); 	//커밋한다. 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	

}

package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;
import static com.answer.db.JDBCTemplate.*;

public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		Connection con=getConnection();
		PreparedStatement pstm = null;
		ResultSet rs= null;
		List<AnswerDto> list= new ArrayList<AnswerDto>();
		
		try {
			pstm=con.prepareStatement(SELECE_LIST_SQL);
			System.out.println("3. query 준비");
			rs=pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				AnswerDto dto =new AnswerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				list.add(dto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
			System.out.println("5. db 종료");
		}
		
		return list;
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		Connection con=getConnection();
		PreparedStatement pstm = null;
		ResultSet rs= null;
		AnswerDto dto =new AnswerDto();
		
		try {
			pstm=con.prepareStatement(SELECE_ONE_SQL);
			pstm.setInt(1, boardno);
			System.out.println("3. query 준비");
			rs=pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto.setBoardno(rs.getInt("BOARDNO"));
				dto.setGroupno(rs.getInt("GROUPNO"));
				dto.setGroupseq(rs.getInt("GROUPSEQ"));
				dto.setTitletab(rs.getInt("TITLETAB"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setRegdate(rs.getDate("REGDATE"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
			System.out.println("5. db 종료");
		}
		
		return dto;
	}

	@Override
	public int boardInsert(AnswerDto dto) {
		Connection con=getConnection();
		PreparedStatement pstm = null;
		int res=0;
		System.out.println("여");
		try {
			System.out.println("기");
			pstm=con.prepareStatement(BOARD_INSERT_SQL);
			System.out.println("까");
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3. query 준비");
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴 : " + BOARD_INSERT_SQL);
			if(res>0) {
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
	public int boardUpdate(AnswerDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardDelete(int boardno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int answerInsert(AnswerDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int answerUpdate(int parentBoardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}

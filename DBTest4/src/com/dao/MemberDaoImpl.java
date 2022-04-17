package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.MemberDto;
import static com.common.JDBCTemplate.*;

public class MemberDaoImpl implements MemberDao {

	@Override
	public List<MemberDto> selectList() {
		Connection con=getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		List<MemberDto> list=new ArrayList<MemberDto>();
		try {
			stmt=con.createStatement();
			
			rs=stmt.executeQuery(SELECT_LIST_SQL);
			while(rs.next()) {
				MemberDto dto=new MemberDto();
				dto.setM_no(rs.getInt(1));
				dto.setM_name(rs.getString(2));
				dto.setM_age(rs.getInt(3));
				dto.setM_gender(rs.getString(4));
				dto.setM_location(rs.getString(5));
				dto.setM_job(rs.getString(6));
				dto.setM_tel(rs.getString(7));
				dto.setM_email(rs.getString(8));
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
	public MemberDto selectOne(int m_no) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		MemberDto dto=new MemberDto();
		ResultSet rs=null;
		try {
			pstm=con.prepareStatement(SELECT_ONE_SQL);
			while(rs.next()) {
				pstm.setInt(1, m_no);
				
			rs=pstm.executeQuery();
			while(rs.next()) {
				dto.setM_no(rs.getInt("M_NO"));
				dto.setM_name (rs.getString("M_NAME"));
				dto.setM_age (rs.getInt("M_AGE"));
				dto.setM_gender (rs.getString("M_GENDER"));
				dto.setM_location (rs.getString("M_LOCATION"));
				dto.setM_job (rs.getString("M_JOB"));
				dto.setM_tel (rs.getString("M_TEL"));
				dto.setM_email (rs.getString("M_EMAIL"));
			}
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
	public int insert(MemberDto dto) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		
		
		try {
			pstm=con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getM_name());
			pstm.setInt(2, dto.getM_age());
			pstm.setString(3, dto.getM_gender());
			pstm.setString(4, dto.getM_location());
			pstm.setString(5, dto.getM_job());
			pstm.setString(6, dto.getM_tel());
			pstm.setString(7, dto.getM_email());
			if (res>0) {
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

	@Override
	public int update(MemberDto dto) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(UPDATE_SQL);
			pstm.setInt(1, dto.getM_no());
			pstm.setString(1, dto.getM_name());
			pstm.setInt(2, dto.getM_age());
			pstm.setString(3, dto.getM_gender());
			pstm.setString(4, dto.getM_location());
			pstm.setString(5, dto.getM_job());
			pstm.setString(6, dto.getM_tel());
			pstm.setString(7, dto.getM_email());
			pstm.setInt(8, dto.getM_no());
			
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

	@Override
	public int delete(int m_no) {
		Connection con =getConnection();
		PreparedStatement pstm=null;
		int res=0;
		try {
			pstm=con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, m_no );
			
			res=pstm.executeUpdate();
			if (res>0) {
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

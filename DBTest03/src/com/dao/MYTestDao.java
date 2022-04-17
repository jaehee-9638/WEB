package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.MYTestDto;
import static com.common.JDBCTemplate.*;

public class MYTestDao {

	public List<MYTestDto> selectList(){
		Connection con =getConnection();
		String sql=" SELECT MNO,MNAME,NICKNAME "+" FROM MYTEST ";
		Statement stmt=null;
		ResultSet rs=null;
		List<MYTestDto> list=new ArrayList<MYTestDto>();
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				MYTestDto temp=new MYTestDto();
				temp.setMno(rs.getInt("MNO"));
				temp.setMname(rs.getString("MNAME"));
				temp.setNickname(rs.getString("NICKNAME"));
				list.add(temp);
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
	public int insert(MYTestDto dto) {
		Connection con=getConnection();
		String sql=" INSERT INTO MYTEST "+ " VALUES(?,?,?) ";
		PreparedStatement pstm =null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, dto.getMno());
			pstm.setString(2,dto.getMname());
			pstm.setString(3, dto.getNickname());
			
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
	
	public MYTestDto selectOne(int mno) {
		Connection con=getConnection();
		String sql = " SELECT MNO,MNAME,NICKNAME "+" FROM MYTEST "+" WHERE MNO=? ";
		PreparedStatement pstm=null;
		MYTestDto dto=new MYTestDto();
		ResultSet rs=null;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, mno);
			
			rs=pstm.executeQuery();
			while(rs.next()) {
				dto.setMno(rs.getInt("MNO"));
				dto.setMname(rs.getString("MNAME"));
				dto.setNickname(rs.getString("NICKNAME"));
				
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
	
	public MYTestDto selectOne2(int mno) {
		Connection con=getConnection();
		String sql=" SELECT * "+" FROM MYTEST "+" WHERE MN0=? "+mno;
		Statement stmt=null;
		ResultSet rs=null;
		MYTestDto dto=null;
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				dto=new MYTestDto(rs.getInt(1),rs.getString(2),rs.getString(3));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
		}
		
		return dto;
	}
	public int update(MYTestDto dto) {
		Connection con=getConnection();
		String sql = " UPDATE MYTEST "+" SET MNAME=?,NICKNAME=? "+" WHERE MNO=? ";
		PreparedStatement pstm=null;
		int res=0; 
		
		try {
			pstm=con.prepareStatement(sql);
		
			pstm.setString(1, dto.getMname());
			pstm.setString(2, dto.getNickname());
			pstm.setInt(3, dto.getMno());
			
			res=pstm.executeUpdate();
			if(res>0){
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
	
	public int delete(int mno) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		String sql=" DELETE FROM MYTEST "+ " WHERE MNO ";
		int res=0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, mno);
			
			
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

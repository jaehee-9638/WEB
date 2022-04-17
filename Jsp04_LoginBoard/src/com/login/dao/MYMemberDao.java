package com.login.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.login.db.JDBCTemplate.*;

import com.login.dto.MYMemberDto;

public class MYMemberDao {

	/*
	 * 관리자(ADMIN) 가능
	 * 1. 회원 전체 정보 확인 (탈퇴한 회원도 포함)
	 * 2. 회원 전체 정보 확인 (MYENABLED='Y'인 -> 탈퇴안한 회원들의 정보)
	 * 3. 회원 등급 조정 (ADMIN <-> USER)
	 * 
	 * */
	
	//1.전체정보
	public List<MYMemberDto> selectAllUser() {
		Connection con = getConnection();
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE"
					+ " FROM MYMEMBER "
					+ " ORDER BY MYNO DESC ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MYMemberDto> list = new ArrayList<MYMemberDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				MYMemberDto dto = new MYMemberDto();
				dto.setMyno(rs.getInt("MYNO"));
				dto.setMyid(rs.getString("MYID"));
				dto.setMypw(rs.getString("MYPW"));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMyaddr(rs.getString("MYADDR"));
				dto.setMyphone(rs.getString("MYPHONE"));
				dto.setMyemail(rs.getString("MYEMAIL"));
				dto.setMyenabled(rs.getString("MYENABLED"));
				dto.setMyrole(rs.getString("MYROLE"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
			System.out.println("5. db 종료");
		}
		
		return list;
	}
	

	

	
	
}

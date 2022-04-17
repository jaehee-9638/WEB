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
	 * ������(ADMIN) ����
	 * 1. ȸ�� ��ü ���� Ȯ�� (Ż���� ȸ���� ����)
	 * 2. ȸ�� ��ü ���� Ȯ�� (MYENABLED='Y'�� -> Ż����� ȸ������ ����)
	 * 3. ȸ�� ��� ���� (ADMIN <-> USER)
	 * 
	 * */
	
	//1.��ü����
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
			System.out.println("3. query �غ� : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query ���� �� ����");
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
			System.out.println("5. db ����");
		}
		
		return list;
	}
	

	

	
	
}

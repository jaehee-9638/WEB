package com.login.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MYMemberDto;
import static com.login.db.JDBCTemplate.*;

public class MYMemberDao {

	/*
	 * 관리자(ADMIN) 가능
	 * 1. 회원 전체 정보 확인 (탈퇴한 회원도 포함)
	 * 2. 회원 전체 정보 확인 (MYENABLED='Y'인 -> 탈퇴안한 회원들의 정보)
	 * 3. 회원 등급 조정 (ADMIN <-> USER)
	 * 
	 * */
	//1.전체정보-->selectList
	public List<MYMemberDto> selectMemberAll(){
		Connection con=getConnection();
		String sql=" SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE FROM MYMEMBER ORDER BY MYNO DESC ";
		PreparedStatement pstm =null;
		ResultSet rs=null;
		List<MYMemberDto> list=new ArrayList<MYMemberDto>();
		try {
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery();
			while (rs.next()) {
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
		}finally {
			close(rs,pstm,con);
		}
		
		return list;
		
	}
	
	//2.전체정보(탈퇴안한)-->seleceList (enabled가 y인 애들만 )
	public List<MYMemberDto> selectMemberEn(){
		Connection con=getConnection();
		String sql=" SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				+ "FROM MYMEMBER "
				+ " WHERE MYENABLED='Y' ";
		PreparedStatement pstm =null;
		ResultSet rs=null;
		List<MYMemberDto> list=new ArrayList<MYMemberDto>();
		try {
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery();
			while (rs.next()) {
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
		}finally {
			close(rs,pstm,con);
		}
		
		return list;
		
	}
	
	//3.회원등급 조정-->update (회원번호 조회해서 role조정하자)
	public int updateRole(int myno, String myrole) {
		Connection con= getConnection();
		
		PreparedStatement pstm= null;
		int res=0;
		
		String sql = " UPDATE MYMEMBER SET MYROLE=? WHERE MYNO=? ";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, myrole);
			pstm.setInt(2, myno);
			
			System.out.println("3. query 준비 : " + sql);
			
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
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
	
	/*
	 * 사용자(USER) 기능
	 * 1. 로그인
	 * 3. 회원가입 <- 2. 아이디 중복체크
	 * 4. 내 정보 조회
	 * 5. 내 정보 수정
	 * 6. 회원 탈퇴(delete 안쓰고 update : myenabled를 n으로 바꾼다)
	 * 
	 * */
	
	//1.로그인--> 로그인 화면에서 아이디 비밀번호 체크해서 맞으면 로그인 된 화면 으로 이동하자
	public MYMemberDto login (String myid,String mypw) {
		Connection con=getConnection();
		PreparedStatement pstm=null;
		String sql= " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE  "
				+ " FROM MYMEMBER WHERE MYID=? AND MYPW=? ";
		ResultSet rs=null;
		MYMemberDto dto = null;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			
			rs=pstm.executeQuery();
			while (rs.next()) {
				dto=new MYMemberDto();
				dto.setMyno(rs.getInt("MYNO"));
				dto.setMyid(rs.getString("MYID"));
				dto.setMypw(rs.getString("MYPW"));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMyaddr(rs.getString("MYADDR"));
				dto.setMyphone(rs.getString("MYPHONE"));
				dto.setMyemail(rs.getString("MYEMAIL"));
				dto.setMyenabled(rs.getString("MYENABLED"));
				dto.setMyrole(rs.getString("MYROLE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,pstm,con);
		}
		
		
		return dto;
	}
	
	
	//2.중복체크--> 회원가입 화면에서 unique 조건인 애들 다 중복 체크 하자 동일한 값있으면 실패 
	public MYMemberDto idcheck(String myid) {
		Connection con=getConnection();
		String sql =" SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE FROM MYMEMBER WHERE MYID=? ";
		PreparedStatement pstm=null;
		ResultSet rs=null;
		MYMemberDto dto =new MYMemberDto();
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, myid);
			rs=pstm.executeQuery();
			while(rs.next()) {
				dto.setMyno(rs.getInt("MYNO")) ;
				dto.setMyid(rs.getString("MYID"));
				dto.setMypw(rs.getString("MYPW"));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMyaddr(rs.getString("MYADDR"));
				dto.setMyphone(rs.getString("MYPHONE"));
				dto.setMyemail(rs.getString("MYEMAIL"));
				dto.setMyenabled(rs.getString("MYENABLED"));
				dto.setMyrole(rs.getString("MYROLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		
		
		return dto;
	}
	
	//3.회원가입--> 회원가입 페이지 만들자 ? 
	
	public int regist(MYMemberDto dto) {
		Connection con = getConnection();
		String sql=" INSERT INTO MYMEMBER VALUES(MYMEMBERSEQ.NEXTVAL,?,?,?,?,?,?,'Y','USER') ";
		PreparedStatement pstm=null;
		int res=0;
		try {
			pstm=con.prepareStatement(sql);
			
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			res=pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		
		return res;
	}
	//4.정보조회--> selectOne ( 마이페이지)
	public MYMemberDto selectUser(int myno) {
		Connection con=getConnection();
		String sql=" SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				+ " FROM MYMEMBER WHERE MYNO=? ";
		PreparedStatement pstm=null;
		ResultSet rs=null;
		MYMemberDto dto =null;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("3. query 준비 : " + sql);
			
			rs=pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while (rs.next()) {
				dto=new MYMemberDto();
				dto.setMyno(rs.getInt("MYNO"));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm,con);
			System.out.println("5. db 종료");
		}
		
		
		return dto;
		
	}
	//5.정보수정--> update (정보 지정해서 업데이트 하자 ) 일단 휴대폰 번호만 변경 해보자 
	//휴대폰 번호 되면 다른것들 수정 하자 
	public int updateUser(MYMemberDto dto) {
		Connection con = getConnection();
		String sql =" UPDATE MYMEMBER SET MYPHONE=? WHERE MYNO=?  ";
		PreparedStatement pstm=null;
		int res=0;
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getMyphone());
			pstm.setInt(2, dto.getMyno());
			res=pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm,con);
		}
		return res;
	}
	//6.회원탈퇴 (update) -->
}

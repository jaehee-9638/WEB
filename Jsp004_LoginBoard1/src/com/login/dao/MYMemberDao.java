package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MYMemberDto;
import static com.login.db.JDBCTemplate.*;

public class MYMemberDao {

	/*admin기능
	  1 회원전체정보 확인 (탈퇴한회원포함)
	  2 회원전체정보 확인 (탈퇴안한회원만 )
	  3 회원등급 조정
	 */
	
	//1 (admin)회원 전체 정보 확인 (탈퇴회원 포함)
	public List<MYMemberDto> selectUserListAll(){
		Connection con = getConnection();
		String sql =" SELECT MYNO,MYID,MYPW,MYNAME,MYADDR,MYPHONE,MYEMAIL,MYENABLED,MYROLE "
				+ " FROM MYMEMBER ";
		Statement stmt = null;
		ResultSet rs=null;
		List<MYMemberDto> list = new ArrayList<MYMemberDto>();
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				//화면에 뿌려줄거 세팅해서 리스트에 넣자 
				int myno =rs.getInt("MYNO");
				String myid=rs.getString("MYID");
				String mypw=rs.getString("MYPW");
				String myname=rs.getString("MYNAME");
				String myaddr=rs.getString("MYADDR");
				String myphone=rs.getString("MYPHONE");
				String myemail=rs.getString("MYEMAIL");
				String myenabled=rs.getString("MYENABLED");
				String myrole=rs.getString("MYROLE");
				MYMemberDto dto = new MYMemberDto(myno,myid,mypw,myname,myaddr,myphone,myemail,myenabled,myrole);
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
	
	//2 (admin)회원 전체 정보 확인(탈퇴회원 포함안함)
	public List<MYMemberDto> selectUserListEn(){
		Connection con = getConnection();
		String sql =" SELECT MYNO,MYID,MYPW,MYNAME,MYADDR,MYPHONE,MYEMAIL,MYENABLED,MYROLE "
				+ " FROM MYMEMBER WHERE MYENABLED ='Y' ";
		Statement stmt = null;
		ResultSet rs=null;
		List<MYMemberDto> list = new ArrayList<MYMemberDto>();
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				//화면에 뿌려줄거 세팅해서 리스트에 넣자 
				int myno =rs.getInt("MYNO");
				String myid=rs.getString("MYID");
				String mypw=rs.getString("MYPW");
				String myname=rs.getString("MYNAME");
				String myaddr=rs.getString("MYADDR");
				String myphone=rs.getString("MYPHONE");
				String myemail=rs.getString("MYEMAIL");
				String myenabled=rs.getString("MYENABLED");
				String myrole=rs.getString("MYROLE");
				MYMemberDto dto = new MYMemberDto(myno,myid,mypw,myname,myaddr,myphone,myemail,myenabled,myrole);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//3 (admin)회원 등급 조절 
	public int updateUserRole(int myno,String role) {
		Connection con = getConnection();
		String sql =" UPDATE MYMEMBER SET MYROLE=? WHERE MYNO=? ";
		PreparedStatement pstm =null;
		int res=0; 
		
		try {
			pstm =con.prepareStatement(sql);
			
			pstm.setString(1, role);
			pstm.setInt(2, myno);
			res=pstm.executeUpdate();
		
			if (res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("dao에서 res"+ res);
		return res;
	}
	
	/*user기능 
	  1 로그인
	  2 아이디 체크 -> 3번에 포함된 
	  3 회원가입
	  4 내정보 조회
	  5 내정보 수정
	  6 회원탈퇴(update)
	*/
	
	//1 (user)로그인 
	//로그인은 select조회 진행하는거고 아이디랑 pw똑같으면 로그인 되는거고 그 
	public MYMemberDto login (String myid,String mypw) {
		MYMemberDto dto = null;
		Connection con = getConnection();
		String sql =" SELECT MYNO,MYID,MYPW,MYNAME,MYADDR,MYPHONE,MYEMAIL,MYENABLED,MYROLE "
				+ " FROM MYMEMBER  WHERE MYID=? AND MYPW=? ";
		PreparedStatement pstm = null;
		ResultSet rs=null;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			rs=pstm.executeQuery();
			while (rs.next()) {
				dto=new MYMemberDto(rs.getInt("MYNO"),
								rs.getString("MYID"),
								rs.getString("MYPW"),
								rs.getString("MYNAME"),
								rs.getString("MYADDR"),
								rs.getString("MYPHONE"),
								rs.getString("MYEMAIL"),
								rs.getString("MYENABLED"),
								rs.getString("MYROLE"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;
	}
	//2 (user)아이디체크
	//아이디 체크는 들어오는 아이디 값  조회해서 출력해줄 내용은 select 해서 id 만 쭉 보내면 될듯 있는지 없는지 체크는 쟤네가 해주겠지 
	public MYMemberDto idchk(){
		Connection con = getConnection();
		Statement stmt = null;
		String sql =" SELECT MYID FROM MYMEMBER ";
		ResultSet rs = null;
		MYMemberDto dto = new MYMemberDto();
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
			String myid = rs.getString("MYID");
			dto.setMyid(myid);
			
			//담긴게 아니고 
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	//3 (user)회원가입
	public int regist(MYMemberDto dto){
		Connection con = getConnection();
		String sql = " INSERT INTO MYMEMBER VALUES ( MYMEMBERSEQ.NEXTVAL,?,?,?,?,?,?,'Y','USER') ";
		PreparedStatement pstm = null;
		
		int res =0;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			res=pstm.executeUpdate();
			if( res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	//4 (user)내정보 조회
	public MYMemberDto selectUser(int myno) {
		Connection con=getConnection();
		String sql =" SELECT MYNO,MYID,MYPW,MYNAME,MYADDR,MYPHONE,MYEMAIL,MYENABLED,MYROLE "
				+ "FROM MYMEMBER WHERE MYNO=? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MYMemberDto dto = null;
		try {
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, myno);
			rs=pstm.executeQuery();
			while(rs.next()) {
				dto=new MYMemberDto (rs.getInt("MYNO"),
						rs.getString("MYID"),
						rs.getString("MYPW"),
						rs.getString("MYNAME"),
						rs.getString("MYADDR"),
						rs.getString("MYPHONE"),
						rs.getString("MYEMAIL"),
						rs.getString("MYENABLED"),
						rs.getString("MYROLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	//4-1 내정보 조회인데 sql문만 아이디로 바꾼거 
	public MYMemberDto selectUser2(String myid) {
		Connection con=getConnection();
		String sql =" SELECT MYNO,MYID,MYPW,MYNAME,MYADDR,MYPHONE,MYEMAIL,MYENABLED,MYROLE "
				+ "FROM MYMEMBER WHERE MYID=? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MYMemberDto dto = null;
		try {
			pstm =con.prepareStatement(sql);
			pstm.setString(1, myid);
			rs=pstm.executeQuery();
			while(rs.next()) {
				dto=new MYMemberDto (rs.getInt("MYNO"),
						rs.getString("MYID"),
						rs.getString("MYPW"),
						rs.getString("MYNAME"),
						rs.getString("MYADDR"),
						rs.getString("MYPHONE"),
						rs.getString("MYEMAIL"),
						rs.getString("MYENABLED"),
						rs.getString("MYROLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	//5 (user)내정보 수정 update
	public int mypageUpdate(String mypw,String myaddr,String myphone, String myemail,int myno) {
		Connection con = getConnection();
		
		String sql =" UPDATE MYMEMBER SET MYPW =?,MYADDR=?,MYPHONE=?,MYEMAIL=? WHERE MYNO=? ";
		PreparedStatement pstm = null;
		int res =0;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, mypw );
			pstm.setString(2, myaddr);
			pstm.setString(3, myphone);
			pstm.setString(4, myemail);
			pstm.setInt(5, myno);
			
			res =pstm.executeUpdate();
			if (res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return res;
		
	}
	//6 (user)회원탈퇴 (update)
	
	public int idDrop (int myno) {
		Connection con = getConnection();
		String sql =" UPDATE MYMEMBER SET MYENABLED='N' WHERE MYNO=? ";
		PreparedStatement pstm =null;
		int res =0;
		
		try {
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, myno);
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
}

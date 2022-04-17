package com.cal.dao;

import static com.cal.db.JDBCTemplata.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;
public class CalDao {
	
	//글작성 : 아이디 제목 내용 일자 추가하는거임
	public int insertCalBoard(CalDto dto) {
		Connection con=getConnection();
		
		String sql =" INSERT INTO CALBOARD VALUES(CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE) ";
		PreparedStatement pstm =null;
		int res=0;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
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
		}
		return res;
		
	}
	//
	public List<CalDto> getCalList(String id,String yyyyMMdd){
		Connection con=getConnection();
		//MDATE: 선택된 날짜, REGDATE: 작성한 날짜
		String sql=" SELECT * "
				+ " FROM "
				+ " ( "
				+ " SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE))RN,SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
				+ " FROM CALBOARD "
				+ " WHERE ID=? "
				+ " AND SUBSTR(MDATE,1,6)=? "
				+ " ) "
				+ " WHERE RN BETWEEN 1 AND 3 ";
		//SUBSTR:MDATE기록한 것들 중 1~8번째자리까지 짤라오겠다.
		PreparedStatement pstm =null;
		ResultSet rs=null;	//값을 넣었다고 확인받으면 데이터, 표를 돌려받음
		List<CalDto> list=new ArrayList<CalDto>();
		try {
			pstm=con.prepareStatement(sql);	//물음표 개수만큼 pstm.해준다
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("3. query 준비 : " + sql);
			rs=pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			
			while (rs.next()) {
				CalDto dto = new CalDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
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
	//row_number() over(partition by @ order by)
	// @로 group by를 해서, 그룹별 rownum을 사용하고 싶을 때
	public List<CalDto> getCalViewList(String id,String yyyyMM){
		return null;
		
	}
	//카운트 칼보드 같은아이디에 같은 날짜에 쓴 글이 몇갠지 카운트 해서 보여주는 매소드 
	public int getCalViewCount(String id,String yyyyMMdd) {
		return 0;
		
	}

}

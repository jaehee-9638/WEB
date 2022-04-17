package com.bike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.dto.BikeDto;

public class BikeDao {
	//인서트 메소드 만들어주는데 리턴타입은 boolean형이고 파라미터는 바이크Dto리스트입
	public boolean insert(List<BikeDto> list) {
		//list안에 있는 값들 전체 저장 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="kh";
		String password="kh";
		
		Connection con=null;
		
		try {
			con= DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정 연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstm=null;
		int res=0; 
		String sql=" INSERT INTO KOREABIKE VALUES(?,?,?,?,?) ";
		
		try {
			pstm=con.prepareStatement(sql);
			
			for (int i=0; i<list.size(); i++) {
				pstm.setString(1, list.get(i).getName());
				pstm.setString(2, list.get(i).getAddr());
				pstm.setDouble(3, list.get(i).getLatitude());
				pstm.setDouble(4, list.get(i).getLongitude());
				pstm.setInt(5, list.get(i).getBike_count());
				
				// addBatch에 담기
				pstm.addBatch();
			}
			
			//addBatch를 통해 쿼리를 메모리에 올린다. 이후 executeBatch 명령어를 통해 쿼리를 전송한다.
			System.out.println("3. query 준비 : " + sql);
			//.executeBatch() : 쿼리 전송 
			int[] result =pstm.executeBatch();
			System.out.println("4. query 실행 및 리턴");
			for (int i=0; i<result.length; i++) {
				if (result[i] == -2) {
					res++;
					//
				}
			}
			//동작 끝까지 하면 커밋 아니면 롤백 
			if (res==list.size()) {
				con.commit();
			}else {
				con.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				con.close();
				System.out.println("5.db종료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//동작끝나면 트루 동작 끝까지 안된거면 펄스 
		return (res==list.size())?true : false;
	}
	
	public boolean delete () {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="kh";
		String password="kh";
		
		Connection con=null;
		
		try {
			con=DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql =" DELETE FROM KOREABIKE ";
		PreparedStatement pstm =null;
		int res=0; 
		
		try {
			pstm=con.prepareStatement(sql);
			System.out.println("3. query 준비 : " + sql);
			
			res=pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			if(res>0) {
				con.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//res가 0보다 크면 true리턴 그렇지않으면 false리턴 
		return (res>0)? true:false;
	}

}

package com.board.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	//private를 해서 해당 클래스 내에서만 접근 가능 
	private SqlSessionFactory sqlSessionFactory;

	//게터 로 값 가져올순잇고 세터는 없어서 설정은 못함 
	//해당 페이지에서 설정해줘야한다.
	public SqlSessionFactory getSqlSessionFactory() {
		
		String resource = "com/board/db/config.xml";
		//reader(문위단위)말고 inputStream(바이트단위)으로 해줬음 
		InputStream inputStream =null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
		
		return sqlSessionFactory;
	}

	
	
	
}

package com.myboard.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	
	private SqlSessionFactory sqlSessionFactory;
	
	
	public SqlSessionFactory getSqlSessionFactory() {
	
		String resource = "com/myboard/db/mybatis-config.xml";
		
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			inputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
		

	}
	
}

package com.login.db;

import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		
		String resource = "com/daytwo/db/mybatis-config.xml";
		// inputstream 있어서 reader생략 하자 Reader reader=null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sqlSessionFactory;
		
	}
	
}

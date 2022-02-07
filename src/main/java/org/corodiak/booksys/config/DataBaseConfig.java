package org.corodiak.booksys.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * DB-Mybatis 관련 빈 생성 객체
 */
@Configuration
public class DataBaseConfig
{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	/*
	 * Mybatis 연동을 위한 SqlSessionFactory 빈 등록
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception
	{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/config/mapper/*-mapper.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	
	/*
	 * Mybatis 연동을 위한 SqlSession 빈 등록, 팩토리 패턴을 이용한 생성
	 */
	@Bean
	public SqlSession sqlSession() throws Exception
	{
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
}

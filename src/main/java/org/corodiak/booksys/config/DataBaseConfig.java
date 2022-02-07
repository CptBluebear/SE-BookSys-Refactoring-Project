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
 * DB-Mybatis ���� �� ���� ��ü
 */
@Configuration
public class DataBaseConfig
{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	/*
	 * Mybatis ������ ���� SqlSessionFactory �� ���
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
	 * Mybatis ������ ���� SqlSession �� ���, ���丮 ������ �̿��� ����
	 */
	@Bean
	public SqlSession sqlSession() throws Exception
	{
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
}

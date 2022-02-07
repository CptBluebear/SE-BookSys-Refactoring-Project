package org.corodiak.booksys.mapper;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.corodiak.booksys.mapper.TestDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

//DB ��ü�� �׽�Ʈ
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = DataBaseConfig.class)
//@SpringJUnitWebConfig(classes = {DataBaseConfig.class, RootConfig.class})
@SpringJUnitWebConfig
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class DBConTest
{
	@Autowired
	private ApplicationContext applicationContext;
	
	private DataSource dataSource;

	private SqlSession sqlSession;
	
	//TOMCAT ���� ���ϰ� �׽�Ʈ �ϱ����� DB Ŀ�ؼ�Ǯ ���� ����, �����δ� JDNI�� ������ ��
	@BeforeAll
	public void setup() throws Exception
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://192.168.0.120:33306/SE001");
		dataSource.setUsername("se001");
		dataSource.setPassword("semonqwerty1234");
		this.dataSource = dataSource;
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource);
		sqlSessionFactoryBean.setConfigLocation(this.applicationContext.getResource("classpath:/config/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(this.applicationContext.getResources("classpath:/config/mapper/*-mapper.xml"));
		
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
		sqlSession = sqlSessionTemplate;
	}
	
	//DataSource ���� �׽�Ʈ
	@Test
	public void testDataSourceTest() throws Exception
	{
		try
		{
			Connection connection = dataSource.getConnection();
			connection.close();
		}
		catch(Exception e)
		{
			fail("Exception Found!!");
		}
	}
	
	//SqlSession ���� �׽�Ʈ
	@Test
	public void testSqlSession() throws InterruptedException
	{
		TestDao testDao1 = sqlSession.getMapper(TestDao.class);
		assertEquals(1, testDao1.test());
	}
}
package org.corodiak.booksys.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.corodiak.booksys.domain.WalkIn;
import org.corodiak.booksys.mapper.WalkInMapper;
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


@SpringJUnitWebConfig
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class WalkInMapperTest
{
	@Autowired
	private ApplicationContext applicationContext;
	
	private DataSource dataSource;

	private SqlSession sqlSession;
	
	private WalkInMapper walkInMapper;
	
	@BeforeAll
	public void setup() throws Exception
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://192.168.0.120:33306/SE101");
		dataSource.setUsername("se001");
		dataSource.setPassword("semonqwerty1234");
		this.dataSource = dataSource;
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource);
		sqlSessionFactoryBean.setConfigLocation(this.applicationContext.getResource("classpath:/config/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(this.applicationContext.getResources("classpath:/config/mapper/*-mapper.xml"));
		
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
		sqlSession = sqlSessionTemplate;
		
		walkInMapper = sqlSession.getMapper(WalkInMapper.class);
	}
	
	@Test
	public void test()
	{
		WalkIn walkIn = walkInMapper.selectOne();
		assertEquals(3, walkIn.getBookingCovers());
		assertEquals(1, walkIn.getWalkInTable().getTableNumber());
	}
}

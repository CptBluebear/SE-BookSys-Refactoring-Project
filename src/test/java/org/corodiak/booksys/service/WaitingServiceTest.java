package org.corodiak.booksys.service;

import javax.sql.DataSource;

import org.corodiak.booksys.config.DataBaseConfig;
import org.corodiak.booksys.config.RootConfig;
import org.corodiak.booksys.config.ServiceConfig;
import org.corodiak.booksys.config.VoidConfig;
import org.corodiak.booksys.mapper.MapperRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitWebConfig
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {RootConfig.class, DataBaseConfig.class, MapperRepository.class, VoidConfig.class, ServiceConfig.class})
public class WaitingServiceTest
{
	@Autowired
	WaitingService waitingService;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	DataSource dataSource;
	
	@BeforeAll
	public void setup()
	{
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		beanFactory.registerSingleton(transactionManager.getClass().getCanonicalName(), transactionManager);
	}
	
	@Test
	@Transactional
	public void test()
	{
		
	}
}

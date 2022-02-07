package org.corodiak.booksys.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import javax.sql.DataSource;

import org.corodiak.booksys.config.DataBaseConfig;
import org.corodiak.booksys.config.RootConfig;
import org.corodiak.booksys.config.ServiceConfig;
import org.corodiak.booksys.config.VoidConfig;
import org.corodiak.booksys.domain.Customer;
import org.corodiak.booksys.mapper.MapperRepository;
import org.corodiak.booksys.util.JWTUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitWebConfig
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {RootConfig.class, DataBaseConfig.class, MapperRepository.class, VoidConfig.class, ServiceConfig.class})
public class CustomerServiceTest
{
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomerService customerService;
	
	@BeforeAll
	public void setup()
	{
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		beanFactory.registerSingleton(transactionManager.getClass().getCanonicalName(), transactionManager);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCustomerServiceRegister()
	{
		
		try {
			customerService.register("test2", "test2", "test2", "01011111111", "1998-01-24");
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCustomerServiceLogin()
	{
		String id = "test1";
		String password = "test1";
		String jwt = null;
		try {
			jwt = customerService.login(id, password);
		} catch (Exception e) {
			fail(e.toString());
		}
		
		assertNotNull(jwt);
		assertEquals(50, JWTUtil.validateToken(jwt).get("customerOid"));
	}
}

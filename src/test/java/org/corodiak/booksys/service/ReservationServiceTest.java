package org.corodiak.booksys.service;

import java.sql.Date;
import java.sql.Time;

import javax.sql.DataSource;

import org.corodiak.booksys.config.DataBaseConfig;
import org.corodiak.booksys.config.RootConfig;
import org.corodiak.booksys.config.ServiceConfig;
import org.corodiak.booksys.config.VoidConfig;
import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.mapper.MapperRepository;
import org.corodiak.booksys.mapper.ReservationMapper;
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
public class ReservationServiceTest
{
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ReservationService reservationService;
	
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
	public void testCreateReservation() throws Exception
	{
		int covers = 4;
		String date = "2021-05-12";
		String time = "19:00:00";
		int customerOid = 50;
		int tableOid = 1;
		
		reservationService.createReservation(covers, date, time, customerOid, tableOid);
	}
}

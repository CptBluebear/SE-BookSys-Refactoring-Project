package org.corodiak.booksys.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.corodiak.booksys.config.DataBaseConfig;
import org.corodiak.booksys.config.RootConfig;
import org.corodiak.booksys.config.VoidConfig;
import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.BookingImpl;
import org.corodiak.booksys.domain.Customer;
import org.corodiak.booksys.domain.Menu;
import org.corodiak.booksys.domain.Order;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.domain.Table;
import org.corodiak.booksys.domain.Waiting;
import org.corodiak.booksys.domain.WalkIn;
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
@ContextConfiguration(classes = {RootConfig.class, DataBaseConfig.class, MapperRepository.class, VoidConfig.class})
public class MapperTest
{
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BookingMapper bookingMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ReservationMapper reservationMapper;
	@Autowired
	private TableMapper tableMapper;
	@Autowired
	private WaitingMapper waitingMapper;
	@Autowired
	private WalkInMapper walkInMapper;
	
	/*
	@BeforeAll
	public void setup() throws Exception
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://192.168.0.120:33306/SE101");
		dataSource.setUsername("se001");
		dataSource.setPassword("semonqwerty1234");
		this.dataSource = dataSource;
		
		transactionManager = new DataSourceTransactionManager(this.dataSource);
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource);
		sqlSessionFactoryBean.setConfigLocation(this.applicationContext.getResource("classpath:/config/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(this.applicationContext.getResources("classpath:/config/mapper/*-mapper.xml"));
		
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
		sqlSession = sqlSessionTemplate;
		
		bookingMapper = sqlSession.getMapper(BookingMapper.class);
		customerMapper = sqlSession.getMapper(CustomerMapper.class);
		menuMapper = sqlSession.getMapper(MenuMapper.class);
		orderMapper = sqlSession.getMapper(OrderMapper.class);
		reservationMapper = sqlSession.getMapper(ReservationMapper.class);
		tableMapper = sqlSession.getMapper(TableMapper.class);
		waitingMapper = sqlSession.getMapper(WaitingMapper.class);
		walkInMapper = sqlSession.getMapper(WalkInMapper.class);
	}
	*/
	
	//이는 @Transactional 등을 사용하기 위함
	@BeforeAll
	public void setup()
	{
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		beanFactory.registerSingleton(transactionManager.getClass().getCanonicalName(), transactionManager);
	}
	
	//Mapper 생성 테스트
	@Test
	public void testCreateMapper()
	{
		assertNotNull(bookingMapper);
		assertNotNull(customerMapper);
		assertNotNull(menuMapper);
		assertNotNull(orderMapper);
		assertNotNull(reservationMapper);
		assertNotNull(tableMapper);
		assertNotNull(waitingMapper);
		assertNotNull(walkInMapper);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testMenuMapperInsertMenu()
	{
		Menu menu = new Menu();
		menu.setMenuName("대홍단감자");
		menu.setMenuPrice(100000);
		menu.setMenuDescription("동글동글 맛있는 대홍단 감자");
		menu.setMenuImagePath("testPathtestPathtestPath");
		menuMapper.insertMenu(menu);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCustomerMapperInsertCustomer()
	{
		Customer customer = new Customer();
		customer.setCustomerId("ttttt");
		customer.setCustomerPassword("ttttt");
		customer.setCustomerName("홍길동");
		customer.setCustomerPhoneNumber("01012345678");
		Date date = Date.valueOf("2017-12-03");
		customer.setCustomerBirthday(date);
		customerMapper.insertCustomer(customer);
	}
	
	/*
	@Test
	@Transactional
	@Rollback
	public void testTableMapperInsertTable()
	{
		Table table = new Table();
		table.setTableNumber(10);
		table.setTablePlaces(5);
		tableMapper.insertTable(table);
	}
	*/
	@Test
	@Transactional
	@Rollback
	public void testBookingMapperInsertBooking()
	{
		Booking booking = new BookingImpl();
		booking.setBookingCovers(3);
		Date date = Date.valueOf("2010-10-03");
		Time time = Time.valueOf("11:11:11");
		booking.setBookingDate(date);
		booking.setBookingTime(time);
		bookingMapper.insertBooking(booking);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testWalkInMapperInsertWalkIn()
	{
		WalkIn walkIn = new WalkIn();
		walkIn.setBookingOid(3);
		walkInMapper.insertWalkIn(walkIn, 2);
	}
	

	@Test
	@Transactional
	@Rollback
	public void testOrderMapperInsertOrder()
	{
		Order order1 = new Order();
		order1.setOrderBookingOid(2);
		order1.setOrderQuantity(3);
		orderMapper.insertOrder(order1, 2);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testReservationMapperInsertReservation()
	{
		Reservation reservation = new Reservation();
		reservation.setBookingOid(3);
		reservation.setReservationAuthPassword("1234");
		try
		{
			reservationMapper.insertReservation(reservation, 1, 2);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback
	public void testWaitingMapperInsertWaiting()
	{
		Waiting waiting = new Waiting();
		waiting.setBookingOid(1);
		waitingMapper.insertWaiting(waiting, 1);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testReservationMapperDeleteReservationByOid()
	{
		int oid = 53;
		int result = reservationMapper.deleteReservationByOid(oid);
		System.out.println(result);
	}
	
	@Test
	public void testCustomerMapperSelectCustomerByOid()
	{
		int oid = 93;
		Customer customer = customerMapper.selectCustomerByOid(oid);
		assertEquals("sdst74", customer.getCustomerId());
	}
	
	@Test
	public void testBookingMapperSelectFilledDateByDate()
	{
		for(Booking booking:bookingMapper.selectFilledDataByDate("2021-05-28"))
		{
			System.out.println(booking.getBookingOid() + " : " + booking.getBookingTime().toString());
		}
	}
	
	@Test
	public void testReservationByDate()
	{
		List<Booking> reservation =  reservationMapper.selectReservationByDate("2021-05-28");
		List<Booking> walkIn = walkInMapper.selectWalkInByDate("2021-05-28");
		reservation.addAll(walkIn);
		for(Booking booking:reservation)
		{
			System.out.println(booking.toString());
		}
	}
	
	@Test
	public void testTableMapperSelectTableCount()
	{
		assertEquals(5,tableMapper.selectTableList().size());
		List<Table> tables = tableMapper.selectTableList();
		for(Table table:tables) System.out.println(table.getTableNumber());
	}
	
	/*
	@Test
	@Transactional
	@Rollback
	public void testCustomerInsertCustomer()
	{
		Customer customer = new Customer();
		customer.setCustomerId("ttttt");
		customer.setCustomerPassword("ttttt");
		customer.setCustomerName("홍길동");
		Date date = Date.valueOf("2017-12-03");
		customer.setCustomerBirthday(date);
		customerMapper.insertCustomer(customer);
	}
	*/
}

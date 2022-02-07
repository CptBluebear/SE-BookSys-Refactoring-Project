package org.corodiak.booksys.config;

import org.corodiak.booksys.service.BookingService;
import org.corodiak.booksys.service.BookingServiceImpl;
import org.corodiak.booksys.service.CustomerService;
import org.corodiak.booksys.service.CustomerServiceImpl;
import org.corodiak.booksys.service.MenuService;
import org.corodiak.booksys.service.MenuServiceImpl;
import org.corodiak.booksys.service.OrderService;
import org.corodiak.booksys.service.OrderServiceImpl;
import org.corodiak.booksys.service.ReservationService;
import org.corodiak.booksys.service.ReservationServiceImpl;
import org.corodiak.booksys.service.TableService;
import org.corodiak.booksys.service.TableServiceImpl;
import org.corodiak.booksys.service.WaitingService;
import org.corodiak.booksys.service.WaitingServiceImpl;
import org.corodiak.booksys.service.WalkInService;
import org.corodiak.booksys.service.WalkInServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 서비스 빈 등록 클래스, 나중에 @Service 어노테이션으로 바꾸고 컴포넌트 스캔 해야할 부분
 */
@Configuration
public class ServiceConfig
{
	@Bean
	public CustomerService customerService()
	{
		return new CustomerServiceImpl();
	}
	
	@Bean
	public ReservationService reservationService()
	{
		return new ReservationServiceImpl();
	}
	
	@Bean
	public MenuService menuService()
	{
		return new MenuServiceImpl();
	}
	
	@Bean
	public BookingService bookingService()
	{
		return new BookingServiceImpl();
	}
	
	@Bean
	public TableService tableService()
	{
		return new TableServiceImpl();
	}
	
	@Bean
	public WaitingService waitingService()
	{
		return new WaitingServiceImpl();
	}
	
	@Bean
	public WalkInService walkInService()
	{
		return new WalkInServiceImpl();
	}
	
	@Bean
	public OrderService orderService()
	{
		return new OrderServiceImpl();
	}
}
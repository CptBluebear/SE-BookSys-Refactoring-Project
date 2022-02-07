package org.corodiak.booksys.service;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import org.corodiak.booksys.config.DataBaseConfig;
import org.corodiak.booksys.config.RootConfig;
import org.corodiak.booksys.config.ServiceConfig;
import org.corodiak.booksys.config.VoidConfig;
import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.domain.WalkIn;
import org.corodiak.booksys.mapper.MapperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringJUnitWebConfig
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {RootConfig.class, DataBaseConfig.class, MapperRepository.class, VoidConfig.class, ServiceConfig.class})
public class BookingServiceTest
{
	@Autowired
	BookingService bookingService;
	
	@Test
	public void testBookingServiceReadBookginByDate()
	{
		String date = "2021-05-28";
		System.out.println("test");
		List<Booking> list = bookingService.readBookingByDate(date);
		System.out.println(list.toString());
		for(Booking booking:list)
		{
			System.out.println(booking.toString());
			System.out.println(booking instanceof Reservation);
			System.out.println(booking instanceof WalkIn);
		}
	}
	
	@Test
	public void testBookingGenerateTimeTable()
	{
		String[] arr = ((BookingServiceImpl)(bookingService)).generateTimeTable();
		for(String s:arr) System.out.println(s);
	}
	
	@Test
	public void test()
	{
		System.out.println("TESTTEST");
		int [][] arr = ((BookingServiceImpl)bookingService).bookingArrayByDate("2021-05-28");
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[i].length;j++)
			{
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

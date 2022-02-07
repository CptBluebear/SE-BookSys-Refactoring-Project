package org.corodiak.booksys.service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.domain.Table;
import org.corodiak.booksys.domain.WalkIn;
import org.corodiak.booksys.mapper.BookingMapper;
import org.corodiak.booksys.mapper.ReservationMapper;
import org.corodiak.booksys.mapper.TableMapper;
import org.corodiak.booksys.mapper.WalkInMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingServiceImpl implements BookingService
{

	@Autowired
	ReservationMapper reservationMapper;
	
	@Autowired
	WalkInMapper walkInMapper;
	
	@Autowired
	BookingMapper bookingMapper;
	
	@Autowired
	TableMapper tableMapper;
	
	Time startTime;
	Time endTime;
	int timegap;
	
	/*
	 * Constructor
	 */
	public BookingServiceImpl()
	{
		startTime = Time.valueOf("18:00:00");
		endTime = Time.valueOf("22:00:00");
		timegap = 30;
	}
	
	/*
	 * 날짜에 따른 Booking List 불러오기
	 */
	@Override
	public List<Booking> readBookingByDate(String date)
	{
		List<Booking> list =  reservationMapper.selectReservationByDate(date);
		List<Booking> list2 = walkInMapper.selectWalkInByDate(date);
		
		list.addAll(list2);
		
		return list;
	}
	
	//booking배열 리턴
	public int[][] bookingArrayByDate(String date)
	{
		List<Booking> list = readBookingByDate(date);
		List<Table> tableList = tableMapper.selectTableList();
		int tableNum = tableList.size();
		int[][] bookingArray = generateBookingArray(tableNum);
		
		LocalTime time1 = startTime.toLocalTime();
		
		for(Booking booking:list)
		{
			LocalTime time = booking.getBookingTime().toLocalTime();
			int indexTime = (int)(Duration.between(time1, time).toMinutes() / timegap);
			for(int i=0;i<tableNum;i++)
			{
				if(booking instanceof Reservation)
				{
					if( ((Reservation)booking).getReservationTable().getTableNumber() == tableList.get(i).getTableNumber() )
					{
						for(int j=0;j<4;j++)
						{
							if(indexTime+j < bookingArray[i].length)
							{
								bookingArray[i][indexTime+j] = 1;
							}
							else break;
						}
						break;
					}
				}
				else
				{
					if( ((WalkIn)booking).getWalkInTable().getTableNumber() == tableList.get(i).getTableNumber() )
					{
						for(int j=0;j<4;j++)
						{
							if(indexTime+j < bookingArray[i].length)
							{
								bookingArray[i][indexTime+j] = 1;
							}
							else break;
						}
						break;
					}
				}
			}
		}
		
		return bookingArray;
	}
	
	/*
	 * 예약 가능 시간대 처리
	 */
	public String[] generateTimeTable()
	{
		LocalTime time1 = startTime.toLocalTime();
		LocalTime time2 = endTime.toLocalTime();
		
		int timeset = (int)(Duration.between(time1, time2).toMinutes() / timegap);
		
		String []  timeTable = new String[timeset+1];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		for(int i=0;i<timeTable.length;i++)
		{
			timeTable[i] = time1.format(dtf);
			time1 = time1.plusMinutes(timegap);
		}
		
		return timeTable;
	}
	
	/*
	 * 예약 여부 Array 처리
	 */
	public int[][] generateBookingArray(int tablenum)
	{
		LocalTime time1 = startTime.toLocalTime();
		LocalTime time2 = endTime.toLocalTime();
		
		int timeset = (int)(Duration.between(time1, time2).toMinutes() / timegap);
		int [][] bookginArray = new int[tablenum][timeset+1];
		return bookginArray;
	}
}

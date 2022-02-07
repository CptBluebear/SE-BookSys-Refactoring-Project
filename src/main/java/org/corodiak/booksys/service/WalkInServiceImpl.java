package org.corodiak.booksys.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.WalkIn;
import org.corodiak.booksys.mapper.BookingMapper;
import org.corodiak.booksys.mapper.WalkInMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class WalkInServiceImpl implements WalkInService
{

	@Autowired
	WalkInMapper walkInMapper;
	
	@Autowired
	BookingMapper bookingMapper;
	
	/*
	 * 현장 예약 생성
	 */
	@Override
	public boolean createWalkIn(int covers, String date, String time, int tableOid)
	{
		WalkIn walkIn = new WalkIn();
		walkIn.setBookingCovers(covers);
		walkIn.setBookingDate(Date.valueOf(date));
		walkIn.setBookingTime(Time.valueOf(time));
		bookingMapper.insertBooking(walkIn);
		walkInMapper.insertWalkIn(walkIn, tableOid);
		
		return true;
	}

	/*
	 * 지정날짜 현장예약 목록 가져오기
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WalkIn> readWalkInByDate(String date)
	{
		List<Booking> walkInList = walkInMapper.selectWalkInByDate(date);
		return (List<WalkIn>)((List<?>)walkInList);
	}

}

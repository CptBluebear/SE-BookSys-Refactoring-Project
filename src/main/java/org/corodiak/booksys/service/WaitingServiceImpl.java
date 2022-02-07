package org.corodiak.booksys.service;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.domain.Table;
import org.corodiak.booksys.domain.Waiting;
import org.corodiak.booksys.domain.WalkIn;
import org.corodiak.booksys.mapper.BookingMapper;
import org.corodiak.booksys.mapper.ReservationMapper;
import org.corodiak.booksys.mapper.TableMapper;
import org.corodiak.booksys.mapper.WaitingMapper;
import org.corodiak.booksys.mapper.WalkInMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class WaitingServiceImpl implements WaitingService
{
	
	@Autowired
	BookingMapper bookingMapper;
	
	@Autowired
	WaitingMapper waitingMapper;
	
	@Autowired
	WalkInMapper walkInMapper;
	
	@Autowired
	ReservationMapper reservationMapper;
	
	@Autowired
	TableMapper tableMapper;
	
	@Autowired
	ReservationService reservationService;

	/*
	 * 예약 대기 생성
	 */
	@Override
	public boolean createWaiting(int covers, String date, String time, int customerOid)
	{
		try
		{
			Waiting waiting = new Waiting();
			waiting.setBookingCovers(covers);
			waiting.setBookingDate(Date.valueOf(date));
			waiting.setBookingTime(Time.valueOf(time));
			bookingMapper.insertBooking(waiting);
			waitingMapper.insertWaiting(waiting, customerOid);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/*
	 * 고객 정보에 따른 예약대기 정보 가져오기
	 */
	@Override
	public List<Waiting> readWaitingByCustomerOid(int customerOid)
	{
		return waitingMapper.selectWaitingByCustomerOid(customerOid);
	}

	/*
	 * 예약 대기 삭제
	 */
	@Override
	public boolean removeWaiting(int oid)
	{
		Waiting waiting = waitingMapper.selectWaitingByOid(oid);
		if(waiting == null) return false;
		bookingMapper.deleteBookingByOid(waiting.getBookingOid());
		waitingMapper.deleteWaitingByOid(oid);
		return true;
	}
	
	/*
	 * 예약 대기 체크하여 예약 가능하면 대기열에서 없애고 실제로 예약 처리
	 */
	public void checkWaiting()
	{
		List<Waiting> waitingList = waitingMapper.selectWaitingList();
		List<Table> tableList = tableMapper.selectTableList();
		for(Waiting waiting:waitingList)
		{
			Date date = waiting.getBookingDate();
			Time time1 = Time.valueOf(waiting.getBookingTime().toLocalTime().minusMinutes(119));
			Time time2 = Time.valueOf(waiting.getBookingTime().toLocalTime().plusMinutes(119));
			List<Booking> reservationList = reservationMapper.selectReservationByDateAndBetweenTime(date, time1, time2);
			List<Booking> walkInList = walkInMapper.selectWalkInByDateAndBetweenTime(date, time1, time2);
			
			if(tableList.size() <= reservationList.size() + walkInList.size()) continue;
			else
			{
				Set<Integer> tableOidList = new HashSet<Integer>();
				for(Table table:tableList) tableOidList.add(table.getTableOid());
				Set<Integer> bookedTableOidList = new HashSet<Integer>();
				for(Booking reservation:reservationList) bookedTableOidList.add(((Reservation)reservation).getReservationTable().getTableOid());
				for(Booking walkIn:walkInList) bookedTableOidList.add(((WalkIn)walkIn).getWalkInTable().getTableOid());
				
				tableOidList.removeAll(bookedTableOidList);
				Iterator<Integer> tables = tableOidList.iterator();
				
				reservationService.createReservation(
						waiting.getBookingCovers(),
						waiting.getBookingDate().toString(),
						waiting.getBookingTime().toString(),
						waiting.getWaitingCustomer().getCustomerOid(),
						tables.next()
						);
				removeWaiting(waiting.getWaitingOid());
			}
		}
	}

}

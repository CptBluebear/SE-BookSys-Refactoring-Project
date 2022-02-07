package org.corodiak.booksys.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.mapper.BookingMapper;
import org.corodiak.booksys.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationServiceImpl implements ReservationService
{
	@Autowired
	ReservationMapper reservationMapper;
	
	@Autowired
	BookingMapper bookingMapper;
	
	Random random;
	
	/*
	 * Constructor
	 */
	public ReservationServiceImpl()
	{
		random = new Random();
	}

	@Override
	public boolean createReservation(int covers, String date, String time, int customerOid, int tableOid)
	{
		try
		{
			//예약 중복 검증 과정 필요
			Reservation reservation = new Reservation();
			reservation.setBookingCovers(covers);
			reservation.setBookingDate(Date.valueOf(date));
			reservation.setBookingTime(Time.valueOf(time));
			reservation.setReservationAuthPassword(generateAuthPassword());
			bookingMapper.insertBooking((Booking)reservation);
			reservationMapper.insertReservation(reservation, customerOid, tableOid);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String generateAuthPassword()
	{
		return Integer.toString(random.nextInt(9000) + 1000);
	}

	@Override
	public List<Reservation> readReservationByCustomerOid(int customerOid)
	{
		return reservationMapper.selectReservationByCustomerOid(customerOid);
	}

	@Override
	public boolean removeReservation(int oid)
	{
		Reservation reservation = reservationMapper.selectReservationByOid(oid);
		if(reservation == null) return false;
		bookingMapper.deleteBookingByOid(reservation.getBookingOid());
		reservationMapper.deleteReservationByOid(oid);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> readReservationByDate(String date)
	{
		List<Booking> reservationList = reservationMapper.selectReservationByDate(date);
		return (List<Reservation>)((List<?>)reservationList);
	}
	
	@Override
	public Reservation readReservationByOid(int oid)
	{
		return reservationMapper.selectReservationByOid(oid);
	}

	@Override
	public boolean updateArrivalTimeByOid(int oid)
	{
		reservationMapper.updateReservationArrivalTimeByOid(Time.valueOf(LocalTime.now()), oid);
		return true;
	}
	
}

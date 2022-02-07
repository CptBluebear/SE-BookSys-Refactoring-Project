package org.corodiak.booksys.domain;

import java.sql.Date;
import java.sql.Time;

/*
 * Booking 인터페이스의 구현
 */
public class BookingImpl implements Booking
{
	protected int bookingOid;
	protected int bookingCovers;
	protected Date bookingDate;
	protected Time bookingTime;
	
	public BookingImpl() {}

	@Override
	public int getBookingOid() { return bookingOid; }
	
	@Override
	public int getBookingCovers() { return bookingCovers; }

	@Override
	public Date getBookingDate() { return bookingDate; }

	@Override
	public Time getBookingTime(){ return bookingTime; }
	
	@Override
	public void setBookingOid(int bookingOid) { this.bookingOid = bookingOid; }

	@Override
	public void setBookingCovers(int bookingCovers) { this.bookingCovers = bookingCovers; }

	@Override
	public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

	@Override
	public void setBookingTime(Time bookingTime) { this.bookingTime = bookingTime; }

	@Override
	public String toString()
	{
		return "BookingImpl [bookingOid=" + bookingOid + ", bookingCovers=" + bookingCovers + ", bookingDate="
				+ bookingDate + ", bookingTime=" + bookingTime + "]";
	}
}

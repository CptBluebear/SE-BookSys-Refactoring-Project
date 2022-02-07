package org.corodiak.booksys.domain;

import java.sql.Date;
import java.sql.Time;

/*
 * Booking 인터페이스, Reservation, WalkIn, Waiting의 기반
 */
public interface Booking
{
	
	public int getBookingOid();
	public int getBookingCovers();
	public Date getBookingDate();
	public Time getBookingTime();
	
	public void setBookingOid(int oid);
	public void setBookingCovers(int covers);
	public void setBookingDate(Date date);
	public void setBookingTime(Time time);
}

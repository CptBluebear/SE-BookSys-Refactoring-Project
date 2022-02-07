package org.corodiak.booksys.domain;

import java.sql.Date;
import java.sql.Time;

/*
 * Booking �������̽�, Reservation, WalkIn, Waiting�� ���
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

package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.Booking;

public interface BookingService
{
	public List<Booking> readBookingByDate(String date);
	public int[][] bookingArrayByDate(String date);
	public String[] generateTimeTable();
	public int[][] generateBookingArray(int tablenum);
}

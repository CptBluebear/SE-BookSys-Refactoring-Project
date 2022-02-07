package org.corodiak.booksys.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.corodiak.booksys.domain.Booking;

/*
 * Booking Table Ã³¸®
 */
@Mapper
public interface BookingMapper
{
	List<Booking> selectBookingListByDate(@Param("date")Date date);
	
	int insertBooking(@Param("booking")Booking booking);
	
	List<Booking> selectFilledDataByDate(@Param("date")String date);
	
	int deleteBookingByOid(@Param("oid")int oid);
}

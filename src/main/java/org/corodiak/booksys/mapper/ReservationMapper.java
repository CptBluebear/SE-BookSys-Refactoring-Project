package org.corodiak.booksys.mapper;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.Reservation;

/*
 * Reservation Table Ã³¸®
 */
@Mapper
public interface ReservationMapper
{
	List<Booking> selectBookingList();
	List<Booking> selectBookingList(RowBounds rowBounds);
	
	int insertReservation(@Param("reservation")Reservation reservation, @Param("customerOid")int customerOid, @Param("tableOid")int tableOid) throws Exception;

	int deleteReservationByOid(@Param("oid")int oid);
	
	List<Booking> selectReservationByDate(@Param("date")String date);
	List<Reservation> selectReservationByCustomerOid(@Param("customerOid")int customerOid);
	
	Reservation selectReservationByOid(@Param("oid")int oid);
	
	int updateReservationArrivalTimeByOid(@Param("arrivalTime")Time arrivaltime, @Param("oid")int oid);
	
	List<Booking> selectReservationByDateAndBetweenTime(@Param("date")Date date, @Param("time1")Time time1, @Param("time2")Time time2);
}

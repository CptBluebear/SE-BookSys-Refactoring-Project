package org.corodiak.booksys.mapper;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.corodiak.booksys.domain.Booking;
import org.corodiak.booksys.domain.WalkIn;

/*
 * WalkIn Table Ã³¸®
 */
@Mapper
public interface WalkInMapper
{
	WalkIn selectOne();
	//List<WalkIn> selectOne();
	
	int insertWalkIn(@Param("walkIn")WalkIn walkIn, @Param("tableOid")int tableOid);
	
	List<Booking> selectWalkInByDate(@Param("date")String date);
	
	List<Booking> selectWalkInByDateAndBetweenTime(@Param("date")Date date, @Param("time1")Time time1, @Param("time2")Time time2);
}

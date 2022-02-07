package org.corodiak.booksys.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.booksys.domain.Order;

/*
 * Order Table Ã³¸®
 */
@Mapper
public interface OrderMapper
{
	Order selectOrderByOid(@Param("oid")int oid);
	List<Order> selectOrderList();
	List<Order> selectOrderList(RowBounds rowBounds);
	
	List<Order> selectOrderByDate(@Param("date")Date date);

	int insertOrder(@Param("order")Order order, @Param("menuOid")int menuOid);
	
	List<Order> selectOrderByBookingOid(@Param("bookingOid")int bookginOid);
	
}

package org.corodiak.booksys.service;

import java.sql.Date;
import java.util.List;

import org.corodiak.booksys.domain.Order;
import org.corodiak.booksys.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService
{
	
	@Autowired
	OrderMapper orderMapper;

	/*
	 * 주문 추가
	 */
	@Override
	public boolean addOrder(int menuOid, int bookingOid, int quantituy)
	{
		Order order = new Order();
		order.setOrderQuantity(quantituy);
		order.setOrderBookingOid(bookingOid);
		int result = orderMapper.insertOrder(order, menuOid);
		if(result > 0) return true;
		else return false;
	}

	/*
	 * 지정날짜 전체 주문 목록 불러오기
	 */
	@Override
	public List<Order> readOrderByDate(String date)
	{
		return orderMapper.selectOrderByDate(Date.valueOf(date));
	}

	/*
	 * BookingOid 기반으로 주문정보 전체 불러오기
	 */
	@Override
	public List<Order> readOrderByBookingOid(int bookingOid)
	{
		return orderMapper.selectOrderByBookingOid(bookingOid);
	}

}

package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.Order;

public interface OrderService
{
	boolean addOrder(int menuOid, int bookingOid, int quantituy);
	List<Order> readOrderByDate(String date);
	List<Order> readOrderByBookingOid(int bookingOid);
}

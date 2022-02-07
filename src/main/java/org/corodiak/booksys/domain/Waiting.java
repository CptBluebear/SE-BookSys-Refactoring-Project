package org.corodiak.booksys.domain;

/*
 * 예약 대기 정보, Booking 인터페이스를 구현한 BookingImpl을 상속받음
 */
public class Waiting extends BookingImpl
{
	private int waitingOid;
	private Customer waitingCustomer;
	
	public int getWaitingOid() { return waitingOid; }
	public Customer getWaitingCustomer() { return waitingCustomer; }
	
	public void setWaitingOid(int waitingOid) { this.waitingOid = waitingOid; }
	public void setWaitingCustomer(Customer waitingCustomer) { this.waitingCustomer = waitingCustomer; }
}

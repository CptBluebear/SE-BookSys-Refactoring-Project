package org.corodiak.booksys.domain;

/*
 * ���� ��� ����, Booking �������̽��� ������ BookingImpl�� ��ӹ���
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

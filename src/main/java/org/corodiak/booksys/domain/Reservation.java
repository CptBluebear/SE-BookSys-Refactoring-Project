package org.corodiak.booksys.domain;

import java.sql.Time;

/*
 * 예약 정보, Booking 인터페이스를 구현한 BookingImpl을 상속받음
 */
public class Reservation extends BookingImpl
{
	private int reservationOid;
	private Customer reservationCustomer;
	private Table reservationTable;
	
	private String reservationAuthPassword;
	private Time reservationArrivalTime;
	
	public int getReservationOid() { return reservationOid; }
	public Customer getReservationCustomer() { return reservationCustomer; }
	public Table getReservationTable() { return reservationTable; }
	public String getReservationAuthPassword() { return reservationAuthPassword; }
	public Time getReservationArrivalTime() { return reservationArrivalTime; }
	
	public void setReservationOid(int reservationOid) { this.reservationOid = reservationOid; }
	public void setReservationCustomer(Customer reservationCustomer) { this.reservationCustomer = reservationCustomer; }
	public void setReservationTable(Table reservationTable) { this.reservationTable = reservationTable; }
	public void setReservationAuthPassword(String reservationAuthPassword) { this.reservationAuthPassword = reservationAuthPassword; }
	public void setReservationArrivalTime(Time reservationArrivalTime) { this.reservationArrivalTime = reservationArrivalTime; }
	
	@Override
	public String toString()
	{
		return super.toString() + "Reservation [reservationOid=" + reservationOid + ", reservationCustomer=" + reservationCustomer
				+ ", reservationTable=" + reservationTable + ", reservationAuthPassword=" + reservationAuthPassword
				+ ", reservationArrivalTime=" + reservationArrivalTime + "]";
	}
}

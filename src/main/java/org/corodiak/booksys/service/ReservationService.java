package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.Reservation;

public interface ReservationService
{
	public boolean createReservation(int covers, String date, String time, int customerOid, int tableOid);
	public List<Reservation> readReservationByCustomerOid(int customerOid);
	public boolean removeReservation(int oid);
	public List<Reservation> readReservationByDate(String date);
	public Reservation readReservationByOid(int oid);
	public boolean updateArrivalTimeByOid(int oid);
	//public String generateAuthPassword();
	//public Reservation readReservation(int reservationOid);
	//public boolean updateReservation();
	//public boolean deleteReservation(int reservationOid);
	//public List<Reservation> readReservationList();
}

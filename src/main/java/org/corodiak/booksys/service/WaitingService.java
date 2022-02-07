package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.Waiting;

public interface WaitingService
{
	public boolean createWaiting(int covers, String date, String time, int customerOid);
	public List<Waiting> readWaitingByCustomerOid(int customerOid);
	public boolean removeWaiting(int oid);
	public void checkWaiting();
}

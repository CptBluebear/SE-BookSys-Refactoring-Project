package org.corodiak.booksys.service;

import java.util.List;

import org.corodiak.booksys.domain.WalkIn;

public interface WalkInService
{
	boolean createWalkIn(int covers, String date, String time, int tableOid);
	List<WalkIn> readWalkInByDate(String date);
}

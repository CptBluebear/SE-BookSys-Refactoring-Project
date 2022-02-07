package org.corodiak.booksys.domain;

/*
 * 현장 예약 정보, Booking 인터페이스를 구현한 BookingImpl을 상속받음
 */
public class WalkIn extends BookingImpl
{
	private int walkInOid;
	private Table walkInTable;
	
	public WalkIn() {}
	
	public int getWalkInOid() { return walkInOid; }
	public Table getWalkInTable() { return walkInTable; }
	
	public void setWalkInOid(int walkInOid) { this.walkInOid = walkInOid; }
	public void setWalkInTable(Table walkInTable) { this.walkInTable = walkInTable; }
}

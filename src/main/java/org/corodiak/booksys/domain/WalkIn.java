package org.corodiak.booksys.domain;

/*
 * ���� ���� ����, Booking �������̽��� ������ BookingImpl�� ��ӹ���
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

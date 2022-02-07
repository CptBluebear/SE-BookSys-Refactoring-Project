package org.corodiak.booksys.domain;

/*
 * 테이블 정보
 */
public class Table
{
	protected int tableOid;
	protected int tableNumber;
	protected int tablePlaces;
	
	public Table() {}

	public int getTableOid() { return tableOid; }
	public void setTableOid(int tableOid) { this.tableOid = tableOid; }
	public int getTableNumber() { return tableNumber; }
	public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }
	public int getTablePlaces() { return tablePlaces; }
	public void setTablePlaces(int tablePlaces) { this.tablePlaces = tablePlaces; }
}

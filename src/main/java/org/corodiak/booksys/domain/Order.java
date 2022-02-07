package org.corodiak.booksys.domain;

/*
 * 주문 정보
 */
public class Order
{
	protected int orderOid;
	protected int orderBookingOid;
	protected Menu orderMenu;
	protected int orderQuantity;
	
	public Order() {}
	
	public int getOrderOid() { return orderOid; }
	public int getOrderBookingOid() { return orderBookingOid; }
	public Menu getOrderMenu() { return orderMenu; }
	public int getOrderQuantity() { return orderQuantity; }
	
	public void setOrderOid(int orderOid) { this.orderOid = orderOid; }
	public void setOrderBookingOid(int orderBookingOid) { this.orderBookingOid = orderBookingOid; }
	public void setOrderMenu(Menu orderMenu) { this.orderMenu = orderMenu; }
	public void setOrderQuantity(int orderQuantity) { this.orderQuantity = orderQuantity; }
}

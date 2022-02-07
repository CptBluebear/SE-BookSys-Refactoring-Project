package org.corodiak.booksys.domain;

import java.sql.Date;

/*
 * °í°´ Á¤º¸
 */
public class Customer
{
	private int customerOid;
	private String customerId;
	private String customerPassword;
	private String customerName;
	private String customerPhoneNumber;
	private Date customerBirthday;
	private boolean isAdmin;
	
	public Customer() {}
	
	public int getCustomerOid() { return customerOid; }
	public String getCustomerId() { return customerId; }
	public String getCustomerPassword() { return customerPassword; }
	public String getCustomerName() { return customerName; }
	public String getCustomerPhoneNumber() { return customerPhoneNumber; }
	public Date getCustomerBirthday() { return customerBirthday; }
	public boolean getCustomerIsAdmin() { return isAdmin; }
	
	public void setCustomerOid(int customerOid) { this.customerOid = customerOid; }
	public void setCustomerId(String customerId){ this.customerId = customerId; }
	public void setCustomerPassword(String customerPassword) { this.customerPassword = customerPassword; }
	public void setCustomerName(String customerName) { this.customerName = customerName; }
	public void setCustomerPhoneNumber(String customerPhoneNumber) { this.customerPhoneNumber = customerPhoneNumber; }
	public void setCustomerBirthday(Date customerBirthday) { this.customerBirthday = customerBirthday; }
	public void setCustomerIsAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}

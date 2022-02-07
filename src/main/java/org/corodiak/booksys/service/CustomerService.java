package org.corodiak.booksys.service;

import org.corodiak.booksys.domain.Customer;

public interface CustomerService
{
	public boolean register(String id, String password, String name, String phoneNumber, String birthday);
	public String login(String id, String password) throws Exception;
	public Customer getCustomerInformation(int oid);
}
